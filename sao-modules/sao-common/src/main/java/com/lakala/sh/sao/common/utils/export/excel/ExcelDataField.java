package com.lakala.sh.sao.common.utils.export.excel;

import com.lakala.sh.sao.common.utils.export.DataField;

/**
 * 描述: 
 * @author Hill
 *
 */
public class ExcelDataField extends DataField {
	private int width;
	private CellRender render;
	private CellRender headRender;
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public CellRender getRender() {
		return render;
	}
	public void setRender(CellRender render) {
		this.render = render;
	}
	public CellRender getHeadRender() {
		return headRender;
	}
	public void setHeadRender(CellRender headRender) {
		this.headRender = headRender;
	}
}
