package fr.cnam.nfe102.cashcalendar.services;

import java.util.List;

import fr.cnam.nfe102.cashcalendar.modele.Calendrier;
import fr.cnam.nfe102.cashcalendar.modele.Produit;
import fr.cnam.nfe102.cashcalendar.modele.Utilisateur;

public interface IServiceCalendrier {
	List<Calendrier> listeCalendrier();
	Produit passerCommande(final Utilisateur user, final int idCalendrier, final Produit produit);
}
