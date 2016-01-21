<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Inscription</title>
		<!-- <link type="text/css" rel="stylesheet" href="<c:url value="/style/form.css"/>" /> -->
		
		<link type="text/css" rel="stylesheet" href="<c:url value="/style/bootstrap.min.css"/>">
		<script type="text/javascript" src="<c:url value="/style/bootstrap.min.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/style/jquery.min.js"/>"></script>
    </head>
    <body>
    <%@ include file="menu.jsp" %>
				<div class="container">
			      <form class="form-signin" method="post" action="inscription">
			        <h2 class="form-signin-heading">Inscription</h2>
			        
			        <label for="inputEmail" class="sr-only">Adresse email</label>
			        <input type="email" id="inputEmail" class="form-control" placeholder="Email address" name="mail" required autofocus>
			        <span class="erreur">${form.erreurs['mail']}</span>
			        
			        
			        <label for="inputPassword" class="sr-only">Mot de passe</label>
			        <input type="password" id="inputPassword" class="form-control" placeholder="Password" name="motdepasse" required>
			        <span class="erreur">${form.erreurs['motdepasse']}</span>
			        
			        <label for="inputPassword" class="sr-only">Confirmation du mot de passe</label>
			        <input type="password" id="inputPassword" class="form-control" placeholder="Password" name="confirmation" required>
			        <span class="erreur">${form.erreurs['confirmation']}</span>
			        
			        <button class="btn btn-lg btn-primary btn-block" value="Inscription" type="submit">Inscription</button>
                
	                <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
	                
	        		<p>Déjà membre ? connectez vous <a href="connexion" >ici</a></p>
			      </form>
			    </div>
        <%@ include file="piedDePage.jsp" %>
    </body>
</html>