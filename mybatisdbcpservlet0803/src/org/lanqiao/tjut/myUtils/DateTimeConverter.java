package org.lanqiao.tjut.myUtils;

import java.text.DateFormat; 
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.apache.commons.beanutils.Converter;

public class DateTimeConverter implements Converter {
	// Ҫת����Ŀ���������͵ĸ�ʽ
	private static final String DATE = "yyyy-MM-dd";
	private static final String DATETIME = "yyyy-MM-dd HH:mm:ss";
	private static final String TIMESTAMP = "yyyy-MM-dd HH:mm:ss.SSS";

	@Override
	public <T> T convert(Class<T> type, Object value) {
		return toDate(type, value);
	}

	/**
	 * ���ַ���ת��Ϊ��Ӧ��ʽ��Date����
	 * 
	 * @param type
	 *            ����
	 * @param value
	 *            ת����ֵ
	 * @return Date����
	 */
	public static <T> T toDate(Class<T> type, Object value) {
		if (value == null || "".equals(value))
			return null;
		// �жϱ�ת���Ķ���ֵ�ǲ���String����
		if (value instanceof String) {
			// ��ȡת���Ķ�����ַ���
			String dateValue = value.toString().trim();
			// ��ת���ַ����ĳ���
			int length = dateValue.length();
			// �жϱ�ת���������ǲ���ʱ������
			if (type.equals(java.util.Date.class)) {
				try {
					DateFormat formatter = null;
					// ת��Ϊ��yyyy-MM-dd����ʽ��Date����
					if (length <= 10) {
						formatter = new SimpleDateFormat(DATE, new DateFormatSymbols(Locale.CHINA));
						return (T) formatter.parse(dateValue);
					}
					// ת��Ϊ��yyyy-MM-dd HH:mm:ss����ʽ��Date����
					if (length <= 19) {
						formatter = new SimpleDateFormat(DATETIME, new DateFormatSymbols(Locale.CHINA));
						return (T) formatter.parse(dateValue);
					}
					// ת��Ϊ��yyyy-MM-dd HH:mm:ss.SSS����ʽ��Date����
					if (length <= 23) {
						formatter = new SimpleDateFormat(TIMESTAMP, new DateFormatSymbols(Locale.CHINA));
						return (T) formatter.parse(dateValue);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return (T) value;
	}

}
