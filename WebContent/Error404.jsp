<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Page d'erreur 404</title>
		<link type="text/css" rel="stylesheet" href="<c:url value="/style/bootstrap.min.css"/>">
		<script type="text/javascript" src="<c:url value="/style/bootstrap.min.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/style/jquery.min.js"/>"></script>
    </head>
<body>
<%@ include file="WEB-INF/menu.jsp" %>
ERROR 404
<%@ include file="WEB-INF/piedDePage.jsp" %>
</body>
</html>