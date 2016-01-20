package fr.cnam.nfe102.cashcalendar.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import fr.cnam.nfe102.cashcalendar.modele.Utilisateur;
import fr.cnam.nfe102.cashcalendar.utils.HibernateUtil;
import fr.cnam.nfe102.cashcalendar.modele.Calendrier;
import fr.cnam.nfe102.cashcalendar.modele.Produit;

public class CalendrierDAO implements ICalendrierDAO {
	Session s = null;
	
	@Override
	public List<Calendrier> findAll(){
        List<Calendrier> list_cust = null;
        s =  HibernateUtil.getSessionFactory().openSession();
        String sql = "FROM Calendrier";
        try{
            s.beginTransaction();
            list_cust = s.createQuery(sql).list();
            for (Calendrier cal : list_cust) {
            	Hibernate.initialize(cal.getProduit());
                Hibernate.initialize(cal.getUtilisateur());
                //or cp.getCustomer().getLoginName();
            }
            s.beginTransaction().commit();
        }catch(Exception e){
            s.beginTransaction().rollback();
        }
        return list_cust;
    }


	@Override
	public Produit passerCommande(Utilisateur user, int idCalendrier, Produit pProduit) {
    	
		s =  HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		
        Calendrier cal =  (Calendrier) s.load(Calendrier.class, idCalendrier);
        cal.setUtilisateur(user);
        cal.setProduit(pProduit);
        cal.setDisponible(false);
        s.merge(cal);
        //Utilisateur user =  (Utilisateur) session.load(Utilisateur.class, idUtilisateur);
		//session.save(pProduit);
		
		s.getTransaction().commit();
		
		return pProduit;
	}

}
