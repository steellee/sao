package com.lakala.sh.sao.common.utils.export;

/**
 * @描述: 分页参数.
 * @作者: Along.shen .
 * @创建时间:2014-11-13,上午10:19:07.
 * @版本:
 */
public class DataPage {
//	/**
//	 * 总页数
//	 */
//	private int countPage = 1;
	
	/**
	 * 一页中最多条目数
	 */
	private int maxEntityInOnePage;
	

	public DataPage() {
	}

	public DataPage (int maxEntityInOnePage) {
		this.maxEntityInOnePage = maxEntityInOnePage;
	}

//	public int getCountPage() {
//		return countPage;
//	}
//
//	public void setCountPage(int countPage) {
//		this.countPage = countPage;
//	}

	public int getMaxEntityInOnePage() {
		return maxEntityInOnePage;
	}

	public void setMaxEntityInOnePage(int maxEntityInOnePage) {
		this.maxEntityInOnePage = maxEntityInOnePage;
	}
	
}
