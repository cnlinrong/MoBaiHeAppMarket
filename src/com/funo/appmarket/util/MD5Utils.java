package com.funo.appmarket.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {

	/**
	 * 将文本加密成32位的MD5
	 * 
	 * @param originalText
	 *            原始文本
	 * @param encoding
	 *            编码类型
	 * @return 加密文本
	 */
	public static String toMD5(String originalText, String encoding) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(originalText.getBytes(encoding));
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			// 转化成大写，是这次约定好的
			return buf.toString().toUpperCase();
			// System.out.println("result: " + buf.toString());// 32位的加密
			// System.out.println("result: " + buf.toString().substring(8,
			// 24));// 16位的加密
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将文本加密成32位GBK编码的MD5（针对亿阳单点登陆接口设计）
	 * 
	 * @param originalText
	 *            原始文本
	 * @return 加密文本
	 */
	public static String toMD5(String originalText) {
		return toMD5(originalText, "UTF-8");
	}
}
