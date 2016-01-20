package fr.cnam.nfe102.cashcalendar.dao;

import java.util.List;

import org.hibernate.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import fr.cnam.nfe102.cashcalendar.modele.Utilisateur;
import fr.cnam.nfe102.cashcalendar.utils.HibernateUtil;

public class UtilisateurDAO implements IUtilisateurDAO {
	Session s = null;

	// Renvoie l'utilisateur possédant le mail passé en paramètre
	public Utilisateur getFromEmail(String mail) {
	      // Récupération d'une session Hibernate
	      if(s == null)
	            s = HibernateUtil.getSessionFactory().openSession();
	
	      // Requête de récupération du Utilisateur correspondant au email donné
	      Query q = s.createQuery("from Utilisateur u where u.mail=:mail")
	             .setString("mail", mail);
	
	      // Récupération et renvoi du résultat unique
	      Utilisateur u = (Utilisateur)q.uniqueResult();
	      
	      return u;
	}

	public void creerUser(final Utilisateur pUser) {
	            	
		s =  HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.save(pUser);

		s.getTransaction().commit();
		//entityManager.persist(pUser);
	}
	            

	@Override
	public List<Utilisateur> listeUtilisateur() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String verifMail(String mail) {
		String message = null;
	      // Récupération d'une session Hibernate
	      if(s == null)
	            s =  HibernateUtil.getSessionFactory().openSession();
	      
	      // Début de la transaction Hibernate
	      Transaction tx = s.beginTransaction();
	
	      // Requête de récupération du Utilisateur correspondant au email donné
	      Query q = s.createQuery("from Utilisateur u where u.mail=:mail")
	             .setString("mail", mail);
	
	      // Récupération et renvoi du résultat unique
	      if (q.uniqueResult() != null) {
	    	  message = "Utilisateur déjà existant !";
	      }
	      
	      tx.commit();
	      
	      return message;
	}


	@Override
    public Utilisateur connexionUser(String mail, String motDePasse) {
        // Récupération d'une session Hibernate
        if(s == null)
              s =  HibernateUtil.getSessionFactory().openSession();
        
	    // Début de la transaction Hibernate
	    Transaction tx = s.beginTransaction();

        // Requête de récupération du User correspondant au login et au motDePasse donné
        Query q = s.createQuery("from Utilisateur u where u.mail= :mail and u.motDePasse= :motDePasse")
                    .setString("mail", mail).setString("motDePasse", motDePasse);

        // Récupération et renvoi du résultat unique
        Utilisateur u = (Utilisateur)q.uniqueResult();
        
        tx.commit();
        
        return u;  
  }

}