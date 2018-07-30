package com.lakala.sh.sao.common.utils.export.txt;

import com.lakala.sh.sao.common.utils.StringUtil;
import com.lakala.sh.sao.common.utils.export.DataExportor;
import com.lakala.sh.sao.common.utils.export.DataField;
import com.lakala.sh.sao.common.utils.export.ExportDataSource;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;

/**
 * /**
 * 描述: Txt格式数据导出工具
 * @author Hill
 * @param <T>
 */
public class TxtDataExportor<T> extends DataExportor<T> {
	
	/**
	 * 间隔符
	 */
	private String split = ",";
	
	private StringBuilder stringBuilder = new StringBuilder();
	
	public TxtDataExportor(DataField[] fields,ExportDataSource<T> dataSource,OutputStream os){
		super(null,fields, dataSource, os);
	}
	
	public TxtDataExportor(DataField[] fields,ExportDataSource<T> dataSource,OutputStream os,String split){
		this(fields, dataSource, os);
		if(!StringUtil.isBlank(split)){
			this.split = split;
		}
	}
	
	@Override
	public void export() throws IOException {
		Collection<?> dataList;
		outputBOM();
		outputHead();
//		do{
			dataList = dataSource.getData();
			output(dataList);
//		}while(dataList.size());
		if(count > 0){
			writeString(stringBuilder.toString());
			stringBuilder.delete(0, stringBuilder.length());
			count = 0;
		}
		os.flush();
	}
	
	private void outputHead() throws IOException{
		StringBuilder b = new StringBuilder();
		if(isUseIndex()){
			b.append("序号").append(split);
		}
		for(DataField field : fields){
			b.append(field.getName()).append(split);
		}
		b.deleteCharAt(b.lastIndexOf(split));
		b.append("\n");
		writeString(b.toString());
	}

	private int count = 0;
	protected void output(Collection<?> dataList) throws IOException {
		for(Object data : dataList){
			stringBuilder.append(formatToLine(data));
			stringBuilder.append("\r\n");
			count++;
			if(count >= 100){
				writeString(stringBuilder.toString());
				stringBuilder.delete(0, stringBuilder.length());
				count = 0;
			}
		}
	}
	
	private String formatToLine(Object data){
		StringBuilder tmp = new StringBuilder();
		if(isUseIndex()){
			tmp.append(++currentIndex).append(split);;
		}
		for(DataField field : fields){
			Object value = getValue(data, field.getField());
			if(value == null){
				value = "";
			}
			tmp.append(value).append(split);
		}
		tmp.deleteCharAt(tmp.lastIndexOf(split));
		return tmp.toString();
	}
	
	/**
	 * 输出UTF-8的BOM头,UTF-8无BOM打开有可能乱码
	 * @throws IOException
	 */
	private void outputBOM() throws IOException{
		String[] bom = new String[]{"EF","BB","BF"};
		byte[] bytes = new byte[bom.length];
		for(int i = 0; i < bom.length; i++){
			bytes[i] = (byte) Integer.parseInt(bom[i], 16);
		}
		os.write(bytes);
	}
	
	private void writeString(String v) throws IOException{
		os.write(v.getBytes("UTF-8"));
	}

}
