<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="board">
				<form id="search_form" action="${pageContext.request.contextPath }/board?a=search}" method="post">
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th style="text-align:left">제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>				
					<c:set var="count" value="${map.totalCount }"/>
					<c:forEach items="${list }" var="board" begin = "0" end = "${map.countBoard }" varStatus="status">
						<tr>
						<%-- <img src="${pageContext.servletContext.contextPath }/assets/images/reply.png"/> --%>
<%-- 							<td>${count-status.index }</td> --%>
							<td>${board.no}</td>
							<td style="text-align:left; padding-left: ${board.depth * 20 }px">
								<c:if test = "${board.depth >= 1 }">
									<img src="${pageContext.servletContext.contextPath }/assets/images/reply.png"/>
								</c:if>
								<a href="${pageContext.request.contextPath }/board?a=view&no=${board.no}">${board.title }</a>
							</td>
							<td>${board.userName }</td>
							<td>${board.hit }</td>
							<td>${board.regDate }</td>
							<c:if test="${authUser.no eq board.userNo }" >
								<td><a href="${pageContext.request.contextPath }/board?a=delete&no=${board.no }" class="del">삭제</a></td>
							</c:if>
						</tr>
					</c:forEach>
				</table>
				<div class="pager">
					<ul>
						<c:choose>
							<c:when test="${map.page <= 10 }">
								<li>◀</li>
							</c:when>
							<c:otherwise>
								<li><a href="/mysite02/board?page=${map.firstPageNo - 10}">◀</a></li>
							</c:otherwise>
							
							
						</c:choose>
							<c:forEach var ="pageNo" begin = "${map.firstPageNo }" end = "${map.lastPageNo }">
								<c:choose>
									<c:when test="${pageNo eq map.currentPageNo}">
										<li class="selected">${pageNo}</li>
									</c:when>
									<c:otherwise>
										<li><a href="/mysite02/board?page=${pageNo }">${pageNo }</a></li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						<c:choose>
						
						
							<c:when test="${map.firstPageNo + 10 >= map.totalPage}">
								<li>▶</li>
							</c:when>
							<c:otherwise>
								<li><a href="/mysite02/board?page=${map.firstPageNo + 10}">▶</a></li>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>					
				<!-- pager 추가 -->
				
				<!-- 세션이 있으면 ㄱ 없으면 로그인페이지 -->
				<div class="bottom">
					<a href="${pageContext.request.contextPath}/board?a=writeform" id="new-book">글쓰기</a>
				</div>				
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>