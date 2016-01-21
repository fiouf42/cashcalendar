<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Connexion</title>
		<!-- <link type="text/css" rel="stylesheet" href="<c:url value="/style/form.css"/>" /> -->
		
		<link type="text/css" rel="stylesheet" href="<c:url value="/style/bootstrap.min.css"/>">
		<script type="text/javascript" src="<c:url value="/style/bootstrap.min.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/style/jquery.min.js"/>"></script>
    </head>
    <body>
    <%@ include file="menu.jsp" %>
        <!-- <form method="post" action="connexion">-->
            <!-- <fieldset>-->
                <!-- <legend>Connexion</legend>-->
                <!-- <p>Vous pouvez vous connecter via ce formulaire.</p>-->
                
                <!-- <div class="input-group">-->
				  <!-- <span class="input-group-addon" id="basic-addon1">@</span> -->
				  <!-- <input type="email" id="email" name="email"  class="form-control" placeholder="Username" aria-describedby="basic-addon1" value="<c:out value="${utilisateur.email}"/>">-->
				<!-- </div> -->
				
				
				
				<div class="container">
			      <form class="form-signin" method="post" action="connexion">
			        <h2 class="form-signin-heading">Connexion</h2>
			        
			        <label for="inputEmail" class="sr-only">Adresse email</label>
			        <input type="email" id="inputEmail" class="form-control" placeholder="Email address" name="email" required autofocus>
			        <span class="erreur">${form.erreurs['email']}</span>
			        
			        
			        <label for="inputPassword" class="sr-only">Mot de passe</label>
			        <input type="password" id="inputPassword" class="form-control" placeholder="Password" name="motdepasse" required>
			        <span class="erreur">${form.erreurs['motdepasse']}</span>
			        
			        <button class="btn btn-lg btn-primary btn-block" value="Connexion" type="submit">Connexion</button>
                
	                <span class="erreur">${form.erreurs['general']}</span>
	                <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
	
	                <p>Pas encore membre ? inscrivez vous <a href="inscription" >ici</a></p>
			      </form>
			    </div>
			    
            <!-- </fieldset> -->
       <!--  </form>-->
        <%@ include file="piedDePage.jsp" %>
    </body>
</html>