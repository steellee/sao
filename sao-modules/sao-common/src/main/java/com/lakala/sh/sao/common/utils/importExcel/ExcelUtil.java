/**
 *com.lakala.sh.sao.common.utils.importExcel.ExcelUtil.java;
 */
package com.lakala.sh.sao.common.utils.importExcel;

import jxl.*;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 
 * <ul>
 * <li>Title:Excel导入工具类</li>
 * <li>Description:</li>
 * <li>Copyright: www.gzzyzz.com</li>
 * <li>Company:</li>
 * </ul>
 * 
 * @author Hill
 * @version 2013-11-26
 */
public class ExcelUtil {

	/**
	 * 从excel文件中读取内容 以第一行为key，从第二行开始为正式数据
	 * 
	 * @param file
	 * @param sheetIndex
	 * @return
	 * @throws BiffException
	 * @throws IOException
	 */
	public static final List<Map<String, String>> readSheet(File file, int sheetIndex) {
		Workbook workbook = null;
		List<Map<String, String>> result = null;
		try {
			workbook = Workbook.getWorkbook(file);
			jxl.Sheet sheet = workbook.getSheet(0);
			int rowSize = sheet.getRows();
			Cell[] headCell = sheet.getRow(0);
			result = new ArrayList<Map<String, String>>(rowSize - 1);
			Cell[] cells;
			for (int i = 1; i < rowSize; i++) {
				cells = sheet.getRow(i);
				Map<String, String> data = new HashMap<String, String>();
				for (int j = 0; j < headCell.length; j++) {
					if (cells.length > j) {
						if (cells[j].getType() == CellType.DATE) { // TODO:时间类型读取
							DateCell dc = (DateCell) cells[j];
							Date date = dc.getDate();
							TimeZone zone = TimeZone.getTimeZone("GMT");
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							sdf.setTimeZone(zone);
							String dateStr = sdf.format(date);
							data.put(trim(headCell[j].getContents()), dateStr);
						} else if (cells[j].getType() == CellType.NUMBER) { // 数字类型读取
							if (cells[j].getContents().indexOf(".") > 0) {
								NumberCell nc = (NumberCell) cells[j];
								double value = nc.getValue();
								data.put(trim(headCell[j].getContents()), trim(String.valueOf(value)));
							} else {
								data.put(trim(headCell[j].getContents()), trim(cells[j].getContents()));
							}
						} else {
							data.put(trim(headCell[j].getContents()), trim(cells[j].getContents()));
						}

					}
				}
				result.add(data);
			}
		} catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }  finally {
			workbook.close();
		}

		return result;
	}

	private static final String trim(String str) {
		return str == null ? "" : str.trim();
	}
}
