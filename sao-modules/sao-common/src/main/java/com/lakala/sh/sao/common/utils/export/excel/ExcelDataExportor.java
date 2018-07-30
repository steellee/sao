package com.lakala.sh.sao.common.utils.export.excel;

import com.lakala.sh.sao.common.utils.StringUtil;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import com.lakala.sh.sao.common.utils.export.DataExportor;
import com.lakala.sh.sao.common.utils.export.DataField;
import com.lakala.sh.sao.common.utils.export.DataPage;
import com.lakala.sh.sao.common.utils.export.ExportDataSource;
import com.lakala.sh.sao.common.utils.export.csv.CsvDataExportor;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;

/**
 * 描述: Excel数据导出工具,默认为CSV格式,CSV格式文件可以用excel正常打开(兼容03和07+) 如果指定为Excel,则导出Excel03
 * 
 * @author Hill
 * 
 */
public class ExcelDataExportor<T> extends DataExportor<T> {
	private MODE mode;

	private String sheetName;

	public ExcelDataExportor(DataField[] fields, ExportDataSource<T> dataSource, OutputStream os) {
		this(fields, dataSource, os, MODE.CSV);
	}

	/**
	 * 没有分页参数调用
	 * 
	 * @param fields
	 * @param dataSource
	 * @param os
	 * @param mode
	 */
	public ExcelDataExportor(DataField[] fields, ExportDataSource<T> dataSource, OutputStream os, MODE mode) {
		super(null, fields, dataSource, os);
		this.mode = mode;
	}

	/**
	 * 带分页参数
	 * 
	 * @param pageParam
	 * @param fields
	 * @param dataSource
	 * @param os
	 * @param mode
	 */
	public ExcelDataExportor(DataPage pageParam, DataField[] fields, ExportDataSource<T> dataSource, OutputStream os, MODE mode) {
		super(pageParam, fields, dataSource, os);
		this.mode = mode;
	}

	@Override
	public void export() throws IOException {
		if (mode == null || mode == MODE.CSV) {
			new CsvDataExportor<T>(fields, dataSource, os).export();
		} else {
			try {
				if (pageParam == null) {
					exportExcel07();
				} else {
					exportExcel07ForPage();
				}
			} catch (RowsExceededException e) {
				throw new RuntimeException(e);
			} catch (WriteException e) {
				throw new RuntimeException(e);
			}
		}
	}

	private void exportExcel07ForPage() throws IOException, RowsExceededException, WriteException {
		Collection<?> dataList;
		WritableWorkbook workBook = Workbook.createWorkbook(os);
		dataList = dataSource.getData();
		int countPage = 1;
		//如果下载的数据存在且需要分页
		if (dataList.size() > 0) {
			countPage = (dataList.size() % pageParam.getMaxEntityInOnePage()) == 0 ? (dataList.size() / pageParam.getMaxEntityInOnePage()) : (dataList.size() / pageParam.getMaxEntityInOnePage()) + 1;
		}

		int maxEntityInOnePage = pageParam.getMaxEntityInOnePage();// 一页中最多条目数
		int sheetIndex = 1;
		int rowIndex = 1;
		int roeIndexPage = 1;
		WritableSheet sheet = workBook.createSheet(createSheetName(sheetIndex), 0);
		createHead(sheet);
		Object value;
		Label label;
		do {

			for (Object data : dataList) {

				if (sheetIndex < countPage && ((rowIndex - 1) % maxEntityInOnePage) == 0 && rowIndex > 1) {
					roeIndexPage = 1;
					sheetIndex++;
					sheet = workBook.createSheet(createSheetName(sheetIndex), 0);
					createHead(sheet);// 创建头部
				}

				int count = 0;
				if (isUseIndex()) {
					label = new Label(0, rowIndex, "" + rowIndex);
					sheet.setColumnView(0, 3);
					sheet.addCell(label);
					count++;
				}
				for (int i = 0 + count; i < fields.length + count; i++) {
					value = getValue(data, fields[i - count].getField());
					if (value != null) {
						label = new Label(i, roeIndexPage, value.toString());
						sheet.setColumnView(i, fields[i - count].getColumnWidth());
						sheet.addCell(label);
					}
				}
				roeIndexPage++;
				rowIndex++;
			}
		} while (rowIndex <= dataList.size());
		workBook.write();
		workBook.close();
		os.flush();
	}

	/**
	 * 
	 */
	private void exportExcel07() throws IOException, RowsExceededException, WriteException {
		Collection<?> dataList;
		WritableWorkbook workBook = Workbook.createWorkbook(os);
		int sheetIndex = 1;
		WritableSheet sheet = workBook.createSheet(createSheetName(sheetIndex), 0);
		int rowIndex = 1;
		createHead(sheet);// 创建头部
		Object value;
		Label label;
		do {
			dataList = dataSource.getData();

			for (Object data : dataList) {
				int count = 0;
				if (isUseIndex()) {
					label = new Label(0, rowIndex, "" + rowIndex);
					sheet.setColumnView(0, 3);
					sheet.addCell(label);
					count++;
				}
				for (int i = 0 + count; i < fields.length + count; i++) {
					value = getValue(data, fields[i - count].getField());
					if (value != null) {
						label = new Label(i, rowIndex, value.toString());
						sheet.setColumnView(i, fields[i - count].getColumnWidth());
						sheet.addCell(label);
					}
				}
				rowIndex++;
			}
		} while (rowIndex <= dataList.size());
		workBook.write();
		workBook.close();
		os.flush();
	}

	/**
	 * 将指定行创建为表头
	 * 
	 * @param row
	 * @throws WriteException
	 * @throws RowsExceededException
	 */
	private void createHead(WritableSheet sheet) throws RowsExceededException, WriteException {
		DataField field;
		int count = 0;
		Label label;
		if (isUseIndex()) {
			label = new Label(0, 0, "序号"); // 第一行第一列为序号
			sheet.setColumnView(0, 3); // 第一列宽度为3
			sheet.addCell(label); // 把定义好的单元格添加到工作表中
			count++;
		}
		for (int i = 0 + count; i < fields.length + count; i++) {
			field = fields[i - count];
			label = new Label(i, 0, field.getName());
			sheet.setColumnView(i, field.getColumnWidth());
			sheet.addCell(label);
			continue;
		}
	}

	private String createSheetName(int index) {
		if (StringUtil.isBlank(sheetName)) {
			this.sheetName = "sheet";
		}
		return this.sheetName + index;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
}
