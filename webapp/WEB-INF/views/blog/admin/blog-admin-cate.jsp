<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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

		<div id="content">
		
			<ul id="admin-menu" class="clearfix">
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/${ authUser.id }/admin/basic">기본설정</a></li>
				<li class="tabbtn selected"><a href="${pageContext.request.contextPath}/${ authUser.id }/admin/category">카테고리</a></li>
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/${ authUser.id }/admin/writeForm">글작성</a></li>
			</ul>
			<!-- //admin-menu -->
			
			<div id="admin-content">
			
				<table id="admin-cate-list">
					<colgroup>
						<col style="width: 50px;">
						<col style="width: 200px;">
						<col style="width: 100px;">
						<col>
						<col style="width: 50px;">
					</colgroup>
		      		<thead>
			      		<tr>
			      			<th>번호</th>
			      			<th>카테고리명</th>
			      			<th>포스트 수</th>
			      			<th>설명</th>
			      			<th>삭제</th>      			
			      		</tr>
		      		</thead>
		      		<tbody id="cateList">
		      			<!-- 리스트 영역 -->
						<!-- 리스트 영역 -->
					</tbody>
				</table>
      	
		      	<table id="admin-cate-add" >
		      		<colgroup>
						<col style="width: 100px;">
						<col style="">
					</colgroup>
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input type="text" name="name" value=""></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input type="text" name="desc" value=""></td>
		      		</tr>
		      	</table> 
			
				<div id="btnArea">
		      		<button id="btnAddCate" class="btn_l" type="submit" >카테고리추가</button>
		      	</div>
			
			</div>
			<!-- //admin-content -->
		</div>	
		<!-- //content -->
		
		
		<!-- 개인블로그 푸터 -->
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp" />
	
	
	</div>
	<!-- //wrap -->
</body>

<script type="text/javascript">

// 문서 레디(DOM TREE)
$(document).ready(function(){
	fetchList();
	
});

// // 문서 레디(DOM TREE) - 내부 코드 실행 함수
function fetchList(){
	$.ajax({
		url: "${pageContext.request.contextPath}/{authUser.id}/admin/category/list",
		type: "post",
		success: function(returnCateList) {
			for (var i = 0; i < returnCateList.length; i++) {
				render(returnCateList[i], 'down');
			};
			
		},
		error: function(XHR, status, error) {
			console.error(status + ' : ' + error);
		}
	});
};


// 카테고리 리스트 - HTML 삽입 함수
function render(categoryVo, type) {
	var str = '<tr id="tr-' + categoryVo.cateNo + '">'
			+		'<td>' + categoryVo.cateNo + '</td>'
			+		'<td>' + categoryVo.cateName + '</td>'
			+		'<td>' + categoryVo.postCount + '</td>'
			+		'<td>' + categoryVo.description + '</td>'
			+	    '<td class="text-center">'
			+	    	'<img class="btnCateDel" data-no="' + categoryVo.cateNo + '" src="${pageContext.request.contextPath}/assets/images/delete.jpg">'
			+	    '</td>'
			+ '</tr>'
	
	if (type === 'down') {
		$('#cateList').append(str);
	} else if (type === 'up') {
		$('#cateList').prepend(str);
	}
			
	$('.btnCateDel').css('cursor', 'pointer');
	$('.text-center').css('text-align', 'center');
}


// 카테고리 추가
$('#btnAddCate').on('click', function(){
	/*
	var cateName = $('[name="name"]').val();
	var description = $('[name="desc"]').val();
	*/
	var cateVo = {
			cateName: $('[name="name"]').val(),
			description: $('[name="desc"]').val()
	}
	
	$.ajax({
		url: "${pageContext.request.contextPath}/{authUser.id}/admin/category/add",
		type: "post",
		data: cateVo,
		success: function(categoryVo){
			render(categoryVo, 'up');
			
			$('[name="name"]').val('');
			$('[name="desc"]').val('');
		},
		error: function(XHR, status, error){
			console.error(status + ' : ' + error);
		}
	});
});


$('#cateList').on('click', '.btnCateDel', function(){
	
	var cateNo = $(this).data("no");
	
	$.ajax({
		url: "${pageContext.request.contextPath}/{authUser.id}/admin/category/delete",
		type: "post",
		data: { cateNo: cateNo },
		success: function(count){
			// count = 1 성공, count < 1 실패
			if (count === 1) {
				$('#tr-' + cateNo).remove();
			} else {
				alert('fail delete');
			}
		},
		error: function(XHR, status, error) {
			console.error(status + ' : ' + error);
		}
	});
});

</script>

</html>