package com.sqe.shop.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularUtil {

	/**
	 * ��ȡ�ַ�
	 * 
	 * @return
	 */
	public static String cutContent(String reg, String value) {
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(value);
		String result = "";
		if (m.find()) {
			result = m.group();
		}
		return result;
	}

	/**
	 * ����html��ǩ��style��script ���ش��ı�
	 * 
	 * @param inputString
	 * @return
	 */
	public static String Html2Text(String inputString) {
		String htmlStr = inputString; // ��html��ǩ���ַ�
		String textStr = "";
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;
		java.util.regex.Pattern p_style;
		java.util.regex.Matcher m_style;
		java.util.regex.Pattern p_html;
		java.util.regex.Matcher m_html;
		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // ����script��������ʽ{��<script[^>]*?>[\\s\\S]*?<\\/script>
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // ����style��������ʽ{��<style[^>]*?>[\\s\\S]*?<\\/style>
			String regEx_html = "<[^>]+>"; // ����HTML��ǩ��������ʽ

			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // ����script��ǩ

			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // ����style��ǩ

			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // ����html��ǩ

			textStr = htmlStr;
		} catch (Exception e) {
			System.err.println("Html2Text: " + e.getMessage());
		}
		return textStr;
	}

	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("^\\d*(\\.\\d*)?");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}
	
	public static boolean isFloat(String str) {
		Pattern pattern = Pattern.compile("^\\d{1,9}(\\.\\d{0,2})?");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}
	
	public static boolean isPositiveInt(Integer str) {
		Pattern pattern = Pattern.compile("^\\d{1,9}$");
		Matcher isNum = pattern.matcher(str+"");
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) {

		System.out.println(isPositiveInt(12345678));
		System.out.println(isFloat("12345678912"));	
		
		/*String test = "<img height='231' alt='װ��ģʽ��ͼ1' width='528' src='http://dl.iteye.com/upload/picture/pic/44707/d591f98f-6ebd-39a2-8450-0fc94a0ef4e1.jpg' style='border:0px;font-family:helvetica, tahoma, arial, sans-serif;font-size:14px;line-height:25.2px;background-color:#ffffff;' />";
		String reg = "(?<=src=\")[\\S\\s]+?(?=\")";
		String b = cutContent(reg, test);
		System.out.println(b);*/
	}
	

}
