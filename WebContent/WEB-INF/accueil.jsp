<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Accueil</title>
		<link rel="stylesheet" href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"/>
		<script type="text/javascript" src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="webjars/jquery/2.2.0/js/jquery.min.js"></script>
	</head>
	<body>
	<%@ include file="menu.jsp" %>
		<c:choose>
		    <c:when test="${!empty sessionScope.sessionUtilisateur}">		        
				<table border="1">
				    <thead>
				        <tr>
				            <th>Jours</th>
				            <th>Disponible</th>
				            <th>Lien</th>
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
														<!--  --><img alt="source" width="200" height="150" src="<%=request.getContextPath()%>/imagePath?ImageName=${cal.getProduit().getDescriptionProduit()}">
														<!-- <img alt="source" width="200" height="150" src="<c:url value="${cal.getProduit().getDescriptionProduit()}" /> "/> -->
														<!-- <img alt="source" width="200" height="150" src="<c:url value="${pageContext.request.contextPath}" /> "/> -->
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