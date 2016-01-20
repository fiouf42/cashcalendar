package fr.cnam.nfe102.cashcalendar.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

//import org.hibernate.Session;

import fr.cnam.nfe102.cashcalendar.modele.Utilisateur;
//import fr.cnam.nfe102.cashcalendar.services.IServiceUtilisateur;
//import fr.cnam.nfe102.cashcalendar.utils.HibernateUtil;
import fr.cnam.nfe102.cashcalendar.services.ServiceUtilisateur;

public final class InscriptionController {
	
    private ServiceUtilisateur serviceUtilisateur;
	
    private static final String CHAMP_EMAIL  = "mail";
    private static final String CHAMP_PASS   = "motdepasse";
    private static final String CHAMP_CONF   = "confirmation";
    private static final String CHAMP_NOM    = "nom";

    private String              resultat;
    private Map<String, String> erreurs      = new HashMap<String, String>();

    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }
    
    public Utilisateur inscrireUtilisateur( HttpServletRequest request ) {
        String mail = getValeurChamp( request, CHAMP_EMAIL );
        String motDePasse = getValeurChamp( request, CHAMP_PASS );
        String confirmation = getValeurChamp( request, CHAMP_CONF );
        String nom = getValeurChamp( request, CHAMP_NOM );
        String message = null;

        Utilisateur utilisateur = null;
        serviceUtilisateur = new ServiceUtilisateur();
        
        //List<Utilisateur> listeUtilisateur = serviceUtilisateur.listeUtilisateur();

        try {
            validationEmail( mail );
        } catch ( Exception e ) {
            setErreur( CHAMP_EMAIL, e.getMessage() );
        }
        message = serviceUtilisateur.verifMail(mail);
        if (message != null) {
        	setErreur( CHAMP_EMAIL, message );
        }
        
        //utilisateur.setEmail( mail );

        try {
            validationMotsDePasse( motDePasse, confirmation );
        } catch ( Exception e ) {
            setErreur( CHAMP_PASS, e.getMessage() );
            setErreur( CHAMP_CONF, null );
        }
        //utilisateur.setMotDePasse( motDePasse );

        try {
            validationNom( nom );
        } catch ( Exception e ) {
            setErreur( CHAMP_NOM, e.getMessage() );
        }
        //utilisateur.setNom( nom );
        
        //serviceUtilisateur.listeUtilisateur();
        
        if ( erreurs.isEmpty() ) {
        	//try {
        	//utilisateur.setDateInscription(new Date());
            //utilisateur.setSolde(16.00);
            	
        	utilisateur = serviceUtilisateur.creerUtilisateur(mail, motDePasse, nom, /*new Date(),*/ 16.00);
            	
                //Session session = HibernateUtil.getSessionFactory().getCurrentSession();

                //session.beginTransaction();
                
                //session.save(utilisateur);

                //session.getTransaction().commit();
        	//} catch (Exception e) {
        		//resultat = "Échec de l'inscription.";
        	//}
    		resultat = "Succès de l'inscription.";
        } else {
    		resultat = "Échec de l'inscription.";
        }

        return utilisateur;
    }
    
    private void validationEmail( String mail ) throws Exception {
        if ( mail != null ) {
            if ( !mail.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
                throw new Exception( "Merci de saisir une adresse mail valide." );
            }
        } else {
            throw new Exception( "Merci de saisir une adresse mail." );
        }
        
        //Iterator<Utilisateur> iterator = listeUtilisateur.iterator();
        //while (iterator.hasNext()) {
        //	Utilisateur utilisateur = iterator.next();
        //}
    }

    private void validationMotsDePasse( String motDePasse, String confirmation ) throws Exception {
        if ( motDePasse != null && confirmation != null ) {
            if ( !motDePasse.equals( confirmation ) ) {
                throw new Exception( "Les mots de passe entrés sont différents, merci de les saisir à nouveau." );
            } else if ( motDePasse.length() < 3 ) {
                throw new Exception( "Les mots de passe doivent contenir au moins 3 caractères." );
            }
        } else {
            throw new Exception( "Merci de saisir et confirmer votre mot de passe." );
        }
    }

    private void validationNom( String nom ) throws Exception {
        if ( nom != null && nom.length() < 3 ) {
            throw new Exception( "Le nom d'utilisateur doit contenir au moins 3 caractères." );
        }
    }

    /*
     * Ajoute un message correspondant au champ spécifié à la map des erreurs.
     */
    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }

    /*
     * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
     * sinon.
     */
    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur.trim();
        }
    }
}