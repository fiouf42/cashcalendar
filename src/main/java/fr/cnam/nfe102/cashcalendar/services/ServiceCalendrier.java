package fr.cnam.nfe102.cashcalendar.services;

import java.util.List;

import fr.cnam.nfe102.cashcalendar.dao.CalendrierDAO;
//import fr.cnam.nfe102.cashcalendar.modele.AchatId;
import fr.cnam.nfe102.cashcalendar.modele.Calendrier;
import fr.cnam.nfe102.cashcalendar.modele.Produit;
import fr.cnam.nfe102.cashcalendar.modele.Utilisateur;

public class ServiceCalendrier implements IServiceCalendrier {
	
	private CalendrierDAO dao = new CalendrierDAO();
	
	@Override
	public List<Calendrier> findAll() {
		List<Calendrier> listCalendrier = dao.findAll();
		
		return listCalendrier;
	}

	@Override
	public Produit passerCommande(Utilisateur user, int idCalendrier, Produit produit) {
		Produit prd = dao.passerCommande(user, idCalendrier, produit);
		
		
		return prd;
	}
}
