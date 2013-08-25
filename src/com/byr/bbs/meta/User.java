package com.byr.bbs.meta;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * @author Liutong
 * 
 */
public class User {

	private static final String TAG = User.class.getSimpleName();

	public static final String ID = "id";
	public static final String USER_NAME = "user_name";
	public static final String FACE_URL = "face_url";
	public static final String FACE_WIDTH = "face_width";
	public static final String FACE_HIGHT = "face_height";
	public static final String GENDER = "gender";
	public static final String ASTRO = "astro";
	public static final String LIFE = "life";
	public static final String QQ = "qq";
	public static final String MSN = "msn";
	public static final String HOME_PAGE = "home_page";
	public static final String LEVEL = "level";
	public static final String IS_ONLINE = "is_online";
	public static final String POST_COUNT = "post_count";
	public static final String LAST_LOGIN_TIME = "last_login_time";
	public static final String LAST_LOGIN_IP = "last_login_ip";
	public static final String IS_HIDE = "is_hide";
	public static final String IS_REGISTER = "is_register";
	public static final String FIRST_LOGIN_TIME = "first_login_time";
	public static final String LOGIN_COUNT = "login_count";
	public static final String IS_ADMIN = "is_admin";
	public static final String STAY_COUNT = "stay_count";
	public static final String SCORE = "score";
	public static final String ROLE = "role";

	/**
	 * 用户id
	 */
	private String id;

	/**
	 * 用户昵称
	 */
	private String user_name;

	/**
	 * 用户头像地址
	 */
	private String face_url;

	/**
	 * 用户头像宽度
	 */
	private int face_width;

	/**
	 * 用户头像高度
	 */
	private int face_height;

	/**
	 * 用户性别
	 * <ul>
	 * <li>m 男性</li>
	 * <li>f 女性</li>
	 * <li>n 隐藏性别</li>
	 * </ul>
	 */
	private String gender;

	/**
	 * 用户星座,隐藏星座时值为空
	 */
	private String astro;

	/**
	 * 用户生命值
	 */
	private int life;

	private String qq;
	private String msn;
	private String home_page;

	/**
	 * 用户身份
	 */
	private String level;
	private boolean is_online;

	/**
	 * 用户发文数量
	 */
	private int post_count;
	private int last_login_time;
	private String last_login_ip;

	/**
	 * 用户是否隐藏性别和星座
	 */
	private boolean is_hide;

	/**
	 * 用户是否通过注册审批
	 */
	private boolean is_register;

	/**
	 * 用户注册时间
	 */
	private int first_login_time;

	/**
	 * 用户登录次数
	 */
	private int login_count;
	private boolean is_admin;

	/**
	 * 用户挂站时间, 以秒为单位
	 */
	private int stay_count;

	private int score;
	private String role;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getFace_url() {
		return face_url;
	}

	public void setFace_url(String face_url) {
		this.face_url = face_url;
	}

	public int getFace_width() {
		return face_width;
	}

	public void setFace_width(int face_width) {
		this.face_width = face_width;
	}

	public int getFace_height() {
		return face_height;
	}

	public void setFace_height(int face_height) {
		this.face_height = face_height;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAstro() {
		return astro;
	}

	public void setAstro(String astro) {
		this.astro = astro;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public String getQQ() {
		return qq;
	}

	public void setQQ(String qq) {
		this.qq = qq;
	}

	public String getMsn() {
		return msn;
	}

	public void setMsn(String msn) {
		this.msn = msn;
	}

	public String getHome_page() {
		return home_page;
	}

	public void setHome_page(String home_page) {
		this.home_page = home_page;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public boolean isIs_online() {
		return is_online;
	}

	public void setIs_online(boolean is_online) {
		this.is_online = is_online;
	}

	public int getPost_count() {
		return post_count;
	}

	public void setPost_count(int post_count) {
		this.post_count = post_count;
	}

	public int getLast_login_time() {
		return last_login_time;
	}

	public void setLast_login_time(int last_login_time) {
		this.last_login_time = last_login_time;
	}

	public String getLast_login_ip() {
		return last_login_ip;
	}

	public void setLast_login_ip(String last_login_ip) {
		this.last_login_ip = last_login_ip;
	}

	public boolean isIs_hide() {
		return is_hide;
	}

	public void setIs_hide(boolean is_hide) {
		this.is_hide = is_hide;
	}

	public boolean isIs_register() {
		return is_register;
	}

	public void setIs_register(boolean is_register) {
		this.is_register = is_register;
	}

	public int getFirst_login_time() {
		return first_login_time;
	}

	public void setFirst_login_time(int first_login_time) {
		this.first_login_time = first_login_time;
	}

	public int getLogin_count() {
		return login_count;
	}

	public void setLogin_count(int login_count) {
		this.login_count = login_count;
	}

	public boolean isIs_admin() {
		return is_admin;
	}

	public void setIs_admin(boolean is_admin) {
		this.is_admin = is_admin;
	}

	public int getStay_count() {
		return stay_count;
	}

	public void setStay_count(int stay_count) {
		this.stay_count = stay_count;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		Log.i(TAG, "user info");
		final String EQUAL = "=";
		final String COMMA = ",";
		StringBuilder builder = new StringBuilder();
		builder.append(ID).append(EQUAL).append(id).append(COMMA);
		builder.append(USER_NAME).append(EQUAL).append(user_name).append(COMMA);
		builder.append(GENDER).append(EQUAL).append(gender).append(COMMA);
		builder.append(HOME_PAGE).append(EQUAL).append(home_page).append(COMMA);
		builder.append(LEVEL).append(EQUAL).append(level).append(COMMA);
		builder.append(LAST_LOGIN_TIME).append(EQUAL)
				.append(String.valueOf(last_login_time));
		return builder.toString();
	}

	public static User parse(String json) {
		try {
			Gson gson = new Gson();
			User user = gson.fromJson(json, User.class);
			return user;
		} catch (JsonSyntaxException e) {
			e.printStackTrace();

			return null;
		}
	}

}
