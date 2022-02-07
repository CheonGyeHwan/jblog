<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	pageContext.setAttribute("newline", "\n");
%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/blogHeader.jsp"/>
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<h4>${postVo.title }</h4>
					<p>
						${fn:replace(postVo.contents, newline, "<br/>") }
					</p>
				</div>
				<ul class="blog-list">
					<c:forEach items='${postList }' var='postList'>
						<li><a href="${pageContext.request.contextPath}/${blogVo.blogId }/${categoryVo.no }/${postList.no }">${postList.title }</a><span>${postList.regDate }</span></li>
					</c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}${blogVo.logo }">
			</div>
		</div>

		<c:import url="/WEB-INF/views/includes/blogNavigation.jsp"/>
		<c:import url="/WEB-INF/views/includes/blogFooter.jsp"/>
	</div>
</body>
</html>