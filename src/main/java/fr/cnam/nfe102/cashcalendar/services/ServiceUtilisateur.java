package fr.cnam.nfe102.cashcalendar.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

//import fr.cnam.nfe102.cashcalendar.dao.IUtilisateurDAO;
import fr.cnam.nfe102.cashcalendar.dao.UtilisateurDAO;
import fr.cnam.nfe102.cashcalendar.modele.Utilisateur;

public class ServiceUtilisateur implements IServiceUtilisateur {
	
	private UtilisateurDAO dao = new UtilisateurDAO();

	@Override
	public Utilisateur creerUtilisateur(String mail, String motDePasse, String nom, /*Date dateInscription,*/ double solde) {
		final Utilisateur utilisateur = new Utilisateur();
		
		utilisateur.setMail(mail);
		utilisateur.setMotDePasse(encrypteMD5(motDePasse));
		//utilisateur.setNom(nom);
		//utilisateur.setDateInscription(dateInscription);
		utilisateur.setSolde(solde);
		
		dao.creerUser(utilisateur);
		
		return utilisateur;
	}

	@Override
	public List<Utilisateur> listeUtilisateur() {
		List<Utilisateur> listeUtilisateur = dao.listeUtilisateur();
		return listeUtilisateur;
	}

	@Override
	public String verifMail(String mail) {
		String message = dao.verifMail(mail);
		return message;
	}
	
    // Récupération de l'utilisateur correspondant au couple mail/motDePasse donné
	@Override
    public Utilisateur connectUser(String mail, String motDePasse) {
		Utilisateur u = dao.connexionUser(mail, encrypteMD5(motDePasse));
          return u;
    }
	
	public String encrypteMD5(String motDePasse) {
		MessageDigest md = null;
		
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	    md.update(motDePasse.getBytes());

	    byte byteData[] = md.digest();

	    StringBuffer sb = new StringBuffer();
	    for (int i = 0; i < byteData.length; i++)
	        sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	    
	    return sb.toString();
	}

}
