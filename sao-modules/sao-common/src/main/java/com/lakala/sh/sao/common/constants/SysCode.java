package com.lakala.sh.sao.common.constants;

/**
 * 系统通用代码
 * 根据需要各系统继承此类以扩展消息描述
 * @author steellee
 * @date 2018/06/28
 */
public class SysCode {

	public static final SysCode SUCCESS = new SysCode("000000", "SUCCESS");

	public static final SysCode NO_NULL = new SysCode("010000", "数据不能为空");

	public static final SysCode PARAM_TYPE_ERR = new SysCode("020000", "参数类型错误");

	public static final SysCode PARAM_FORMAT_ERR = new SysCode("030000", "参数格式错误");

	public static final SysCode NO_SUPPORT = new SysCode("040000", "不支持该操作");
	public static final SysCode NO_SUPPORT_PARAM = new SysCode("040001", "不支持该参数");

	public static final SysCode NO_POWER = new SysCode("050000", "无权限");
	public static final SysCode NO_LOGIN = new SysCode("050001", "未登录");
	
	public static final SysCode VALID_ERR = new SysCode("060000", "数据验证失败");

	public static final SysCode NOT_EXIST = new SysCode("999990", "数据不存在");
	public static final SysCode EXISTED = new SysCode("999991", "数据已存在");
	public static final SysCode HTTP_ERR = new SysCode("999997", "HTTP错误");
	public static final SysCode THIRD_ERR = new SysCode("999998", "第三方错误");
	public static final SysCode SYSTEM_ERR = new SysCode("999999", "系统错误");

	public String CODE;
	public String MESSAGE;
	private SysCode(String CODE, String MESSAGE) {
		this.CODE = CODE;
		this.MESSAGE = MESSAGE;
	}
}
