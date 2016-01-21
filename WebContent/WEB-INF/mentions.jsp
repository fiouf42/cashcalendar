<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
		<!-- <link type="text/css" rel="stylesheet" href="<c:url value="/style/form.css"/>" /> -->
		
		<link type="text/css" rel="stylesheet" href="<c:url value="/style/bootstrap.min.css"/>">
		<script type="text/javascript" src="<c:url value="/style/bootstrap.min.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/style/jquery.min.js"/>"></script>
		<title>Mentions Légales</title>
	</head>
	<body>
	<%@ include file="menu.jsp" %>
	
		<p>Le site www.cnam-nfe102.com est édité par :</p>
		<p>FouadCorporation au capital de 3 000 000 000 €, immatriculée sous le numéro 123 456 789 au RCS de Caen, domiciliée 10 rue du jeu de paume à Saint-Etienne (42000)</p>
		<p>Directeur de la publication : Fouad Sedira, téléphone : 04 77 34 56 78</p>
		<p>Le contenu intégral de ce site est protégé par les lois en vigueur.</p>
		<p>La marque et les solutions CashCalendar sont protégées par les lois en vigueur sur la propriété intellectuelle (dépôt APP IDDN FR 001 500001 000 S P 2009 000 20900 en date du 7 décembre 2009 et IDDN FR 001 370010 000 R C 2014 000 30000 du 23 juin 2014, actualisé le 22 décembre 2014 et le 15 mai 2015) et industrielle (dépôt INPI Paris N°3552043 en date du 29 janvier 2008).</p>
		<p>Hébergeur : Localhost</p>
		<p>Les bases de données de CashCalendar sont déclarées à la CNIL sous les numéros 123456789 (données clients) et 987654321 (logs de connexion). Les utilisateurs disposent d'un droit de consultation qu'ils peuvent exercer en contactant directement CashCalendar à l'adresse : admin@CashCalendar.com</p>
	
	<%@ include file="piedDePage.jsp" %>
	</body>
</html>