<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">

<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>

</head>

<body>
	<div id="wrap">

		<!-- 개인블로그 해더 -->
		<c:import url="/WEB-INF/views/includes/blog-header.jsp" />
		
		<div id="content" class="clearfix">
			<div id="profilecate_area">
				<div id="profile">
					
					<c:choose>
      				<%-- 기본이미지 --%>
					<c:when test="${ blogInfo.logoFile eq 'spring-logo.jpg' }">
						<img id="proImg" src="${pageContext.request.contextPath}/assets/images/spring-logo.jpg">
					</c:when>
					<%-- 사용자업로드 이미지 --%>
					<c:otherwise>
						<img id="proImg" src="${ pageContext.request.contextPath }/jblog/upload/${ blogInfo.logoFile }" alt="${ blogInfo.userName }의 변경된 로고 이미지">
					</c:otherwise>
					</c:choose>
					
					<div id="nick">${ blogInfo.userName }(${ blogInfo.id })님</div>
				</div>
				<div id="cate">
					<div class="text-left">
						<strong>카테고리</strong>
					</div>
					<ul id="cateList" class="text-left">
					<c:forEach items="${ blogMainMap.categoryList }" var="cateVo">
						<li><a href="${ pageContext.request.contextPath }/${ blogInfo.id }?selectCateNo=${ cateVo.cateNo }">${ cateVo.cateName }</a></li>
					</c:forEach>
					</ul>
				</div>
			</div>
			<!-- profilecate_area -->
			
			<div id="post_area">
				
				<div id="postBox" class="clearfix">
						<div id="postTitle" class="text-left"><strong>${ blogMainMap.getPost.postTitle }</strong></div>
						<div id="postDate" class="text-left"><strong>${ blogMainMap.getPost.regDate }</strong></div>
						<div id="postNick">${ blogInfo.userName }(${ blogInfo.id })님</div>
				</div>
				<!-- //postBox -->
			
				<div id="post" >
					${ blogMainMap.getPost.postContent }
				</div>
				<!-- //post -->
				
				<c:choose>
				<c:when test="${ !empty blogMainMap.postList }">
					<div id="list">
						<div id="listTitle" class="text-left"><strong>카테고리의 글</strong></div>
						<table>
							<colgroup>
								<col style="">
								<col style="width: 20%;">
							</colgroup>
							
							<c:forEach items="${ blogMainMap.postList }" var="postVo" varStatus="status">
							
							<tr>
								<td class="text-left"><a href="${ pageContext.request.contextPath }/${ blogInfo.id }?selectCateNo=${ postVo.cateNo }&selectPostNo=${ postVo.postNo }">${ postVo.postTitle }</a></td>
								<td class="text-right">${ postVo.regDate }</td>
							</tr>
							
							</c:forEach>
							
						</table>
					</div>
				</c:when>
				
				<c:otherwise>
					<%-- 글이 없는 경우 --%>
					<div id="postBox" class="clearfix">
								<div id="postTitle" class="text-left"><strong>등록된 글이 없습니다.</strong></div>
								<div id="postDate" class="text-left"><strong></strong></div>
								<div id="postNick"></div>
					</div>
				    
					<div id="post" >
					</div>
				</c:otherwise>
				</c:choose>
				<!-- //list -->
			</div>
			<!-- //post_area -->
			
			
			
		</div>	
		<!-- //content -->
		<div class=></div>
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp" />
		
	
	
	</div>
	<!-- //wrap -->
</body>

<!-- 
<script type="text/javascript">

$(document).ready(function(){
	categoryList();
});

function categoryList(){
	$.ajax({
		url: "${pageContext.request.contextPath}/{" + blogInfo.id + "}/test",
		type: "post",
		
		success: function(returnCateList) {
			for (var i = 0; i < returnCateList.length; i++) {
				renderCategoryList(returnCateList[i]);
			};
		},
		error: function(XHR, status, error) {
			console.error(status + ' : ' + error);
		}
		
	});
};

function renderCategoryList(CateListVo) {
	var str = '<li><a href="${pageContext.request.contextPath}/{authUser.id}/.....">' + CateListVo.cateName + '</a></li>'
	
	$('#cateList').append(str);
};





</script>
 -->


</html>