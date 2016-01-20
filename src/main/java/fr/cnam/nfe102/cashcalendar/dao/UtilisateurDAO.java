package fr.cnam.nfe102.cashcalendar.dao;

import java.util.List;

import org.hibernate.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import fr.cnam.nfe102.cashcalendar.modele.Utilisateur;
import fr.cnam.nfe102.cashcalendar.utils.HibernateUtil;

public class UtilisateurDAO implements IUtilisateurDAO {
	Session s = null;

	// Renvoie l'utilisateur poss�dant le mail pass� en param�tre
	public Utilisateur getFromEmail(String mail) {
	      // R�cup�ration d'une session Hibernate
	      if(s == null)
	            s = HibernateUtil.getSessionFactory().openSession();
	
	      // Requ�te de r�cup�ration du Utilisateur correspondant au email donn�
	      Query q = s.createQuery("from Utilisateur u where u.mail=:mail")
	             .setString("mail", mail);
	
	      // R�cup�ration et renvoi du r�sultat unique
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
	      // R�cup�ration d'une session Hibernate
	      if(s == null)
	            s =  HibernateUtil.getSessionFactory().openSession();
	      
	      // D�but de la transaction Hibernate
	      Transaction tx = s.beginTransaction();
	
	      // Requ�te de r�cup�ration du Utilisateur correspondant au email donn�
	      Query q = s.createQuery("from Utilisateur u where u.mail=:mail")
	             .setString("mail", mail);
	
	      // R�cup�ration et renvoi du r�sultat unique
	      if (q.uniqueResult() != null) {
	    	  message = "Utilisateur d�j� existant !";
	      }
	      
	      tx.commit();
	      
	      return message;
	}


	@Override
    public Utilisateur connexionUser(String mail, String motDePasse) {
        // R�cup�ration d'une session Hibernate
        if(s == null)
              s =  HibernateUtil.getSessionFactory().openSession();
        
	    // D�but de la transaction Hibernate
	    Transaction tx = s.beginTransaction();

        // Requ�te de r�cup�ration du User correspondant au login et au motDePasse donn�
        Query q = s.createQuery("from Utilisateur u where u.mail= :mail and u.motDePasse= :motDePasse")
                    .setString("mail", mail).setString("motDePasse", motDePasse);

        // R�cup�ration et renvoi du r�sultat unique
        Utilisateur u = (Utilisateur)q.uniqueResult();
        
        tx.commit();
        
        return u;  
  }

}