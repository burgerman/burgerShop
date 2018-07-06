
	

 package org.lanqiao.tjut.myUtils;

import java.io.File; 
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.beanutils.converters.ByteConverter;
import org.apache.commons.beanutils.converters.DoubleConverter;
import org.apache.commons.beanutils.converters.FloatConverter;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.converters.LongConverter;
import org.apache.commons.beanutils.converters.ShortConverter;

public class MyUtils {
	/*
	 * 将request参数转换为实体bean
	 * 要求:表单控件的name属性的值必须要和实体类的属性名一样(大小写区分) 具有文件上传处理功能
	 * @param request
	 *        request对象
	 * @param clazzBean
	 *        被转换的实体类类型
	 * @param fileUploadPath
	 *        文件上传服务器路径 例如:"uploadfiles/stuImgs/" 不进行文件上传处理，赋值null
	 * @param filePrefix
	 *        上传文件的前缀文件名格式：文件前缀_UUID随机码，文件后缀
	 *        如："admin_9d6ea285-09d9-4774-8cf3-7e898d7a0c53.png"
	 *        不进行文件上传处理，赋值null
	 * @return 转换之后的实体类类型                            
	 */
	
	public static <T> T convertRequest2Bean(HttpServletRequest request, Class<T> clazzBean,
	     String fileUploadPath, String filePrefix){
		T tb = null;
		//注册日期转换器:request参数中的日期类型字符串值则会使用该转换器进行转换为Date类型
		ConvertUtils.register(new DateTimeConverter(), Date.class);
		
		ConvertUtils.register(new ByteConverter(null), Byte.class);
		ConvertUtils.register(new LongConverter(null), Long.class);
		ConvertUtils.register(new FloatConverter(null), Float.class);
		ConvertUtils.register(new ShortConverter(null), Short.class);
		ConvertUtils.register(new IntegerConverter(null), Integer.class);
		ConvertUtils.register(new DoubleConverter(null), Double.class);
		ConvertUtils.register(new BigDecimalConverter(null), BigDecimal.class);
		
		
		
		
		try{
			tb = clazzBean.newInstance();
			//===============除文件控件外的表单参数的转换处理==========//
			//获取所有的参数名
			Enumeration<String> strPNames = request.getParameterNames();
			//循环遍历参数名，将获取到的参数值，赋值给实体bean
			if(strPNames != null){
				
				while (strPNames.hasMoreElements()){
					//取出当前的参数名
					String pName = strPNames.nextElement();
					//获取该元素的值
					String pVale = request.getParameter(pName);
					//使用beanUtils给指定实体类的名字的属性进行赋值(注意日期型需要进行转换处理)
				    BeanUtils.setProperty(tb, pName, pVale);
				
				}
			}
			
			
			//==========文件控件表单转换处理及文件上传处理=================//
			//判定该表单中是否需要进行文件处理
			/*
			 * 判定条件
			 * request对象中的ContentType不能为Null;
			 * ContentType中必须包含multipart/form-data 属性值
			 * 
			 */
			 
			if(request.getContentType() != null
					&& request.getContentType().split(";")[0].equals("multipart/form-data")){
				
				//进行文件上传处理
				//获取所有的part对象:包含普通控件对象和文件控件对象
				Collection<Part> parts = request.getParts();
				//依次遍历 每个part对象，找出文件类型的part进行文件上传处理
				for(Part part : parts){
					//获取文件控件的name属性值
					String pName = part.getName();
					//获取MINE类型，普通控件的改属性是Null,文件上传控件的属性不是Null
					String pContentType = part.getContentType();
					//System.out.println("part-ContentType:" + pContentType);
					//判断当前的part是否进行文件上传处理
					if(pContentType !=null && !pContentType.equals("") && part.getSize()>0)
					{
						//进行文件上传处理
						//处理文件对象part
						//获取上传文件的文件名
						String strFileOldName = part.getSubmittedFileName();
						//获取网站的根目录(相对于服务器的盘符)
						String realPath = request.getServletContext().getRealPath("/");
						
						//验证该目录是否在服务器上存在，若不存在则创建该目录
						File f = new File(realPath + fileUploadPath);
						if (!f.exists()){
							f.mkdirs();
						}
						System.out.println("打印上传文件目录:" + f.getAbsolutePath());
						
						//获取上传文件的后缀名
						String strFileType = strFileOldName.substring(strFileOldName.lastIndexOf("."));
						//创建上传文件名格式:前缀_UUID随机编号.文件后缀
						String strUploadFileName = filePrefix + "_" + UUID.randomUUID()+ strFileType;
						//进行文件上传
						part.write(realPath + fileUploadPath + strUploadFileName);
						//保存文件相对路径
						BeanUtils.setProperty(tb, pName, fileUploadPath + strUploadFileName);
						
						}
					}
			}
		} catch(Exception e){
			
			//获取实体类异常
			System.out.println("获取实体类异常:" + e.getMessage());
			e.printStackTrace();
		}
		  return tb;
	}
	
	/*
	 * 将字符串转换为Integer类型
	 * 
	 * @param strVal
	 *     被转换的字符串
	 * @return 转换后的Integer对象
	 */
	
	public static Integer convertString2Integer(String strVal){
		if(strVal == null || strVal.equals("")){
			 return null;
		}else{
			return Integer.valueOf(strVal);
		}
	}
	
	
	/*
	 * 将日期类型转为指定格式的字符串
	 * 
	 * @param date 
	 *       被转换的日期类型
	 * @param strPattern
	 *       日期格式串
	 * @return 转换后的字符串(默认返回当前时间的字符串)
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

	 *            日期字符串的格式

	 * @return 转换之后的日期对象

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

			
			e.printStackTrace();
		}
		return date;
	}
	
	/*
	 * 获取某一个类的实例对象
	 * 
	 * @param clazz
	 *     获取实例对象的class
	 * @return 实例对象    
	 */
      
	public static <T> T getNewInstance(Class<T> clazz){
		T t =null;
       try{
    	   t = clazz.newInstance();
    	   
       }catch (Exception e){
    	   //公用方法:实例化对象异常
    	   
    	   System.out.println("公用方法:实例化对象异常:" + e.getMessage());
           e.printStackTrace();
       }
       return t;
	}
	
	
	/**

	 * 将Request参数转换为实体bean 
	 * 要求：表单控件的name属性的值必须要和实体类的属性名一样（区分大小写） 具有文件上传处理功能

	 * 

	 * @param request

	 *            request对象

	 * @param clazz

	 *            被转换的实体类类型

	 * @param uploadFilePath

	 *            文件上传服务器路径 例如："updatefiles/adminImgs/" 不进行文件上传处理，赋值 null

	 * @param uploadFilePerfix

	 *            上传文件的前缀 文件名格式：文件前缀_UUID随机码.文件后缀

	 *            例如："admin_9d6ea285-09d9-4774-8cf3-7e898d7a0c53.png"

	 *            不进行文件上传处理，赋值 null

	 * @return 转换之后的实体类类型

	 */
	public static <T> T convertRequestParameter2Bean(HttpServletRequest request, Class<T> clazz, String uploadFilePath,
			String uploadFilePerfix) {
		// 返回值

		T tBean = null;
		/*

		 * 由于使用beanutils时，从字符串转换为对象类型时，需要进行自定义的转换处理，也就是要自己写一个转换类

		 */
		// 注册自定义的日期类型转换器

		ConvertUtils.register(new DateTimeConverter(), Date.class);

		try {
			// 使用反射，获取被转换的实体类的实例变量

			tBean = clazz.newInstance();
			/************** 处理普通参数部分 begin ***************/
			// 从request请求对象中获取所有的参数名的集合(不包括文件上传对象)

			Enumeration<String> enu = request.getParameterNames();
			// 遍历所有的request请求中的参数名

			while (enu.hasMoreElements()) {
				// 获取当前遍历的参数名

				String pName = enu.nextElement();
				// 通过参数名获取对应的参数值

				String pValue = request.getParameter(pName);
				// 把获取到的参数值 赋值给对应的实体类的属性

				Field pField = null;
				/*

				 * 使用反射，获取当前实体类的与参数名对应的属性对象，如果该属性对象没有，则不进行赋值操作

				 */
				try {
					pField = clazz.getDeclaredField(pName);
				} catch (NoSuchFieldException e) {
					// 在实体类中如果没有与表单name值对应的属性，则忽略转换，继续后面属性的处理

					continue;
				}
				// 如果获取到了属性对象，则进行赋值操作

				if (pField != null && pValue != null && !pValue.equals("")) {
					// 使用beanutils将改request请求参数的值复制到实体类对应名字的属性上

					/*

					 * BeanUtils.setProperty该方法完成的工作是：

					 * 将tBean实体类中名字为pName的属性赋值为pValue

					 */
					BeanUtils.setProperty(tBean, pName, pValue);
				}
			}
			/************** 处理普通参数部分 end ***************/
			/************** 处理文件上传部分 begin ***************/
			// 判断当前request请求是否进行文件上传处理；表单是否使用了 enctype="multipart/form-data"属性

			/*

			 * Add & Modify by yuw @20170703 添加 request.getContentType() != null

			 * 过滤条件， 防止ajax提交的请求中没有设置ContentType的情况下出现异常

			 */
			if (request.getContentType() != null
					&& request.getContentType().split(";")[0].equals("multipart/form-data")) {
				// 获取文件上传对象集合

				Collection<Part> parts = request.getParts();
				// 一次处理每一个文件上传对象

				if (parts != null && parts.size() > 1) {
					for (Part part : parts) {
						// 获取文件控件的name属性的值

						String pName = part.getName();
						// 获取 MINE类型，普通控件的改属性是 null，文件上传控件的属性不是null

						String pContentType = part.getContentType();
						// 判断当前的part是否是进行文件上传处理

						if (pContentType != null && !pContentType.equals("") && part.getSize() > 0) {
							// 将文件二进制字节流保存到服务器的指定目录下

							// 获取网站的根目录（相对于服务器的盘符）

							String realPath = request.getServletContext().getRealPath("/");
							
							// 获取文件上传是提交的文件名

							String fileSubmitName = part.getSubmittedFileName();
							// 提取保存文件的后缀类型

							String fileType = "."
									+ pContentType.substring(pContentType.lastIndexOf('/')).replace("/", "");
							// 重新组合保存文件的文件名：文件前缀_随机码.文件后缀

							String fileSaveName = uploadFilePerfix + "_" + UUID.randomUUID().toString() + fileType;
							
							// 保存的文件路径（相对于根路径的文件路径名）

							String fileSavePath = uploadFilePath + fileSaveName;
							// 如果存储上传文件的文件夹不存在，则创建文件夹

							File f = new File(realPath + uploadFilePath);
							if (!f.exists()) {
								f.mkdirs();
							}
							// 上传文件

							part.write(realPath + fileSavePath);
							// 将文件保存的路径赋值给bean实体的属性

							if (fileSavePath != null && !fileSavePath.equals("")) {
								BeanUtils.setProperty(tBean, pName, fileSavePath);
							}
						}
					}
				}
			}
			/************** 处理文件上传部分 end ***************/
		} catch (Exception e) {
			// Request参数转换为实体bean异常

			
			e.printStackTrace();
		}

		// 返回值

		return tBean;
	}

	/**

	 * MD5加密算法

	 * 

	 * @param strSrc

	 *            被加密的内容

	 * @return MD5加密密文

	 */
	public final static String MD5(String strSrc) {
		// 用于加密的字符

		char md5String[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			// 使用平台的默认字符集将此 String 编码为 byte序列，并将结果存储到一个新的 byte数组中

			byte[] btInput = strSrc.getBytes();

			// 信息摘要是安全的单向哈希函数，它接收任意大小的数据，并输出固定长度的哈希值。

			MessageDigest mdInst = MessageDigest.getInstance("MD5");

			// MessageDigest对象通过使用 update方法处理数据， 使用指定的byte数组更新摘要

			mdInst.update(btInput);

			// 摘要更新之后，通过调用digest（）执行哈希计算，获得密文

			byte[] md = mdInst.digest();

			// 把密文转换成十六进制的字符串形式

			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) { // i = 0

				byte byte0 = md[i]; // 95

				str[k++] = md5String[byte0 >>> 4 & 0xf]; // 5

				str[k++] = md5String[byte0 & 0xf]; // F

			}

			// 返回经过加密后的字符串

			return new String(str);

		} catch (Exception e) {
			return null;
		}
	}

	public static void main(String[] args) {
		System.out.println(MD5("user01"));
	}


}