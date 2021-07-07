
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
			<div id="board">
				<form class="board-form" method="post" action="${pageContext.request.contextPath}/board/write?status=${status }">
					<input type="hidden" name="group_no" value="${map.groupNo}">
					<input type="hidden" name="order_no" value="${map.orderNo}">
					<input type="hidden" name="depth" value="${map.depth}">
					<table class="tbl-ex">
						<tr>
							<c:choose>
							<c:when test="${status ne 'reply' }">
								<th colspan="2">글쓰기</th>
							</c:when>
							<c:otherwise>
								<th colspan="2">답글쓰기</th>
							</c:otherwise>
							</c:choose>
						</tr>
						<tr>
							<td class="label">제목</td>
							<td><input type="text" name="title" value=""></td>
						</tr>
						<tr>
							<td class="label">내용</td>
							<td>
								<textarea id="content" name="contents"></textarea>
							</td>
						</tr>
					</table>
					<div class="bottom">
						<a href="${pageContext.request.contextPath}/board/1">취소</a>
<%-- 						<form action="${pageContext.request.contextPath}/board?a=write&no=${map.boardNo}" method="post"> --%>
							
						<input type="submit" value="등록">
					</div>
				</form>				
			</div>
		</div>
<c:import url="/WEB-INF/views/includes/navigation.jsp"/>
<c:import url="/WEB-INF/views/includes/footer.jsp"/>			
	</div>
</body>
</html>