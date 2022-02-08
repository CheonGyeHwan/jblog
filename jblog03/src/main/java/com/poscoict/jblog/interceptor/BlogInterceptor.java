package com.poscoict.jblog.interceptor;

import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.poscoict.jblog.service.BlogService;

public class BlogInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	BlogService blogService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		ServletContext servletContext = request.getServletContext();
		
		Map<String, String> pathVariables = (Map<String, String>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		if (pathVariables == null) {
			return true;
		}
		
		if (pathVariables.get("id") == null) {
			return true;
		}
		
		if (servletContext.getAttribute("blogVo") == null) {
			String id = pathVariables.get("id");
			
			servletContext.setAttribute("blogVo", blogService.getBlog(id));
			return true;
		}
		
		return true;
	}

}
