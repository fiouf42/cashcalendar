<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Envoi de fichier</title>
		<link type="text/css" rel="stylesheet" href="webjars/bootstrap/3.3.6/css/bootstrap.min.css">
		<script type="text/javascript" src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="webjars/jquery/2.2.0/js/jquery.min.js"></script>
    </head>
    <body>
    <%@ include file="menu.jsp" %>
	<p class="succes">On va acheter : ${nom} du calendrier ${idCalendrier}</p>
		<c:choose>
		<c:when test="${nom == 'image'}">
			<form action="<c:url value="/achat" />" method="post" enctype="multipart/form-data">
				<fieldset>
					<legend>Envoi de fichier</legend>					

					<label for="fichier">Emplacement du fichier <span class="requis">*</span></label>
					<input type="file" id="fichier" name="fichier" value="<c:out value="${produit.nom}"/>" />
					<span class="erreur">${form.erreurs['fichier']}</span>
					<br />
					
					<input type="submit" name="validAchat" value="Valider" class="sansLabel" />
					<br />
					
					<p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>        
				</fieldset>
			</form>
		</c:when>
		<c:otherwise>
			<form method="post" action="achat">
				<fieldset>
					<input type="text" id="dicton" name="dicton" maxlength="100" />
					<input type="submit" name="validAchat" value="Valider" class="sansLabel" />
					<span class="erreur">${form.erreurs['dicton']}</span>
					<br />
					
					<p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>     
				</fieldset>
			</form>
		</c:otherwise>
		</c:choose>
		<%@ include file="piedDePage.jsp" %>
    </body>
</html>