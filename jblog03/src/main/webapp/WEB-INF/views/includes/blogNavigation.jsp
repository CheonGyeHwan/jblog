<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="navigation">
	<h2>카테고리</h2>
	<ul>
		<c:forEach items='${category }' var='category'>
			<li><a href="${pageContext.request.contextPath}/${category.blogId }/${category.no }">${category.name }</a></li>
		</c:forEach>
	</ul>
</div>