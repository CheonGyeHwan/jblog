package com.poscoict.jblog.interceptor;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.poscoict.jblog.service.BlogService;

public class BlogInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private HttpSession session;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Map<String, String> pathVariables = (Map<String, String>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		if (pathVariables == null) {
			return true;
		}
		
		if (pathVariables.get("id") == null) {
			return true;
		}
		
		if (session.getAttribute("blogVo") == null) {
			String id = pathVariables.get("id");
			
			session.setAttribute("blogVo", blogService.getBlog(id));
			return true;
		}
		
		return true;
	}

}
