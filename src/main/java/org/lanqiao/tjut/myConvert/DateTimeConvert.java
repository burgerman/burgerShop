package org.lanqiao.tjut.myConvert;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.core.convert.converter.Converter;

public class DateTimeConvert implements Converter<String, Date> {
	// 要转换的目标日期类型的格式
	private static final String DATE = "yyyy-MM-dd";
	private static final String DATETIME = "yyyy-MM-dd HH:mm:ss";
	private static final String TIMESTAMP = "yyyy-MM-dd HH:mm:ss.SSS";

	/*
	 * 形参 str 是被转化的类型; 函数返回值是转换之后的目标类型; 也即是将形参 str 转换为目标类型进行返回
	 */
	@Override
	public Date convert(String value) {

		return toDate(value);
	}

	/**
	 * 
	 * 将字符串转换为对应格式的Date对象
	 * 
	 * @param value
	 *            被转换的值
	 * @return Date对象
	 */
	private static Date toDate(String value) {
		Date date = null;
		if (value != null && !"".equals(value)) {

			// 判断被转换的对象值是不是String类型
			// 获取转换的对象的字符串
			String dateValue = value.trim();
			// 被转换字符串的长度
			int length = dateValue.length();
			// 判断被转换的类型是不是时间类型
			try {
				DateFormat formatter = null;
				// 转换为“yyyy-MM-dd”格式的Date对象
				if (length <= 10) {
					formatter = new SimpleDateFormat(DATE, new DateFormatSymbols(Locale.CHINA));
					date = formatter.parse(dateValue);
				}
				// 转换为“yyyy-MM-dd HH:mm:ss”格式的Date对象
				else if (length <= 19) {
					formatter = new SimpleDateFormat(DATETIME, new DateFormatSymbols(Locale.CHINA));
					date = formatter.parse(dateValue);
				}
				// 转换为“yyyy-MM-dd HH:mm:ss.SSS”格式的Date对象
				else if (length <= 23) {
					formatter = new SimpleDateFormat(TIMESTAMP, new DateFormatSymbols(Locale.CHINA));
					date = formatter.parse(dateValue);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return date;
	}
}
