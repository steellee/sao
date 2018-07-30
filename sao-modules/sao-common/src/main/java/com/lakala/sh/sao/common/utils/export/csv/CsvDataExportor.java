package com.lakala.sh.sao.common.utils.export.csv;

import com.lakala.sh.sao.common.utils.export.DataField;
import com.lakala.sh.sao.common.utils.export.ExportDataSource;
import com.lakala.sh.sao.common.utils.export.txt.TxtDataExportor;

import java.io.OutputStream;

/**
 * 描述: csv格式数据导出工具
 * @author Hill
 *
 */
public class CsvDataExportor<T> extends TxtDataExportor<T> {
	public CsvDataExportor(DataField[] fields, ExportDataSource<T> dataSource,OutputStream os) {
		super(fields, dataSource, os,",");
	}
}
