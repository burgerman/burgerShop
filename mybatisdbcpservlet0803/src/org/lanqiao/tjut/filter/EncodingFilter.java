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
       * 过滤器的业务逻辑处理		
       */
		
		
		public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		    throws IOException, ServletException{
			//设置所有的http请求中的request和response的编码格式
			//设置编码
			
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			System.out.println("当前页面编码为utf-8");
			
			//chain是过滤器的传递链对象，将http请求中的request和response对象继续向下传递，直到服务器端
			
			//http向下发送的必要条件
			chain.doFilter(request, response);
			
		}
         
		
		/*
		 * 使用配置参数进行初始化处理
		 */
		
		
		public void init(FilterConfig fConfig) throws ServletException{
			
			//初始化处理
		}
		
		@Override
		public void destroy(){
			
		}
	}
	


