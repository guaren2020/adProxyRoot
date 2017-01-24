package com.ocean.core.common;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UniversalUtils {
	
	public static final String CONTENT_CHARSET = "UTF-8";
	/**
	 * 随机字符串
	 */
	public static String randomString(int length) {

		String str = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		int range = str.length();

		Random r = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++)
			sb.append(str.charAt(r.nextInt(range)));

		return sb.toString();
	}

	public static String string2Unicode(String string) {
		  
	     StringBuffer unicode = new StringBuffer();
	  
	     for (int i = 0; i < string.length(); i++) {
	  
	         // 取出每一个字符
	        char c = string.charAt(i);
	  
	         // 转换为unicode
	         unicode.append("\\u" + Integer.toHexString(c));
	     }
	  
	     return unicode.toString();
	 }
	
	/**
	 * 随机字符串(只有数字)
	 */
	public static String randomNumber(int length) {

		String str = "0123456789";
		int range = str.length();

		Random r = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++)
			sb.append(str.charAt(r.nextInt(range)));

		return sb.toString();
	}
	
	public static <T, E> void parseMap(Map<T, List<E>> map, E value, T key){
		
		if(map == null) return;
		
		if(!map.containsKey(key))
		{
			List<E> list = new ArrayList<E>();
			list.add(value);
			map.put(key, list);
		}
		else
		{
			List<E> list = map.get(key);
			if(!list.contains(value)) list.add(value);
		}
	}

	/**
	 * 验证是否是邮箱格式
	 */
	public static boolean isEmail(String str) {

		String regex = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
		return matches(str, regex);
	}

	/**
	 * 验证是否是密码格式
	 */
	public static final boolean isPassword(String str) {

		return matches(str, "^([0-9a-f]{32})$");
	}

	/**
	 * 验证是否是数字
	 */
	public static boolean isNumer(String str) {
		
//		if(str == null || str.equals("")) return false;
//		
//		for (int i = 0; i < str.length() - 1; i++) 
//		{
//			 char ch = str.charAt(i);
//			 if(ch < '0' && ch > '9') return false;
//		}
//		return true;
		return matches(str, "[0-9]*");
	}

	/**
	 * 是否是姓名格式("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
	 */
	public static boolean isRealname(String str) {
		
//		Pattern pat1 = Pattern.compile("[\u4E00-\u9FA5]");
		return matches(str, "([\u4E00-\u9FA5]{2,4})$");
	}
	
	/**
	 * 验证是否是手机号 
	 */
	public static boolean isMobile(String str){
		
		return matches(str, "^((13[0-9])|(15[^4,\\D])|(18[0236789])|(14[57]))\\d{8}$");
	}
	
	/**
	 * 是否是可用用户名 
	 */ 
	public static boolean isUsername(String str){
		
		return matches(str, "^[a-zA-Z0-9_]{6,16}$") || isEmail(str);
	}
	
	private static boolean matches(String str, String regex) {

		if(str == null || str.trim().equals("")) return false;
		
		Pattern pat1 = Pattern.compile(regex);
		Matcher mat1 = pat1.matcher(str);
		return mat1.matches();
	}

	/**
	 * 得到异常堆栈详细信息
	 */
	public static String getStackTraceElement(Exception e) {

		if (e == null)
			return null;

		String n = "\n";
		StringBuilder sb = new StringBuilder("系统异常信息: ");
		sb.append(n);

		StackTraceElement[] error = e.getStackTrace();
		for (StackTraceElement stackTraceElement : error)
			sb.append(stackTraceElement.toString()).append(n);

		return sb.toString();
	}
	
	public static String getHostAddress(){
		
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			throw new RuntimeException("获取本机ip失败！！");
		}
	}
}
