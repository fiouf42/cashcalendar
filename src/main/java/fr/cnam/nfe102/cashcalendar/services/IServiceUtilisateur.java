package fr.cnam.nfe102.cashcalendar.services;

import java.util.Date;
import java.util.List;

import fr.cnam.nfe102.cashcalendar.modele.Utilisateur;

public interface IServiceUtilisateur {
	List<Utilisateur> listeUtilisateur();
	String verifMail (final String mail);
	Utilisateur creerUtilisateur(final String mail, final String motDePasse, final String nom, /*final Date date,*/ final double solde);
	Utilisateur connectUser(String mail, String motDePasse);
}
