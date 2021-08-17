<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

		<div id="header" class="clearfix">
			<h1><a href="${pageContext.request.contextPath}/${ blogInfo.id }">${ blogInfo.blogTitle }</a></h1>
			<ul class="clearfix">
			
			<c:choose>
				<%-- when: 로그인 전 메뉴 --%>
				<c:when test="${ authUser eq null }">
					<li><a class="btn_s" href="${pageContext.request.contextPath}/user/loginForm">로그인</a></li>
				</c:when>
				<%-- otherwise: 로그인 후 메뉴 --%>
				<c:otherwise>
					<c:choose>
					<%-- 자신의 블로그일때만 관리 메뉴가 보인다 --%>
					<c:when test="${ authUser.id eq blogInfo.id }">
						<li><a class="btn_s" href="${pageContext.request.contextPath}/${ authUser.id }/admin/basic">내 블로그 관리</a></li>
						<li><a class="btn_s" href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
					</c:when>
					<%-- 로그인은 했지만 다른 사람의 블로그인 경우 --%>
					<c:otherwise>
						<li><a class="btn_s" href="${pageContext.request.contextPath}/${ authUser.id }">내 블로그로 돌아가기</a></li>
						<li><a class="btn_s" href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
					</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>
			</ul>
		</div>
		<!-- //header -->
