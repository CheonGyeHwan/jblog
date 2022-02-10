package com.poscoict.jblog.security;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.poscoict.jblog.vo.UserVo;

public class AdminAuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//1. handler 종류 확인
		if(handler instanceof HandlerMethod == false) {
			return true;
		}
		
		//2. casting
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		
		//3. Handler Method의 @AdminAuth 받아오기
		AdminAuth adminAuth = handlerMethod.getMethodAnnotation(AdminAuth.class);
		
		//4. method에 @AdminAuth가 적용이 안되어 있는 경우
		if(adminAuth == null) {
			return true;
		}
		
		//5. 블로그 주인 ID 추출
		Map<String, String> pathVariables = (Map<String, String>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		String blogId = pathVariables.get("id");		
		
		//6. 인증
		HttpSession session = request.getSession();
		if(session == null) {
			response.sendRedirect(request.getContextPath() + "/" + blogId);
			return false;
		}
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			response.sendRedirect(request.getContextPath() + "/" + blogId);
			return false;
		}
		
		if (!authUser.getId().equals(blogId)) {
			response.sendRedirect(request.getContextPath() + "/" + blogId);
			return false;
		}
		
		return true;
	}
	
}
