package fr.cnam.nfe102.cashcalendar.dao;

import java.util.List;

import fr.cnam.nfe102.cashcalendar.modele.Calendrier;
import fr.cnam.nfe102.cashcalendar.modele.Produit;
import fr.cnam.nfe102.cashcalendar.modele.Utilisateur;

public interface ICalendrierDAO {
	List<Calendrier> findAll();
	Produit passerCommande(final Utilisateur user, final int idCalendrier, final Produit pProduit);
}
