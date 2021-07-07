<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="board" class="board-form">
				<table class="tbl-ex">
					<tr>
						<th colspan="2">글보기</th>
					</tr>
					<tr>
						<td class="label">제목</td>
						<td>${board.title }</td>
					</tr>
					<tr>
						<td class="label">내용</td>
						<td>
							<div class="view-content">
								${board.contents }
							</div>
						</td>
					</tr>
				</table>
				<div class="bottom">
						<%-- <a href="${pageContext.request.contextPath}/board?a=reply&group_no=${board.groupNo}&order_no=${board.orderNo}&depth=${board.depth}">답글달기</a> --%>
					<c:if test="${!empty authUser.no}" >
						<form action="${pageContext.request.contextPath}/board/reply/${board.no}" method="post">
							<input type="hidden" name="group_no" value="${board.groupNo}">
							<input type="hidden" name="order_no" value="${board.orderNo}">
							<input type="hidden" name="depth" value="${board.depth}">
							<input type="submit" value ="답글쓰기">
						</form>
					</c:if>
					<a href="${pageContext.request.contextPath}/board/1">글목록</a>  <!-- 고치기(싫음) -->
				
					
					<!-- 세션이 있을경우 보여주기? -->
					<c:if test="${authUser.no eq board.userNo }" >
						<a href="${pageContext.request.contextPath}/board/modify/${board.no}">글수정</a>
					</c:if>
				</div>
			</div>
		</div>
<c:import url="/WEB-INF/views/includes/navigation.jsp"/>
<c:import url="/WEB-INF/views/includes/footer.jsp"/>			
	</div>
</body>
</html>