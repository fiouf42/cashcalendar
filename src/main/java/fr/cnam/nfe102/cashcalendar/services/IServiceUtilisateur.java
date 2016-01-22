package fr.cnam.nfe102.cashcalendar.services;

import java.util.List;

import fr.cnam.nfe102.cashcalendar.modele.Utilisateur;

public interface IServiceUtilisateur {
	List<Utilisateur> listeUtilisateur();
	String verifMail (final String mail);
	Utilisateur creerUtilisateur(final String mail, final String motDePasse, final double solde);
	Utilisateur connecterUtilisateur(String mail, String motDePasse);
}
