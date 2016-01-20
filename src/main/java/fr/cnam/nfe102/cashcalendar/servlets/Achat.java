package fr.cnam.nfe102.cashcalendar.servlets;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.cnam.nfe102.cashcalendar.controller.CalendrierController;
import fr.cnam.nfe102.cashcalendar.modele.Calendrier;
import fr.cnam.nfe102.cashcalendar.modele.Produit;

/**
 * Servlet implementation class Achat
 */
@WebServlet( name="Achat", urlPatterns = {"/achat"}, initParams={
	    @WebInitParam(name="chemin", value="/PHP/images/")
	})
@MultipartConfig(location="C:/PHP/images", fileSizeThreshold=1024*1024, maxFileSize=1024*1024, maxRequestSize=1024*1024*5)
public class Achat extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String VUE = "/WEB-INF/achat.jsp";
	public static final String VUE_OK = "/WEB-INF/accueil.jsp";

	public static final String ATT_SESSION_ID         = "idCalendrier";
	public static final String ATT_SESSION_NOM         = "nom";
	public static final String ATT_CALENDRIER         = "calendrier";
	public static final String ATT_DICTON     = "dicton";

	public static final String CHEMIN      = "chemin";

    public static final String ATT_FICHIER = "fichier";
    public static final String ATT_FORM    = "form";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Achat() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();
        
        CalendrierController form = null;
		
        if ( request.getParameter(ATT_SESSION_ID) != null ) {
        	session.setAttribute( ATT_SESSION_ID, request.getParameter(ATT_SESSION_ID));
            session.setAttribute( ATT_SESSION_NOM, request.getParameter(ATT_SESSION_NOM));
        }
		
		request.setAttribute(ATT_SESSION_ID, session.getAttribute(ATT_SESSION_ID));
		request.setAttribute(ATT_SESSION_NOM, session.getAttribute(ATT_SESSION_NOM));
		
        /*
         * Lecture du paramètre 'chemin' passé à la servlet via la déclaration
         * dans le web.xml
         */
		
		System.out.println("MON FICHIER " + request.getParameter("fichier"));
		System.out.println("MON DICTON " + request.getParameter("dicton"));
		
		if (request.getParameter("validAchat") != null) {
			System.out.println("on passe maintenant !!");
	        String chemin = this.getServletConfig().getInitParameter( CHEMIN );
	        session.setAttribute(ATT_DICTON, request.getParameter("dicton"));
	        /* Préparation de l'objet formulaire */
	        form = new CalendrierController();
	
	        /* Traitement de la requête et récupération du bean en résultant */
	        Produit produit = form.enregistrerFichier( request, chemin );
	
	        /* Stockage du formulaire et du bean dans l'objet request */
	        request.setAttribute( ATT_FORM, form );
	        request.setAttribute( ATT_FICHIER, produit );
		}
		if (form != null && form.getErreurs().isEmpty()) {
			List<Calendrier> listCalendrier = form.listCalendrier();
			request.setAttribute( ATT_CALENDRIER, listCalendrier );
			this.getServletContext().getRequestDispatcher( VUE_OK ).forward( request, response );
		} else {
			this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
		}
	}

}
