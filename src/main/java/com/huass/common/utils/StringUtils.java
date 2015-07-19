
package com.huass.common.utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.LocaleResolver;

import com.google.common.collect.Lists;


public class StringUtils extends org.apache.commons.lang3.StringUtils {
	
	public static String lowerFirst(String str){
		if(StringUtils.isBlank(str)) {
			return "";
		} else {
			return str.substring(0,1).toLowerCase() + str.substring(1);
		}
	}
	
	public static String upperFirst(String str){
		if(StringUtils.isBlank(str)) {
			return "";
		} else {
			return str.substring(0,1).toUpperCase() + str.substring(1);
		}
	}

	
	public static String replaceHtml(String html) {
		if (isBlank(html)){
			return "";
		}
		String regEx = "<.+?>";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(html);
		String s = m.replaceAll("");
		return s;
	}

	
	public static String abbr(String str, int length) {
		if (str == null) {
			return "";
		}
		try {
			StringBuilder sb = new StringBuilder();
			int currentLength = 0;
			for (char c : replaceHtml(StringEscapeUtils.unescapeHtml4(str)).toCharArray()) {
				currentLength += String.valueOf(c).getBytes("GBK").length;
				if (currentLength <= length - 3) {
					sb.append(c);
				} else {
					sb.append("..");
					break;
				}
			}
			return sb.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	
	public static String rabbr(String str, int length) {
        return abbr(replaceHtml(str), length);
	}
		
	
	
	public static Double toDouble(Object val){
		if (val == null){
			return 0D;
		}
		try {
			return Double.valueOf(trim(val.toString()));
		} catch (Exception e) {
			return 0D;
		}
	}

	
	public static Integer[] toIntegers(String... val){
		Integer[] in = new Integer[val.length];
		for (int i = 0; i < in.length; i++) {
			in[i] = toInteger(val[i]);
		}
		return in;
	}	
	
	
	public static Float toFloat(Object val){
		return toDouble(val).floatValue();
	}

	
	public static Long toLong(Object val){
		return toDouble(val).longValue();
	}

	
	public static Integer toInteger(Object val){
		return toLong(val).intValue();
	}
	
	
	public static String getMessage(String code, Object[] args) {
		LocaleResolver localLocaleResolver = SpringContextHolder.getBean(LocaleResolver.class);
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
		Locale localLocale = localLocaleResolver.resolveLocale(request);
		return SpringContextHolder.getApplicationContext().getMessage(code, args, localLocale);
	}
	
	
	public static String getRemoteAddr(HttpServletRequest request){
		String remoteAddr = request.getHeader("X-Real-IP");
        if (isNotBlank(remoteAddr)) {
        	remoteAddr = request.getHeader("X-Forwarded-For");
        }else if (isNotBlank(remoteAddr)) {
        	remoteAddr = request.getHeader("Proxy-Client-IP");
        }else if (isNotBlank(remoteAddr)) {
        	remoteAddr = request.getHeader("WL-Proxy-Client-IP");
        }
        return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
	}
	
	public static String sub4br(String str)
	{
		if (str == null) {
			return "";
		}
		try {
			StringBuilder sb = new StringBuilder();
			int currentLength = 0;
			int subLength = 18;
			int ccon = 1;
			int ccon2 = 18;
			for (char c : replaceHtml(StringEscapeUtils.unescapeHtml4(str)).toCharArray()) {
				currentLength += String.valueOf(c).getBytes("GBK").length;
				if (currentLength >= subLength) {
					ccon++;
					subLength = ccon * ccon2;
					sb.append(c+"<br/>");
				} else {
					sb.append(c);
				}
			}
			return sb.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	
	public static String dellastdot(String str)
	{
		str = str == null? "" :str;
		if(StringUtils.endsWith(str, ".."))
		{
			str = StringUtils.substring(str, 0, str.length()-2);
		}
		return str;
	}
	/**
	 * 随机选取子集
	 * @param <T>
	 * @param views
	 * @param max
	 * @return
	 */
	public static <T> List<T> randomList(List<T> views, int max) {

	    final int size = views.size();
	    Random random = new Random();
	    int index = random.nextInt(size);
	    //
	    List<T> ret = new ArrayList<T>(max);
	    int low = index - 1, high = index;
	    while (max > 0 && (low >= 0 || high < size)) {
	        if (low >= 0 && max-- > 0) {
	            ret.add(views.get(low));
	        }
	        if (high < size && max-- > 0) {
	            ret.add(views.get(high));
	        }
	        low--;
	        high++;
	    }
	    return ret;
	}
	public static void main(String[] args) {
		//String str = "乐....";
		//System.out.println(StringUtils.dellastdot(str));
		List<String> list = Lists.newArrayList();
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		Random random = new Random();
		for(int i=0;i<10;i++){
			//System.out.println(random.nextInt(3));
			System.out.println(list.get(random.nextInt(list.size()-1)));
		}
	}
	
}
