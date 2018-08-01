package com.lakala.sh.sao.common.exception;

import com.lakala.sh.sao.common.constants.SysCode;

/**
 * 异常信息封装
 * @author steellee
 * @date 2018/06/28
 */
public class BaseException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private int httpStatus = 200;
	private String code;
	private String msg;
	private Object addiData;
	public BaseException() {
	}
	public BaseException(int httpStatus) {
		this.httpStatus=httpStatus;
	}
	public BaseException(String code) {
		this.code = code;
	}
	public BaseException(String code,int httpStatus) {
		this.code = code;
		this.httpStatus=httpStatus;
	}
	public BaseException(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	public BaseException(String code, String msg,int httpStatus) {
		this.code = code;
		this.msg = msg;
		this.httpStatus=httpStatus;
	}
	public BaseException(SysCode kpei) {
		this.code = kpei.CODE;
		this.msg = kpei.MESSAGE;
	}
	public BaseException(SysCode kpei,int httpStatus) {
		this.code = kpei.CODE;
		this.msg = kpei.MESSAGE;
		this.httpStatus=httpStatus;
	}
	public BaseException(String code, Exception ex) {
		super(code,ex);
	}
	public int getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(int httpStatus) {
		this.httpStatus = httpStatus;
	}
	public String getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}
	public Object getAddiData() {
		return addiData;
	}
	public void setAddiData(Object addiData) {
		this.addiData = addiData;
	}
	/*public FeedBack toFeedBack() {
		return new FeedBack(code, msg);
	}
	@SuppressWarnings("unchecked")
	public <T extends FeedBack> T toFeedBack(Class<? extends FeedBack> clazz) {
		FeedBack feedBack = null;
		try {
			feedBack = clazz.newInstance();
			feedBack.setCode(code);
			feedBack.setMsg(msg);
			if (this instanceof LocalException) {
				LocalException le = (LocalException) this;
				feedBack.setField(le.getField());
			}
		} catch (Exception e) {
			feedBack = new FeedBack(SysCode.SYSTEM_ERR);
		}
		return (T) feedBack;
	}*/
}
