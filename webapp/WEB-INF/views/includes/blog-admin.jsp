<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> %>

		<ul id="admin-menu" class="clearfix">
		<c:choose>
			<c:when test="">
				<li class="tabbtn selected"><a href="${pageContext.request.contextPath}/${ authUser.id }/admin/basic">기본설정</a></li>
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/${ authUser.id }/admin/category">카테고리</a></li>
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/${ authUser.id }/admin/writeForm">글작성</a></li>
			</c:when>
			<c:when test="">
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/${ authUser.id }/admin/basic">기본설정</a></li>
				<li class="tabbtn selected"><a href="${pageContext.request.contextPath}/${ authUser.id }/admin/category">카테고리</a></li>
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/${ authUser.id }/admin/writeForm">글작성</a></li>
			</c:when>
			<c:otherwise>
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/${ authUser.id }/admin/basic">기본설정</a></li>
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/${ authUser.id }/admin/category">카테고리</a></li>
				<li class="tabbtn selected"><a href="${pageContext.request.contextPath}/${ authUser.id }/admin/writeForm">글작성</a></li>
			</c:otherwise>
		</c:choose>
		</ul>