package com.lakala.sh.sao.common.exception;

import com.lakala.sh.sao.common.constants.SysCode;

/**
 * 本地业务异常
 * 
 */
public class LocalException extends BaseException {
	private static final long serialVersionUID = 1L;
	private String field;
	public LocalException() {
		super();
	}
	public LocalException(int httpStatus) {
		super(httpStatus);
	}
	public LocalException(String code) {
		super(code);
	}
	public LocalException(String code, int httpStatus) {
		super(code, httpStatus);
	}
	public LocalException(String code, String message) {
		super(code, message);
	}
	public LocalException(String code, String message, int httpStatus) {
		super(code, message, httpStatus);
	}
	public LocalException(String field, String code, String message) {
		super(code, message);
		this.field = field;
	}
	public LocalException(String field, String code, String message, int httpStatus) {
		super(code, message, httpStatus);
		this.field = field;
	}
	public LocalException(SysCode kpei) {
		super(kpei);
	}
	public LocalException(SysCode kpei, int httpStatus) {
		super(kpei, httpStatus);
	}
	public LocalException(String field, SysCode kpei) {
		super(kpei);
		this.field = field;
	}
	public LocalException(String field, SysCode kpei, int httpStatus) {
		super(kpei, httpStatus);
		this.field = field;
	}
	public LocalException(String code, Exception ex) {
		super(code, ex);
	}
	public LocalException(String field, String code, Exception ex) {
		super(code, ex);
		this.field = field;
	}
	public String getField() {
		return field;
	}
}
