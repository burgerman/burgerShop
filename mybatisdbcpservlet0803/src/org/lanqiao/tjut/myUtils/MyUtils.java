
	

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
	 * ��request����ת��Ϊʵ��bean
	 * Ҫ��:���ؼ���name���Ե�ֵ����Ҫ��ʵ�����������һ��(��Сд����) �����ļ��ϴ�������
	 * @param request
	 *        request����
	 * @param clazzBean
	 *        ��ת����ʵ��������
	 * @param fileUploadPath
	 *        �ļ��ϴ�������·�� ����:"uploadfiles/stuImgs/" �������ļ��ϴ�������ֵnull
	 * @param filePrefix
	 *        �ϴ��ļ���ǰ׺�ļ�����ʽ���ļ�ǰ׺_UUID����룬�ļ���׺
	 *        �磺"admin_9d6ea285-09d9-4774-8cf3-7e898d7a0c53.png"
	 *        �������ļ��ϴ�������ֵnull
	 * @return ת��֮���ʵ��������                            
	 */
	
	public static <T> T convertRequest2Bean(HttpServletRequest request, Class<T> clazzBean,
	     String fileUploadPath, String filePrefix){
		T tb = null;
		//ע������ת����:request�����е����������ַ���ֵ���ʹ�ø�ת��������ת��ΪDate����
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
			//===============���ļ��ؼ���ı�������ת������==========//
			//��ȡ���еĲ�����
			Enumeration<String> strPNames = request.getParameterNames();
			//ѭ������������������ȡ���Ĳ���ֵ����ֵ��ʵ��bean
			if(strPNames != null){
				
				while (strPNames.hasMoreElements()){
					//ȡ����ǰ�Ĳ�����
					String pName = strPNames.nextElement();
					//��ȡ��Ԫ�ص�ֵ
					String pVale = request.getParameter(pName);
					//ʹ��beanUtils��ָ��ʵ��������ֵ����Խ��и�ֵ(ע����������Ҫ����ת������)
				    BeanUtils.setProperty(tb, pName, pVale);
				
				}
			}
			
			
			//==========�ļ��ؼ���ת�������ļ��ϴ�����=================//
			//�ж��ñ����Ƿ���Ҫ�����ļ�����
			/*
			 * �ж�����
			 * request�����е�ContentType����ΪNull;
			 * ContentType�б������multipart/form-data ����ֵ
			 * 
			 */
			 
			if(request.getContentType() != null
					&& request.getContentType().split(";")[0].equals("multipart/form-data")){
				
				//�����ļ��ϴ�����
				//��ȡ���е�part����:������ͨ�ؼ�������ļ��ؼ�����
				Collection<Part> parts = request.getParts();
				//���α��� ÿ��part�����ҳ��ļ����͵�part�����ļ��ϴ�����
				for(Part part : parts){
					//��ȡ�ļ��ؼ���name����ֵ
					String pName = part.getName();
					//��ȡMINE���ͣ���ͨ�ؼ��ĸ�������Null,�ļ��ϴ��ؼ������Բ���Null
					String pContentType = part.getContentType();
					//System.out.println("part-ContentType:" + pContentType);
					//�жϵ�ǰ��part�Ƿ�����ļ��ϴ�����
					if(pContentType !=null && !pContentType.equals("") && part.getSize()>0)
					{
						//�����ļ��ϴ�����
						//�����ļ�����part
						//��ȡ�ϴ��ļ����ļ���
						String strFileOldName = part.getSubmittedFileName();
						//��ȡ��վ�ĸ�Ŀ¼(����ڷ��������̷�)
						String realPath = request.getServletContext().getRealPath("/");
						
						//��֤��Ŀ¼�Ƿ��ڷ������ϴ��ڣ����������򴴽���Ŀ¼
						File f = new File(realPath + fileUploadPath);
						if (!f.exists()){
							f.mkdirs();
						}
						System.out.println("��ӡ�ϴ��ļ�Ŀ¼:" + f.getAbsolutePath());
						
						//��ȡ�ϴ��ļ��ĺ�׺��
						String strFileType = strFileOldName.substring(strFileOldName.lastIndexOf("."));
						//�����ϴ��ļ�����ʽ:ǰ׺_UUID������.�ļ���׺
						String strUploadFileName = filePrefix + "_" + UUID.randomUUID()+ strFileType;
						//�����ļ��ϴ�
						part.write(realPath + fileUploadPath + strUploadFileName);
						//�����ļ����·��
						BeanUtils.setProperty(tb, pName, fileUploadPath + strUploadFileName);
						
						}
					}
			}
		} catch(Exception e){
			
			//��ȡʵ�����쳣
			System.out.println("��ȡʵ�����쳣:" + e.getMessage());
			e.printStackTrace();
		}
		  return tb;
	}
	
	/*
	 * ���ַ���ת��ΪInteger����
	 * 
	 * @param strVal
	 *     ��ת�����ַ���
	 * @return ת�����Integer����
	 */
	
	public static Integer convertString2Integer(String strVal){
		if(strVal == null || strVal.equals("")){
			 return null;
		}else{
			return Integer.valueOf(strVal);
		}
	}
	
	
	/*
	 * ����������תΪָ����ʽ���ַ���
	 * 
	 * @param date 
	 *       ��ת������������
	 * @param strPattern
	 *       ���ڸ�ʽ��
	 * @return ת������ַ���(Ĭ�Ϸ��ص�ǰʱ����ַ���)
	 */
	
	
	public static String convertDate2String(Date date, String strPattern) {
		// ���ʱ�����Ϊnull�򣬷��ص�ǰʱ��

		if (date == null) {
			date = new Date();
		}
		// �����ʽ��Ϊ����ʹ��Ĭ�ϸ�ʽ

		if (strPattern == null || strPattern.equals("")) {
			// Ĭ�ϸ�ʽ��

			strPattern = "yyyy-MM-dd HH:mm:ss";
		}
		// ����һ�����ڶ���ת����

		SimpleDateFormat sdf = new SimpleDateFormat(strPattern);
		// ����ת��֮����ַ���

		return sdf.format(date);
	}

	/**

	 * ��ָ����ʽ���ַ���ת��Ϊ�������� (strPattern�ĸ�ʽ��ʾ��strDate�ĸ�ʽ)

	 * 

	 * @param strDate

	 *            �����ַ���

	 * @param strPattern

	 *            �����ַ����ĸ�ʽ

	 * @return ת��֮������ڶ���

	 */
	public static Date convertString2Date(String strDate, String strPattern) {
		// ���ʱ�����Ϊnull�򣬷��ص�ǰʱ��

		if (strDate == null || strDate.equals("")) {
			return new Date();
		}
		// �����ʽ��Ϊ����ʹ��Ĭ�ϸ�ʽ

		if (strPattern == null || strPattern.equals("")) {
			// Ĭ�ϸ�ʽ��

			strPattern = "yyyy-MM-dd HH:mm:ss";
		}
		// ����һ�����ڶ���ת����

		SimpleDateFormat sdf = new SimpleDateFormat(strPattern);
		// ����ת��֮���ʱ�����

		Date date = new Date();
		try {
			date = sdf.parse(strDate);
		} catch (ParseException e) {
			// ���÷������ַ���ת�����ڶ����쳣��

			
			e.printStackTrace();
		}
		return date;
	}
	
	/*
	 * ��ȡĳһ�����ʵ������
	 * 
	 * @param clazz
	 *     ��ȡʵ�������class
	 * @return ʵ������    
	 */
      
	public static <T> T getNewInstance(Class<T> clazz){
		T t =null;
       try{
    	   t = clazz.newInstance();
    	   
       }catch (Exception e){
    	   //���÷���:ʵ���������쳣
    	   
    	   System.out.println("���÷���:ʵ���������쳣:" + e.getMessage());
           e.printStackTrace();
       }
       return t;
	}
	
	
	/**

	 * ��Request����ת��Ϊʵ��bean 
	 * Ҫ�󣺱��ؼ���name���Ե�ֵ����Ҫ��ʵ�����������һ�������ִ�Сд�� �����ļ��ϴ�������

	 * 

	 * @param request

	 *            request����

	 * @param clazz

	 *            ��ת����ʵ��������

	 * @param uploadFilePath

	 *            �ļ��ϴ�������·�� ���磺"updatefiles/adminImgs/" �������ļ��ϴ�������ֵ null

	 * @param uploadFilePerfix

	 *            �ϴ��ļ���ǰ׺ �ļ�����ʽ���ļ�ǰ׺_UUID�����.�ļ���׺

	 *            ���磺"admin_9d6ea285-09d9-4774-8cf3-7e898d7a0c53.png"

	 *            �������ļ��ϴ�������ֵ null

	 * @return ת��֮���ʵ��������

	 */
	public static <T> T convertRequestParameter2Bean(HttpServletRequest request, Class<T> clazz, String uploadFilePath,
			String uploadFilePerfix) {
		// ����ֵ

		T tBean = null;
		/*

		 * ����ʹ��beanutilsʱ�����ַ���ת��Ϊ��������ʱ����Ҫ�����Զ����ת������Ҳ����Ҫ�Լ�дһ��ת����

		 */
		// ע���Զ������������ת����

		ConvertUtils.register(new DateTimeConverter(), Date.class);

		try {
			// ʹ�÷��䣬��ȡ��ת����ʵ�����ʵ������

			tBean = clazz.newInstance();
			/************** ������ͨ�������� begin ***************/
			// ��request��������л�ȡ���еĲ������ļ���(�������ļ��ϴ�����)

			Enumeration<String> enu = request.getParameterNames();
			// �������е�request�����еĲ�����

			while (enu.hasMoreElements()) {
				// ��ȡ��ǰ�����Ĳ�����

				String pName = enu.nextElement();
				// ͨ����������ȡ��Ӧ�Ĳ���ֵ

				String pValue = request.getParameter(pName);
				// �ѻ�ȡ���Ĳ���ֵ ��ֵ����Ӧ��ʵ���������

				Field pField = null;
				/*

				 * ʹ�÷��䣬��ȡ��ǰʵ��������������Ӧ�����Զ�����������Զ���û�У��򲻽��и�ֵ����

				 */
				try {
					pField = clazz.getDeclaredField(pName);
				} catch (NoSuchFieldException e) {
					// ��ʵ���������û�����nameֵ��Ӧ�����ԣ������ת���������������ԵĴ���

					continue;
				}
				// �����ȡ�������Զ�������и�ֵ����

				if (pField != null && pValue != null && !pValue.equals("")) {
					// ʹ��beanutils����request���������ֵ���Ƶ�ʵ�����Ӧ���ֵ�������

					/*

					 * BeanUtils.setProperty�÷�����ɵĹ����ǣ�

					 * ��tBeanʵ����������ΪpName�����Ը�ֵΪpValue

					 */
					BeanUtils.setProperty(tBean, pName, pValue);
				}
			}
			/************** ������ͨ�������� end ***************/
			/************** �����ļ��ϴ����� begin ***************/
			// �жϵ�ǰrequest�����Ƿ�����ļ��ϴ��������Ƿ�ʹ���� enctype="multipart/form-data"����

			/*

			 * Add & Modify by yuw @20170703 ��� request.getContentType() != null

			 * ���������� ��ֹajax�ύ��������û������ContentType������³����쳣

			 */
			if (request.getContentType() != null
					&& request.getContentType().split(";")[0].equals("multipart/form-data")) {
				// ��ȡ�ļ��ϴ����󼯺�

				Collection<Part> parts = request.getParts();
				// һ�δ���ÿһ���ļ��ϴ�����

				if (parts != null && parts.size() > 1) {
					for (Part part : parts) {
						// ��ȡ�ļ��ؼ���name���Ե�ֵ

						String pName = part.getName();
						// ��ȡ MINE���ͣ���ͨ�ؼ��ĸ������� null���ļ��ϴ��ؼ������Բ���null

						String pContentType = part.getContentType();
						// �жϵ�ǰ��part�Ƿ��ǽ����ļ��ϴ�����

						if (pContentType != null && !pContentType.equals("") && part.getSize() > 0) {
							// ���ļ��������ֽ������浽��������ָ��Ŀ¼��

							// ��ȡ��վ�ĸ�Ŀ¼������ڷ��������̷���

							String realPath = request.getServletContext().getRealPath("/");
							
							// ��ȡ�ļ��ϴ����ύ���ļ���

							String fileSubmitName = part.getSubmittedFileName();
							// ��ȡ�����ļ��ĺ�׺����

							String fileType = "."
									+ pContentType.substring(pContentType.lastIndexOf('/')).replace("/", "");
							// ������ϱ����ļ����ļ������ļ�ǰ׺_�����.�ļ���׺

							String fileSaveName = uploadFilePerfix + "_" + UUID.randomUUID().toString() + fileType;
							
							// ������ļ�·��������ڸ�·�����ļ�·������

							String fileSavePath = uploadFilePath + fileSaveName;
							// ����洢�ϴ��ļ����ļ��в����ڣ��򴴽��ļ���

							File f = new File(realPath + uploadFilePath);
							if (!f.exists()) {
								f.mkdirs();
							}
							// �ϴ��ļ�

							part.write(realPath + fileSavePath);
							// ���ļ������·����ֵ��beanʵ�������

							if (fileSavePath != null && !fileSavePath.equals("")) {
								BeanUtils.setProperty(tBean, pName, fileSavePath);
							}
						}
					}
				}
			}
			/************** �����ļ��ϴ����� end ***************/
		} catch (Exception e) {
			// Request����ת��Ϊʵ��bean�쳣

			
			e.printStackTrace();
		}

		// ����ֵ

		return tBean;
	}

	/**

	 * MD5�����㷨

	 * 

	 * @param strSrc

	 *            �����ܵ�����

	 * @return MD5��������

	 */
	public final static String MD5(String strSrc) {
		// ���ڼ��ܵ��ַ�

		char md5String[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			// ʹ��ƽ̨��Ĭ���ַ������� String ����Ϊ byte���У���������洢��һ���µ� byte������

			byte[] btInput = strSrc.getBytes();

			// ��ϢժҪ�ǰ�ȫ�ĵ����ϣ�����������������С�����ݣ�������̶����ȵĹ�ϣֵ��

			MessageDigest mdInst = MessageDigest.getInstance("MD5");

			// MessageDigest����ͨ��ʹ�� update�����������ݣ� ʹ��ָ����byte�������ժҪ

			mdInst.update(btInput);

			// ժҪ����֮��ͨ������digest����ִ�й�ϣ���㣬�������

			byte[] md = mdInst.digest();

			// ������ת����ʮ�����Ƶ��ַ�����ʽ

			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) { // i = 0

				byte byte0 = md[i]; // 95

				str[k++] = md5String[byte0 >>> 4 & 0xf]; // 5

				str[k++] = md5String[byte0 & 0xf]; // F

			}

			// ���ؾ������ܺ���ַ���

			return new String(str);

		} catch (Exception e) {
			return null;
		}
	}

	public static void main(String[] args) {
		System.out.println(MD5("user01"));
	}


}