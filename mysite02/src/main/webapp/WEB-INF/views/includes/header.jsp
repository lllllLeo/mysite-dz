<%@page import="com.douzone.mysite.vo.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
    
    <!-- 여기에는 이미 session이 있어서 request getSession안해도됨 -->
    
<%
	UserVO authUser = (UserVO)session.getAttribute("authUser");
%>
<div id="header">
	<h1>유준Site</h1>
	<ul>
		<c:choose>
			<c:when test="${empty authUser}">
				<li><a href="${pageContext.request.contextPath}/user?a=loginform">로그인</a><li>
				<li><a href="${pageContext.request.contextPath}/user?a=joinform">회원가입</a><li>	
			</c:when>
			<c:otherwise>
				<li><a href="${pageContext.request.contextPath}/user?a=updateform">회원정보수정</a><li>
				<li><a href="${pageContext.request.contextPath}/user?a=logout">로그아웃</a><li>
				<li> ${authUser.getName()} 아 안녕🤤🤣🙌😘😊😂😒😢😉👏😜👀🤔😆</li>	
			</c:otherwise>
		</c:choose>
	</ul>
</div>