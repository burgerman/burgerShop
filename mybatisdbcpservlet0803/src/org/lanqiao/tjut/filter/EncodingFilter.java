package org.lanqiao.tjut.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

     /*
	 *EncodingFilter 
	 */
	@WebFilter("/*")
	public class EncodingFilter implements Filter{
		
      /*
       * ��������ҵ���߼�����		
       */
		
		
		public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		    throws IOException, ServletException{
			//�������е�http�����е�request��response�ı����ʽ
			//���ñ���
			
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			System.out.println("��ǰҳ�����Ϊutf-8");
			
			//chain�ǹ������Ĵ��������󣬽�http�����е�request��response����������´��ݣ�ֱ����������
			
			//http���·��͵ı�Ҫ����
			chain.doFilter(request, response);
			
		}
         
		
		/*
		 * ʹ�����ò������г�ʼ������
		 */
		
		
		public void init(FilterConfig fConfig) throws ServletException{
			
			//��ʼ������
		}
		
		@Override
		public void destroy(){
			
		}
	}
	


