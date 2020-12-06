package com.customtable.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 处理跨域的问题
 * 
 * @author admin
 *
 */
@Component
public class OriginFilter implements Filter {

	Log logger = LogFactory.getLog(OriginFilter.class);

	private int second = 60 * 60;//

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("跨域拦截器已经启动");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletResponse resp = (HttpServletResponse) response;
		HttpServletRequest rest = (HttpServletRequest) request;
		String origin = null;
		if (null != rest && rest.getMethod().equals("OPTIONS")) {
			logger.info("收到预请求{" + rest.getHeader("Origin") + "}请求类型{" + rest.getMethod() + "}");
			resp.setStatus(HttpServletResponse.SC_OK);

		}
		origin = rest.getHeader("Origin");
		// 添加参数，允许任意domain访问
		resp.setHeader("Access-Control-Allow-Origin", origin);

		resp.setHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
		resp.setHeader("Access-Control-Max-Age", second+"");
		// 被注释都得 Access-Control-Allow-Headers 属性值在测试环境是可以使用的，镜像不可以
		resp.setHeader("Access-Control-Allow-Headers",
				"Accept,Content-Type,X-Requested-With,accept,Origin,Access-Control-Request-Method,Access-Control-Request-Headers,TOKEN,Authorization,token");//

		// 跨域允许携带Cookie 前端Ajax配合增加
		// xhrFields: {
		// withCredentials: true
		// } 属性 这里注意的是 如果允许跨域Cookie 则Access-Control-Allow-Origin不能为*
		resp.setHeader("Access-Control-Allow-Credentials", "true");

		resp.setCharacterEncoding("UTF-8");
		resp.setHeader("Access-Control-Expose-Headers", "*");// 响应客户端的头部
		// 允许携带Token 等等

		try {
			chain.doFilter(request, resp);

		} catch (Exception e) {
			response.setContentType("application/json; charset=utf-8");
			response.setCharacterEncoding("UTF-8");
			OutputStream out = resp.getOutputStream();
			String message = e.getMessage();
			out.write(message.getBytes("UTF-8"));
			out.flush();

		}

	}

	@Override
	public void destroy() {
		logger.info(" 跨域拦截器已经销毁");
	}

}
