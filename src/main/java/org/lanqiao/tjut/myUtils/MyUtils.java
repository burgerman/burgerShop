package org.lanqiao.tjut.myUtils;

import java.io.File; 
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.beanutils.converters.ByteConverter;
import org.apache.commons.beanutils.converters.DoubleConverter;
import org.apache.commons.beanutils.converters.FloatConverter;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.converters.LongConverter;
import org.apache.commons.beanutils.converters.ShortConverter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class MyUtils {
	/**
	 * log4j鏃ュ織瀵硅薄
	 */
	public static Log logger = LogFactory.getLog("rootLogger");
	
	/**
	 * 将Request参数转换为实体bean 要求：表单控件的name属性的值必须要和实体类的属性名一样（区分大小写） 具有文件上传处理功能
	 * 
	 * @param request
	 *            request对象
	 * @param modelBean
	 *            保存文件上传路径的实体对象
	 * @param fileUploadPath
	 *            文件上传服务器路径 例如："uploadfiles/stuImgs/" 不进行文件上传处理，赋值 null
	 * @param filePrefix
	 *            上传文件的前缀 文件名格式：文件前缀_UUID随机码.文件后缀
	 *            例如："admin_9d6ea285-09d9-4774-8cf3-7e898d7a0c53.png"
	 *            不进行文件上传处理，赋值 null
	 * @return 转换之后的实体类类型
	 */
	public static <T> void uploadMultiFileByRequest(HttpServletRequest request, T modelBean, String fileUploadPath,
			String filePrefix) {
		// 注册日期转换器：request参数中的日期类型字符串值则会使用该转换器进行转换为Date类型
		ConvertUtils.register(new DateTimeConverter(), Date.class);
		/*
		 * 注册一下几个转换器：防止BeanUtils.setProperty()方法把null或者""的设值为0或者0.0
		 * 也就是：BeanUtils.setProperty()
		 * 给Integer、Short、Double、Float、Long、BigDecimal基本类型的值可以设置为null值，否则会设置默认值。
		 * 阻止BeanUtils.setProperty()方法把null值转换为默认值；
		 */
		ConvertUtils.register(new ByteConverter(null), Byte.class);
		ConvertUtils.register(new LongConverter(null), Long.class);
		ConvertUtils.register(new FloatConverter(null), Float.class);
		ConvertUtils.register(new ShortConverter(null), Short.class);
		ConvertUtils.register(new IntegerConverter(null), Integer.class);
		ConvertUtils.register(new DoubleConverter(null), Double.class);
		ConvertUtils.register(new BigDecimalConverter(null), BigDecimal.class);
		// 将当前上下文初始化给 CommonsMutipartResolver(MutipartResolver接口实现类)
		CommonsMultipartResolver cmr = new CommonsMultipartResolver(request.getSession().getServletContext());
		// 检查request发送的form中是否有 enctype="multipart/form-data" 类型的媒体内容
		if (cmr.isMultipart(request)) {
			// 文件上传的路径默认放在webapp的静态资源文件目录中  commentResources
			fileUploadPath = "commentResources/" + fileUploadPath;
			try {
				// 有文件数据流上传
				// 将request进行转换为MultipartRequest，然后进行处理
				MultipartRequest mr = (MultipartRequest) request;
				// 获取request中的所有的文件名
				Iterator<String> it = mr.getFileNames();
				// 获取文件 上传的服务器的路径地址
				// 获取网站的根目录（相对于服务器的盘符）
				String realPath = request.getSession().getServletContext().getRealPath("/");
				// 遍历处理这些文件
				while (it.hasNext()) {
					// 获取当前遍历的file控件的name属性值
					String fileName = it.next().toString();
					// 通过文件名从request中获取MultipartFile文件对象，进行处理
					MultipartFile file = mr.getFile(fileName);
					if (file != null && file.getSize() > 0) {
						// 获取当前上传的文件名
						String strFileOldName = file.getOriginalFilename();
						// 获取上传文件的后缀名
						String strFileType = strFileOldName.substring(strFileOldName.lastIndexOf('.'));
						// 创建上传文件名格式： 前缀_UUID随机编号.文件后缀
						String strUploadFileName = filePrefix + "_" + UUID.randomUUID() + strFileType;
						// 验证该目录是否在服务器上存在，如果不存在则创建该目录
						File f = new File(realPath + fileUploadPath);
						if (!f.exists()) {
							f.mkdirs();
						}
						logger.info("打印上传文件目录：" + f.getAbsolutePath());
						// 创建上传文件对象
						File fileUp = new File(realPath + fileUploadPath + strUploadFileName);
						// 上传文件
						file.transferTo(fileUp);

						// 保存上传文件的相对路径到实体类对应的属性中
						/*
						 * 上传文件的控件的name属性的命名要求：前缀 file_实体类属性名  ，
						 * 这样剔除前缀file_就是实体类的属性名，可以直接将上传文件的路径保存到对应的实体类的属性中
						 */
						String pName = fileName.replace("file_", "").trim();
						// 使用beanUtils给bean实体指定的属性名进行赋值
						BeanUtils.setProperty(modelBean, pName, fileUploadPath + strUploadFileName);
					}
				}
			} catch (Exception e) {
				// 文件上传相关操作异常
				logger.info("文件上传相关操作异常:" + e.getMessage());
			}
		}

	}

	/**
	 * 将字符串转换为Integer类型
	 * 
	 * @param strVal
	 *            被转换的字符串
	 * @return 转换之后的Integer对象
	 */
	public static Integer convertString2Integer(String strVal) {
		if (strVal == null || strVal.equals("")) {
			return null;
		} else {
			return Integer.valueOf(strVal);
		}
	}

	/**
	 * 将日期类型转为指定格式的字符串
	 * 
	 * @param date
	 *            被转换的日期类型
	 * @param strPattern
	 *            日期格式串
	 * @return 转换之后的字符串(默认返回当前时间的字符串)
	 * 
	 */
	public static String convertDate2String(Date date, String strPattern) {
		// 如果时间对象为null则，返回当前时间
		if (date == null) {
			date = new Date();
		}
		// 如果格式串为空则使用默认格式
		if (strPattern == null || strPattern.equals("")) {
			// 默认格式串
			strPattern = "yyyy-MM-dd HH:mm:ss";
		}
		// 创建一个日期对象转换类
		SimpleDateFormat sdf = new SimpleDateFormat(strPattern);
		// 返回转换之后的字符串
		return sdf.format(date);
	}

	/**
	 * 将指定格式的字符串转换为日期类型 (strPattern的格式表示是strDate的格式)
	 * 
	 * @param strDate
	 *            日期字符串
	 * @param strPattern
	 *            日期字符串的格式(描述的是strDate的格式)
	 * @return 转换之后的日期对象(默认返回当前时间)
	 */
	public static Date convertString2Date(String strDate, String strPattern) {
		// 如果时间对象为null则，返回当前时间

		if (strDate == null || strDate.equals("")) {
			return new Date();
		}
		// 如果格式串为空则使用默认格式
		if (strPattern == null || strPattern.equals("")) {
			// 默认格式串
			strPattern = "yyyy-MM-dd HH:mm:ss";
		}
		// 创建一个日期对象转换类
		SimpleDateFormat sdf = new SimpleDateFormat(strPattern);
		// 返回转换之后的时间对象
		Date date = new Date();
		try {
			date = sdf.parse(strDate);
		} catch (ParseException e) {
			// 公用方法：字符串转换日期对象异常：
						// logRootLogger.error("公用方法-字符串转换日期对象异常：" + e.getMessage());
			e.printStackTrace();
		}
		return date;
	}
	/**
	 * 获取某一个类的实例对象
	 * 
	 * @param clazz
	 *            获取实例对象的class
	 * @return 实例对象
	 */
	public static <T> T getNewInstance(Class<T> clazz) {
		T t = null;
		try {
			t = clazz.newInstance();
		} catch (Exception e) {
			// 公用方法：实例化对象异常
			System.out.println("公用方法：实例化对象异常:" + e.getMessage());
			e.printStackTrace();
		}
		return t;
	}

}
