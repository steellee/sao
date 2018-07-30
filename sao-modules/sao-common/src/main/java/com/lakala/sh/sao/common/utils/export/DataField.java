package com.lakala.sh.sao.common.utils.export;

/**
 * 描述: 数据Field
 * 
 * @author Hill
 * 
 */
public class DataField {
	private static final long serialVersionUID = 6485814331192914480L;
	/**
	 * 字段名称
	 */
	private String name;
	/**
	 * 字段名
	 */
	private String field;

	/**
	 * 列宽
	 */
	private int columnWidth = 15;

	public DataField() {
	}

	public DataField(String name, String field) {
		this.name = name;
		this.field = field;
	}

	public DataField(String name, String field, int columnWidth) {
		this.name = name;
		this.field = field;
		this.columnWidth = columnWidth;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public int getColumnWidth() {
		return columnWidth;
	}

	public void setColumnWidth(int columnWidth) {
		this.columnWidth = columnWidth;
	}
}
