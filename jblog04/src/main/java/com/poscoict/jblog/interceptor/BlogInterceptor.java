package com.poscoict.jblog.interceptor;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.poscoict.jblog.service.BlogService;
import com.poscoict.jblog.vo.BlogVo;

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
		
		String id = pathVariables.get("id");
		BlogVo blogVo = blogService.getBlog(id);
		
		if (blogVo == null) {
			response.sendRedirect(request.getContextPath());
			return false;
		}
		
		if (session.getAttribute("blogVo") == null) {
			session.setAttribute("blogVo", blogVo);
			return true;
		}
		
		return true;
	}

}
