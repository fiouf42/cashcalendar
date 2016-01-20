<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Inscription</title>
		<link type="text/css" rel="stylesheet" href="webjars/bootstrap/3.3.6/css/bootstrap.min.css">
		<script type="text/javascript" src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="webjars/jquery/2.2.0/js/jquery.min.js"></script>
    </head>
    <body>
    <%@ include file="menu.jsp" %>
        <form method="post" action="inscription">
            <fieldset>
                <legend>Inscription</legend>
                <p>Vous pouvez vous inscrire via ce formulaire.</p>

                <label for="email">Adresse email <span class="requis">*</span></label>
                <input type="email" id="mail" name="mail" value="<c:out value="${utilisateur.mail}"/>" size="20" maxlength="60" />
                <span class="erreur">${form.erreurs['mail']}</span>
                <br />

                <label for="motdepasse">Mot de passe <span class="requis">*</span></label>
                <input type="password" id="motdepasse" name="motdepasse" value="" size="20" maxlength="20" />
                <span class="erreur">${form.erreurs['motdepasse']}</span>
                <br />

                <label for="confirmation">Confirmation du mot de passe <span class="requis">*</span></label>
                <input type="password" id="confirmation" name="confirmation" value="" size="20" maxlength="20" />
                <span class="erreur">${form.erreurs['confirmation']}</span>
                <br />

                <input type="submit" value="Inscription" class="sansLabel" />
                <br />
                
                <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
                
        		<p>Déjà membre ? connectez vous <a href="connexion" >ici</a></p>
            </fieldset>
        </form>
        <%@ include file="piedDePage.jsp" %>
    </body>
</html>