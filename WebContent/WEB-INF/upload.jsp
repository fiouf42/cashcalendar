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
        <form action="<c:url value="/upload" />" method="post" enctype="multipart/form-data">
            <fieldset>
                <legend>Envoi de fichier</legend>

                <label for="description">Description du fichier</label>
                <input type="text" id="description" name="description" value="<c:out value="${fichier.description}"/>" />
                <span class="erreur">${form.erreurs['description']}</span>
                <br />
                

                <label for="fichier">Emplacement du fichier <span class="requis">*</span></label>
                <input type="file" id="fichier" name="fichier" value="<c:out value="${fichier.nom}"/>" />
                <span class="erreur">${form.erreurs['fichier']}</span>
                <br />
                
                <input type="submit" value="Envoyer" class="sansLabel" />
                <br />
                
                <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>        
            </fieldset>
        </form>
        <%@ include file="piedDePage.jsp" %>
    </body>
</html>