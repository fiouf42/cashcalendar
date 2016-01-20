<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Connexion</title>
		<link type="text/css" rel="stylesheet" href="webjars/bootstrap/3.2.0/css/bootstrap.min.css"/>
    </head>
    <body>
    <%@ include file="menu.jsp" %>
        <form method="post" action="connexion">
            <fieldset>
                <legend>Connexion</legend>
                <p>Vous pouvez vous connecter via ce formulaire.</p>

                <label for="nom">Adresse email <span class="requis">*</span></label>
                <input type="email" id="email" name="email" value="<c:out value="${utilisateur.email}"/>" size="20" maxlength="60" />
                <span class="erreur">${form.erreurs['email']}</span>
                <br />

                <label for="motdepasse">Mot de passe <span class="requis">*</span></label>
                <input type="password" id="motdepasse" name="motdepasse" value="" size="20" maxlength="20" />
                <span class="erreur">${form.erreurs['motdepasse']}</span>
				<br />

                <input type="submit" value="Connexion" class="sansLabel" />
                <br />
                
                <span class="erreur">${form.erreurs['general']}</span>
                <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>

                <p>Pas encore membre ? inscrivez vous <a href="inscription" >ici</a></p>
            </fieldset>
        </form>
        <%@ include file="piedDePage.jsp" %>
    </body>
</html>