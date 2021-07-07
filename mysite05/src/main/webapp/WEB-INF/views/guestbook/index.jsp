<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>



		<div id="content">
			<div id="guestbook">
				<form action="${pageContext.request.contextPath}/guestbook/add" method="post">
					<input type="hidden" name="a" value="insert">
					<table>
						<tr>
							<td>이름</td><td><input type="text" name="name"></td>
							<td>비밀번호</td><td><input type="password" name="password"></td>
						</tr>
						<tr>
							<td colspan=4><textarea name="message" id="content"></textarea></td>
						</tr>
						<tr>
							<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
						</tr>
					</table>
				</form>
				
				<ul>
					<li>
						<c:set var="count" value="${fn:length(list) }"/> 	<!-- 리스트 길이 저장 -->
						<c:forEach items="${list }" var="guestbook" varStatus="status">	<!-- status가 몇 번쨰 도는건지 -->
						<table>
							<tr>
								<td>${guestbook.no }</td>
								<td>${count - status.index}</td>
								<td>${guestbook.name}</td>
								<td>${guestbook.regDate}</td>
								<td><a href="${pageContext.request.contextPath}/guestbook/delete/${guestbook.no}">삭제</a></td>
							</tr>
							<tr>
								<td colspan=4>${guestbook.message }</td>
							</tr>
						</table>
						<br>
						</c:forEach>
					</li>
				</ul>
				<p style="text-align:center">총 ${count }  개의 글 <p>
			</div>
		</div>

<c:import url="/WEB-INF/views/includes/navigation.jsp"/>
<c:import url="/WEB-INF/views/includes/footer.jsp"/>			
	</div>
</body>
</html>