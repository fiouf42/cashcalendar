package fr.cnam.nfe102.cashcalendar.dao;

import java.util.List;

import fr.cnam.nfe102.cashcalendar.modele.Utilisateur;

public interface IUtilisateurDAO {
	List<Utilisateur> listeUtilisateur();
	void creerUser(final Utilisateur pUser);
	String verifMail (final String mail);
	Utilisateur getFromEmail(String mail);
    // Renvoie l'utilisateur poss�dant le login et le mot de passe pass�s en param�tres
    public abstract Utilisateur connexionUser(String login, String motDePasse);
}
