package com.lakala.sh.sao.common.utils.export;

import java.util.List;

/**
 * 描述: 数据导出,数据源
 * @author Hill
 *
 */
public interface ExportDataSource<T>{
	<T> List<T> getData();
}
