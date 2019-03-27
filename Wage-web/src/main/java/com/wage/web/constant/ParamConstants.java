package com.wage.web.constant;

public class ParamConstants {

	/**
    Session中存放的用户对象名称
	 */
	public static final String LOCAL_CLINET_USER = "user";

	/**
    Session中存放的学生对象名称
	 */
	public static final String LOCAL_CLINET_EMP = "emp";

	/**
    Session中存放的老师对象名称
	 */
	public static final String LOCAL_CLINET_TEACHER = "teacher";

	/**
	 * Session中存放的用户登录部门；
	 */
	public static final String LOCAL_CLINET_DEPT = "dept";

	/**
	 * Session失效时间
	 */
	public static final int SESSION_MAX_INACTIVE_INTERVAL = 1800;//14400;

	/**
	 Session中存放的管理员权限对象名称
	 */
	public static final String EMP_AUTHORITY = "empAuthority";

	/**
	 Session中存放的员工公告条数
	 */
	public static final String EMP_NOTICE_COUNT = "empNoticeCount";

	/**
	 Session中存放的员工公告
	 */
	public static final String EMP_NOTICE = "empNotice";

	/**
	 无头像
	 */
	public static final String NO_HEAD_MD = "group1/M00/00/00/rBA-21sDvUiABB-RAABiG04DJeU293.jpg" /*"37867cd54bfa4b82001110132a99d1e2"*/;

	/**
	 * 逻辑删除，0表示未删除，1表示删除
	 */
	public static final String IS_DELETE_1 = "1";
	public static final String IS_DELETE_0 = "0";

	/**
	 * 页面类型
	 */
	public static final String PAGE_TYPE_NAME = "pageType";

	public static final String PAGE_TYPE_ADD = "add";

	public static final String PAGE_TYPE_EDIT = "edit";

	public static final String PAGE_TYPE_VIEW = "view";

	/**
	 * 每页显示的记录条数与翻页个数
	 */
	public static final int PAGE_RECORD = 20;//每页显示的记录
	static public final int TURN_NUMBER = 4;//翻页个数


	/**
	 * 用户类型 0表示员工，1表示管理员
	 */
	public static final Short USER_TYPE_0 = 0; //员工
	public static final Short USER_TYPE_1 = 1; //管理员


	public static final String USERP = "ROLE_USER"; //员工
	public static final String ADMINP = "ROLE_MANAGER"; //管理员


	/**
	 * 作业发布状态：0：未发布（草稿状态）、1：已发布
	 */
	public static final String PUBLISHSTATUS_0 = "0"; //未发布（草稿状态）
	public static final String PUBLISHSTATUS_1 = "1"; //已发布

	/**
	 * 作业提交状态：0：未提交（草稿状态）、1：已提交
	 */
	public static final String COMMIT_STATUS_NOT_COMMIT = "0"; //0：未提交（草稿状态）
	public static final String COMMIT_STATUS_COMMITTED = "1"; //1：已提交


	/**
	 * 系统年月格式，如：2018-12
	 */
	public static final String DATE_FORMAT_YM = "yyyy-MM";
	/**
	 * 系统年月日格式，如：2018-12-12
	 */
	public static final String DATE_FORMAT_YMD = "yyyy-MM-dd";
	/**
	 * 系统年月日格式，如：2018-12-12 06:25:20
	 */
	public static final String DATE_FORMAT_YMDHMS = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 系统年月日格式，如：2018-12-12 06:25:20:666
	 */
	public static final String DATE_FORMAT_YMDHMSS = "yyyy-MM-dd HH:mm:ss.SSS";

	/**
	 * 员工重置密码
	 */
	public static final String EMP_RESET_PASSWORD = "123456";

}
