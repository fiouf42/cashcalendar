package fr.cnam.nfe102.cashcalendar.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import fr.cnam.nfe102.cashcalendar.modele.Calendrier;
import fr.cnam.nfe102.cashcalendar.modele.Produit;
import fr.cnam.nfe102.cashcalendar.modele.Utilisateur;
import fr.cnam.nfe102.cashcalendar.services.ServiceCalendrier;

public class CalendrierController {
	private ServiceCalendrier serviceCalendrier;
    private static final String CHAMP_FICHIER     = "fichier";
    private static final String ATT_SESSION_NOM     = "nom";
    private static final String ATT_DICTON     = "dicton";
    private static final String ATT_ACCUEIL     = "erreurAccueil";
    private static final String ATT_SESSION_ID     = "idCalendrier";
    private static final int    TAILLE_TAMPON     = 10240;                        // 10 ko
    public static final String ATT_SESSION_USER = "sessionUtilisateur";

    private String              resultat;
    private Map<String, String> erreurs           = new HashMap<String, String>();
	
	public List<Calendrier> listCalendrier() {
		serviceCalendrier = new ServiceCalendrier();
        return serviceCalendrier.findAll();
        
	}

	public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public Produit enregistrerFichier( HttpServletRequest request, String chemin ) {
        Produit produit = new Produit();
        HttpSession session = request.getSession();
        String typeProduit = null;
        String description = null;
        InputStream contenuFichier = null;
        Double prix = 0.00;

        Utilisateur user = (Utilisateur) session.getAttribute(ATT_SESSION_USER);
        
        typeProduit = session.getAttribute(ATT_SESSION_NOM).toString();
        
        if (typeProduit.equals("dicton")) {
        	description = session.getAttribute(ATT_DICTON).toString();
            try {
                validationDescription(description);
                prix = 2.00;
            } catch ( Exception e ) {
                setErreur( ATT_DICTON, e.getMessage() );
            }
        } else {
            //R�cup�ration du contenu du champ fichier du formulaire.
            try {
                Part part = request.getPart( CHAMP_FICHIER );
                
                if (!part.getContentType().startsWith("image")) {
                	//Si le fichier n'est pas une image
                	setErreur( CHAMP_FICHIER, "Ce n'est pas une image !." );
                }
                
                try {
					verifTaille(part.getSize());
				} catch (Exception e) {
					setErreur( ATT_ACCUEIL, e.getMessage() );
					resultat = e.getMessage();
					return produit;
				}
                
                
                //R�cup�ration du nom du fichier.
                description = getNomFichier( part );

                /*
                 * Si la m�thode a renvoy� quelque chose, il s'agit donc d'un
                 * champ de type fichier (input type="file").
                 */
                if ( description != null && !description.isEmpty() ) {
                    //r�cup�ration du nom du fichier sur IE
                    description = description.substring( description.lastIndexOf( '/' ) + 1 )
                            .substring( description.lastIndexOf( '\\' ) + 1 );

                    /* R�cup�ration du contenu du fichier */
                    contenuFichier = part.getInputStream();

                }
            } catch ( IllegalStateException e ) {
                //si taille des donn�es d�passe les limites d�finies dans <multipart-config>
                e.printStackTrace();
                setErreur( CHAMP_FICHIER, "Les donn�es envoy�es sont trop volumineuses." );
            } catch ( IOException e ) {
                //si erreur au niveau des r�pertoires de stockage survient (r�pertoire inexistant, droits d'acc�s insuffisants, etc.)
                e.printStackTrace();
                setErreur( CHAMP_FICHIER, "Erreur de configuration du serveur." );
            } catch ( ServletException e ) {
                //si requ�te n'est pas de type multipart/form-data
                e.printStackTrace();
                setErreur( CHAMP_FICHIER,
                        "Ce type de requ�te n'est pas support�, merci d'utiliser le formulaire pr�vu pour envoyer votre fichier." );
            }
            
            /* Si aucune erreur n'est survenue jusqu'� pr�sent */
            if ( erreurs.isEmpty() ) {
                /* Validation du champ fichier. */
                try {
                    validationFichier( description, contenuFichier );
                    prix = 5.00;
                } catch ( Exception e ) {
                    setErreur( CHAMP_FICHIER, e.getMessage() );
                }
            }

            /* Si aucune erreur n'est survenue jusqu'� pr�sent */
            if ( erreurs.isEmpty() ) {
                /* �criture du fichier sur le disque */
                try {
                    ecrireFichier( contenuFichier, description, chemin );
                } catch ( Exception e ) {
                    setErreur( CHAMP_FICHIER, "Erreur lors de l'�criture du fichier sur le disque." );
                }
            }
        }
        if ( erreurs.isEmpty() ) {
        	try {
				user.setSolde(verifSolde(user.getSolde(), prix));
			} catch (Exception e) {
				setErreur( ATT_ACCUEIL, e.getMessage() );
				resultat = e.getMessage();
				return produit;
			}
        }


        /* Initialisation du r�sultat global de la validation. */
        if ( erreurs.isEmpty() ) {
            resultat = "Le jour a bien �t� achet�.";

            
            
            produit.setNomProduit(typeProduit);
            produit.setDescriptionProduit(description);
            
            int idCalendrier = Integer.parseInt(session.getAttribute(ATT_SESSION_ID).toString());
            
            serviceCalendrier = new ServiceCalendrier();
            serviceCalendrier.passerCommande(user, idCalendrier, produit);
        } else {
            resultat = "�chec de l'achat du jour.";
        }

        return produit;
    }

    /*
     * Valide la description saisie.
     */
    private void validationDescription( String description ) throws Exception {
        if ( description != null ) {
            if ( description.length() > 101 || description.length() < 10 ) {
                throw new Exception( "Le dicton doit contenir au minimum 10 caract�res et au maximum 100 caract�res." );
            }
        } else {
            throw new Exception( "Merci d'entrer un dicton." );
        }
    }

    /*
     * Valide le fichier envoy�.
     */
    private void validationFichier( String description, InputStream contenuFichier ) throws Exception {
        if ( description == null || contenuFichier == null ) {
            throw new Exception( "Merci de s�lectionner un fichier � envoyer." );
        }
    }

    /*
     * Ajoute un message correspondant au champ sp�cifi� � la map des erreurs.
     */
    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }

    /*
     * M�thode utilitaire qui a pour unique but d'analyser l'en-t�te
     * "content-disposition", et de v�rifier si le param�tre "filename" y est
     * pr�sent. Si oui, alors le champ trait� est de type File et la m�thode
     * retourne son nom, sinon il s'agit d'un champ de formulaire classique et
     * la m�thode retourne null.
     */
    private static String getNomFichier( Part part ) {
        /* Boucle sur chacun des param�tres de l'en-t�te "content-disposition". */
        for ( String contentDisposition : part.getHeader( "content-disposition" ).split( ";" ) ) {
            /* Recherche de l'�ventuelle pr�sence du param�tre "filename". */
            if ( contentDisposition.trim().startsWith( "filename" ) ) {
                /*
                 * Si "filename" est pr�sent, alors renvoi de sa valeur,
                 * c'est-�-dire du nom de fichier sans guillemets.
                 */
                return contentDisposition.substring( contentDisposition.indexOf( '=' ) + 1 ).trim().replace( "\"", "" );
            }
        }
        /* Et pour terminer, si rien n'a �t� trouv�... */
        return null;
    }

    /*
     * M�thode utilitaire qui a pour but d'�crire le fichier pass� en param�tre
     * sur le disque, dans le r�pertoire donn� et avec le nom donn�.
     */
    private void ecrireFichier( InputStream contenu, String description, String chemin ) throws Exception {
        /* Pr�pare les flux. */
        BufferedInputStream entree = null;
        BufferedOutputStream sortie = null;
        try {
            /* Ouvre les flux. */
            entree = new BufferedInputStream( contenu, TAILLE_TAMPON );
            sortie = new BufferedOutputStream( new FileOutputStream( new File( chemin + description ) ),
                    TAILLE_TAMPON );

            /*
             * Lit le fichier re�u et �crit son contenu dans un fichier sur le
             * disque.
             */
            byte[] tampon = new byte[TAILLE_TAMPON];
            int longueur = 0;
            while ( ( longueur = entree.read( tampon ) ) > 0 ) {
                sortie.write( tampon, 0, longueur );
            }
        } finally {
            try {
                sortie.close();
            } catch ( IOException ignore ) {
            }
            try {
                entree.close();
            } catch ( IOException ignore ) {
            }
        }
    }
    
    //Methode qui verifie si l'utilisateur peut faire la transaction et renvoi le nouveau solde
    private void verifTaille(long size) throws Exception {
    	if (size > 7500) {
    		throw new Exception( "Votre images est trop lourde ! Taille max 75ko !" );
    	}    	
    }
    
    //Methode qui verifie si l'utilisateur peut faire la transaction et renvoi le nouveau solde
    private Double verifSolde(Double solde, Double prix) throws Exception {
    	Double nouveauSolde = 0.00;
    	
    	nouveauSolde = solde - prix;
    	if (nouveauSolde < 0) {
    		throw new Exception( "Vous n'avez pas assez d'argent pour faire cet achat !" );
    	}
    	
		return nouveauSolde;
    	
    }
	
}
