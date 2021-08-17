<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${ pageContext.request.contextPath }/assets/css/jblog.css">


</head>
<body>
	<div id="center-content">
		<c:import url="/WEB-INF/views/includes/main-header.jsp" />
		<!-- 메인 해더 -->
		
		<div id="joinSucceess">
			<p class="welcome-message">
				<span> ${ joinUser.userName }(${ joinUser.id })님 환영합니다.<br>
					   회원가입 및 블로그가 성공적으로 만들어 졌습니다.
			    </span>
			<br><br>
			<a href="${ pageContext.request.contextPath }/user/loginForm">[로그인 하기]</a>
			</p>
		</div>
		
		<br>
		<br>
		
		<c:import url="/WEB-INF/views/includes/main-footer.jsp" />
		<!-- 메인 푸터  자리-->
		
	</div>
</body>
</html>
