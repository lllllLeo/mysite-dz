<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>${main.title }</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/assets/css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		
<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="wrapper">
			<div id="content">
				<div id="site-introduction">
<!-- 					<img id="profile" src="https://avatars.githubusercontent.com/u/38072322?v=4" style="width:200px"> -->
					<img id="profile" src="${pageContext.request.contextPath}/${main.profile }" style="width:200px">
					<%-- <img id="profile" src="${pageContext.request.contextPath}/assets/images/profile"> --%>
					<h2>${main.welcome }</h2>
					<p>${main.description }<br><br>
						<a href="${pageContext.request.contextPath}/guestbook?a=list">방명록</a>에 글 남기기<br>
					</p>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"/>
<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
	<script>
	var o = {
			"no": 4,
			"name": "管理人",
			"email": null,
			"password": null,
			"gender": null,
			"role": "ADMIN"
			}
	</script>
</body>
</html>