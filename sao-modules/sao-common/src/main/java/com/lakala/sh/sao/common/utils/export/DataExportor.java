package com.lakala.sh.sao.common.utils.export;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 描述:数据导出工具
 * 
 * @author Hill
 * 
 * @param <T>
 */
public abstract class DataExportor<T> {
	/**
	 * 分页参数
	 */
	protected DataPage pageParam;
	
	/**
	 * 字段集
	 */
	protected DataField[] fields;
	/**
	 * 数据源
	 */
	protected ExportDataSource<T> dataSource;
	/**
	 * 输出流
	 */
	protected OutputStream os;

	/**
	 * 每次去数据源取数最大条数
	 */
	protected Integer pageSize = 1000;

	/**
	 * 输出序号列
	 */
	private boolean useIndex = false;

	/**
	 * 当前输出行号
	 */
	protected int currentIndex = 0;

	public DataExportor(DataPage pageParam, DataField[] fields, ExportDataSource<T> dataSource, OutputStream os) {
		if (fields == null || fields.length < 1) {
			throw new IllegalArgumentException("dataFields could not be null");
		}
		if (dataSource == null) {
			throw new IllegalArgumentException("dataSource could not be null");
		}
		if (os == null) {
			throw new IllegalArgumentException("outputstream could not be null");
		}
		this.fields = fields;
		this.dataSource = dataSource;
		this.os = os;
		this.pageParam = pageParam;
	}

	public abstract void export() throws IOException;

	@SuppressWarnings("rawtypes")
	protected Object getValue(Object data, String field) {
		if (data == null) {
			return null;
		}
		Class cls = data.getClass();
		if (Map.class.isAssignableFrom(cls)) {
			Map map = (Map) data;
			if (map.containsKey(field)) {
				return map.get(field);
			}
			String key = field;
			while (key.indexOf(".") > 0) {
				key = key.substring(0, key.lastIndexOf("."));
				if (map.containsKey(key)) {
					return getValue(map.get(key), field.substring(key.length() + 1));
				}
			}
		} else {
			if (field.indexOf(".") > 0) {
				return getValue(data, field.substring(0, field.indexOf(".")));
			} else {
				Method[] methods = cls.getMethods();
				// 寻找getter
				String fieldGetter = "get" + field.substring(0, 1).toUpperCase() + field.substring(1, field.length());
				for (Method m : methods) {
					if (m.getName().equals(fieldGetter) && m.getParameterTypes().length == 0) {
						try {
							return m.invoke(data, new Object[] {});
						} catch (Exception e) {
							return "getValueError";
						}
					}
				}
				// 没有找到getter，寻找属性
				Field[] fields = cls.getFields();
				for (Field f : fields) {
					if (f.getName().equals(field)) {
						try {
							return f.get(data);
						} catch (Exception e) {
							return "getValueError";
						}
					}
				}
			}
		}
		return null;
	}

	public boolean isUseIndex() {
		return useIndex;
	}

	public void setUseIndex(boolean useIndex) {
		this.useIndex = useIndex;
	}
}
