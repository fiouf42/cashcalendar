<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Envoi de fichier</title>
		<!-- <link type="text/css" rel="stylesheet" href="<c:url value="/style/form.css"/>" /> -->
		
		<link type="text/css" rel="stylesheet" href="<c:url value="/style/bootstrap.min.css"/>">
		<script type="text/javascript" src="<c:url value="/style/bootstrap.min.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/style/jquery.min.js"/>"></script>
    </head>
    <body>
    <%@ include file="menu.jsp" %>
	<p class="succes">On va acheter : ${nom} du calendrier ${idCalendrier}</p>
		<c:choose>
		<c:when test="${nom == 'image'}">
			
				<div class="container">
			      <form class="form-signin" method="post" action="achat" enctype="multipart/form-data">
			        <h2 class="form-signin-heading">Envoi de l'image</h2>
			        
			        <label for="fichier" class="sr-only">Emplacement du fichier</label>
			        <input type="file" id="fichier" class="form-control" name="fichier" value="<c:out value="${produit.nom}"/>" />
			        <span class="erreur">${form.erreurs['fichier']}</span>
			        
			        <button class="btn btn-lg btn-primary btn-block" value="Valider" name="validAchat" type="submit">Valider</button>
	                <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
			      </form>
			    </div>
			
		</c:when>
		<c:otherwise>
				<div class="container">
			      <form class="form-signin" method="post" action="achat">
			        <h2 class="form-signin-heading">Entrez le dicton</h2>
			        
			        <input type="text" id="dicton" class="form-control" placeholder="Dicton" name="dicton" required autofocus>
			        <span class="erreur">${form.erreurs['dicton']}</span>
			        
			        <button class="btn btn-lg btn-primary btn-block" value="Valider" name="validAchat" type="submit">Valider</button>
                
	                <span class="erreur">${form.erreurs['fichier']}</span>
	                <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
			      </form>
			    </div>
		</c:otherwise>
		</c:choose>
		<%@ include file="piedDePage.jsp" %>
    </body>
</html>