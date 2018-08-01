package com.lakala.sh.sao.common.exception;

import com.lakala.sh.sao.common.constants.SysCode;

/**
 * 远端服务调用异常
 * @author steellee
 * @date 2018/06/28
 */
public class RemoteException extends BaseException {
	private static final long serialVersionUID = 1L;
	private String thirdCode;
	private String thirdMsg;
	public RemoteException() {
		super();
	}
	public RemoteException(int httpStatus) {
		super(httpStatus);
	}
	public RemoteException(String code) {
		super(code);
	}
	public RemoteException(String code, int httpStatus) {
		super(code, httpStatus);
	}
	public RemoteException(String code, String message) {
		super(code, message);
	}
	public RemoteException(String code, String message, int httpStatus) {
		super(code, message, httpStatus);
	}
	public RemoteException(SysCode sysCode) {
		super(sysCode);
	}
	public RemoteException(SysCode kpei, int httpStatus) {
		super(kpei, httpStatus);
	}
	public RemoteException(String code, Exception ex) {
		super(code,ex);
	}
	public String getThirdCode() {
		return thirdCode;
	}
	public void setThirdCode(String thirdCode) {
		this.thirdCode = thirdCode;
	}
	public String getThirdMsg() {
		return thirdMsg;
	}
	public void setThirdMsg(String thirdMsg) {
		this.thirdMsg = thirdMsg;
	}
}
