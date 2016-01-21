<nav class="navbar navbar-default navbar-fixed-top">
  <div class="container">
    <ul class="nav navbar-nav">
	    <li><a href="accueil">Calendrier</a></li>
	    <c:choose>
	    <c:when test="${!empty sessionScope.sessionUtilisateur}">
	    	<li><a href="deconnexion">Deconnexion</a></li>
	    </c:when>
		<c:otherwise>
			<li><a href="connexion" >Connexion</a></li>
			<li><a href="inscription" >Inscription</a></li>
		</c:otherwise>
		</c:choose>
    </ul>
  </div>
</nav>
<br/><br/><br/>
<c:if test="${!empty sessionScope.sessionUtilisateur}">
	<p class="succes">Vous êtes connecté(e) avec l'adresse : ${sessionScope.sessionUtilisateur.mail} Solde : ${sessionScope.sessionUtilisateur.solde}</p>
</c:if>