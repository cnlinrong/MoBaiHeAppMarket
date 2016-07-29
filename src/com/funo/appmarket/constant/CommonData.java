package com.funo.appmarket.constant;

public class CommonData {

	/**
	 * 调用接口的响应码
	 */
	public final static String RESPONSE_CODE_SUCCESS = "0000";
	public final static String RESPONSE_CODE_ERROR = "E1002";

	/**
	 * 分享的URL
	 */
	public final static String SHARE_URL = "http://114.55.112.85:8081/h5/sdownload.html";

	/**
	 * 请求接口地址
	 */
//	public final static String REMOTE_REQUEST_URL_HTTP = "http://192.168.0.8:8080/xyzs/api/v1/";
//	public final static String REMOTE_REQUEST_URL_HTTP = "http://192.168.0.221:8080/xyzs/api/v1/";
//	public final static String REMOTE_REQUEST_URL_HTTP = "http://192.168.0.202:8080/xyzs/api/v1/";
//	public final static String REMOTE_REQUEST_URL_HTTP = "http://192.168.0.118:8080/xyzs/api/v1/";
//	public final static String REMOTE_REQUEST_URL_HTTP = "http://114.55.112.85:8081/xyzs/api/v1/";
	public final static String REMOTE_REQUEST_URL_HTTP = "http://192.16.137.1:8080/xyzs/api/v1/";
	
	/**
	 * 文件服务器上传地址
	 */
	public static String FILE_SERVER_UPLOAD_URL = "http://192.168.0.118:8080/FileManager/upload";

	/**
	 * 文件服务器获取图片地址
	 */
	public static String FILE_SERVER_GET_IMAGE_URL = "http://192.168.0.118:8080/FileManager/get?vid=";

	/**
	 * 缓存目录
	 */
	public static final String cache_directory = "/schoolble/cache/";

	/**
	 * 友盟
	 */
	public static final String UMENG_APP_KEY = "55ded7ebe0f55ae922002794";

	/**
	 * Bugly
	 */
	public static final String BUGLY_APP_ID = "900037894";

	/**
	 * 信鸽推送
	 */
	public static final long XG_V2_ACCESS_ID = 2100170100;// 测试环境
	public static final String XG_V2_ACCESS_KEY = "ASP4P47U42SK";// 测试环境
	// public static final long XG_V2_ACCESS_ID = 2100144115;// 正式环境
	// public static final String XG_V2_ACCESS_KEY = "AC5S82I3VE7Y";// 正式环境

	/**
	 * QQ
	 */
	public static final String QQ_APP_ID = "1105418282";
	public static final String QQ_APP_KEY = "zWMdNeSmc0FO8ISg";

	/**
	 * 微信
	 */
	public static final String WX_APP_ID = "wxd1754d32557babdc";// AppID
	public static final String WX_API_SECRET = "6dccf902b03b7af531af00f669feaa78";// AppSecret
	/**
	 * 微信支付
	 */
	public static final String WX_API_KEY = "Fujianjunyoudashujujishuyxgs6666";// API密钥，在商户平台设置
	public static final String WX_MCH_ID = "1269082101";// 商户号
	public static final String WX_CALLBACK_URL = "http://123.56.141.13/law/if/wxpay/notify";// 服务器异步通知页面路径

	/**
	 * 高德定位
	 */
	public static final String GAODE_API_KEY = "8ac0aa3c5cd4f33603c1dd47614bc533";

	public static final String DESCRIPTOR_SHARE = "com.umeng.share";
	public static final String DESCRIPTOR_LOGIN = "com.umeng.login";

	private static final String TIPS = "请移步官方网站 ";
	private static final String END_TIPS = ", 查看相关说明.";
	public static final String TENCENT_OPEN_URL = TIPS + "http://wiki.connect.qq.com/android_sdk使用说明" + END_TIPS;
	public static final String PERMISSION_URL = TIPS + "http://wiki.connect.qq.com/openapi权限申请" + END_TIPS;

	public static final String SOCIAL_LINK = "http://www.umeng.com/social";
	public static final String SOCIAL_TITLE = "友盟社会化组件帮助应用快速整合分享功能";
	public static final String SOCIAL_IMAGE = "http://www.umeng.com/images/pic/banner_module_social.png";

	public static final String SOCIAL_CONTENT = "友盟社会化组件（SDK）让移动应用快速整合社交分享功能，我们简化了社交平台的接入，为开发者提供坚实的基础服务：（一）支持各大主流社交平台，"
			+ "（二）支持图片、文字、gif动图、音频、视频；@好友，关注官方微博等功能" + "（三）提供详尽的后台用户社交行为分析。http://www.umeng.com/social";

	public final static String colorGreen = "#08bc1d";// 绿色rgb值
	public final static String colorBlue = "#d3e9f7";// 蓝色背景rgb值
	public final static String coloryellow = "#f5de9b";// 蓝色背景rgb值
	
	//一天的时间戳
    public static Long ONE_DAY=24*60*60*1000L;
    //12小时的时间戳
    public static Long HALF_ONE_DAY=12*60*60*1000L;
    
	public final static String UserInfo="userInfo";//用户信息
	public final static String LoginInfo="loginInfo";//登录信息，用户名、密码、是否记住密码
	//登录账号字段名
	public static String LoginAccount="LoginAccount";
	//登录密码字段名
	public static String LoginPassword="LoginPassword";
	//登录状态字段 ：是否保存密码,是否自动登陆，1为自动登陆，2为记住密码，0为空
	public static String LoginState="LoginState";
}
