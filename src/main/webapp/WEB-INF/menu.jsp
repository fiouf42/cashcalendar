<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<nav class="navbar navbar-default navbar-static-top">
  <div class="container">
    <ul class="nav navbar-nav">
	    <c:choose>
	    <c:when test="${!empty sessionScope.sessionUtilisateur}">
	    	<li><a href="deconnexion">Deconnexion</a></li>
	    </c:when>
		<c:otherwise>
			<li><a href="connexion" >Connexion</a></li>
		</c:otherwise>
		</c:choose>
	    <li><a href="accueil">Calendrier</a></li>
    </ul>
  </div>
</nav>
<c:choose>
<c:when test="${!empty sessionScope.sessionUtilisateur}">
	<p class="succes">Vous êtes connecté(e) avec l'adresse : ${sessionScope.sessionUtilisateur.mail} Solde : ${sessionScope.sessionUtilisateur.solde}</p>
</c:when>
</c:choose>