package org.moana.util;
/**
 * @TODO：参数设置
 * @fileName : org.moana.util.Config.java
 * author | version |   
 * Jiong | 1.0 |
 */
public class Config {
	public static final long LIMIT_PURCHASE_NUM = 5; //电影票限购数
	
	public static final String REDIS_ADDRESS = "119.29.10.25:6379";
	
	public static final int OPERATE_SUCCESS = 1001; //操作成功
	
	public static final int OPERATE_FAILURE = 1002; //操作失败
	
	public static final int USER_UNLOGIN = 1003; //用户未登录
	public static final String USER_UNLOGIN_MSG = "您还没有登录";
	
}

