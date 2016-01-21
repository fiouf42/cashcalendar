<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Accueil</title>
		<!-- <link type="text/css" rel="stylesheet" href="<c:url value="/style/form.css"/>" /> -->
		
		<link type="text/css" rel="stylesheet" href="<c:url value="/style/bootstrap.min.css"/>">
		<script type="text/javascript" src="<c:url value="/style/bootstrap.min.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/style/jquery.min.js"/>"></script>
	</head>
	<body>
	<%@ include file="menu.jsp" %>
		<c:choose>
		    <c:when test="${!empty sessionScope.sessionUtilisateur}">
				<table class = "table table-bordered">
				   <thead>
				      <tr>
				         <th>Jours</th>
				         <th>Disponible</th>
				         <th>Produit</th>
				      </tr>
				   </thead>
				   <tbody>
				   <c:forEach items="${calendrier}" var="cal">
				      <tr>
				         <td><c:out value="${cal.jour}"/></td>
				         <td><c:out value="${cal.disponible}"/></td>
				         <td>
						 <c:choose>
						 	<c:when test="${cal.disponible == true}">				            
								<form method="post" action="achat">
									<select id="nom" name="nom">
										<option>dicton
										<option>image
									</select>
									<input type="hidden" id="idCalendrier" name="idCalendrier" value="${cal.idCalendrier}" />
									<input title="un bouton" type="submit" name="bouton" value="Valider" />
								</form>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${cal.getProduit().getNomProduit() == 'image'}">	
										<img alt="source" width="200" height="150" src="<%=request.getContextPath()%>/imagePath?ImageName=${cal.getProduit().getDescriptionProduit()}">
									</c:when>
									<c:otherwise>
										<p><c:out value="${cal.getProduit().getDescriptionProduit()}"/></p>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
						</td>
				      </tr>
				      </c:forEach>
				   </tbody>
				</table>
		    </c:when>    
		    <c:otherwise>
		        <p>Merci de vous connecter <a href="connexion" >ici</a></p>
		        <br />
		    </c:otherwise>
		</c:choose>
		<%@ include file="piedDePage.jsp" %>
	</body>
</html>