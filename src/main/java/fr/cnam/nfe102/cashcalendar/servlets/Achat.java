package fr.cnam.nfe102.cashcalendar.servlets;

import java.io.IOException;
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
		//@WebInitParam(name="chemin", value="/PHP/images/")
	    @WebInitParam(name="chemin", value="/home/sedira/cashcalendar/images/")
	})
//@MultipartConfig(location="C:/PHP/images")
@MultipartConfig(location="/home/sedira/cashcalendar/images")
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
		
        /* R�cup�ration de la session depuis la requ�te */
        HttpSession session = request.getSession();
        
        CalendrierController form = null;
        
        boolean neRienFaire = false;
		
        if ( request.getParameter(ATT_SESSION_ID) != null ) {
        	session.setAttribute( ATT_SESSION_ID, request.getParameter(ATT_SESSION_ID));
            session.setAttribute( ATT_SESSION_NOM, request.getParameter(ATT_SESSION_NOM));
        }
		
		request.setAttribute(ATT_SESSION_ID, session.getAttribute(ATT_SESSION_ID));
		request.setAttribute(ATT_SESSION_NOM, session.getAttribute(ATT_SESSION_NOM));
		
        /*
         * Lecture du param�tre 'chemin' pass� � la servlet via la d�claration
         * dans le web.xml
         */
		
		
		//List list = Collections.list(request.getParameterNames());
		//Iterator iterator = list.iterator();
		//while (iterator.hasNext()) {
		//	//System.out.println("objet = "+iterator.next());
		//  System.out.println("objet = "+request.getParameter((String)iterator.next()));
		//}
		if (request.getParameter("bouton") != null && request.getParameter("bouton").equals("Acheter")) {
			neRienFaire = true;
		}
		
		if (!neRienFaire) {
	        String chemin = this.getServletConfig().getInitParameter( CHEMIN );
	        session.setAttribute(ATT_DICTON, request.getParameter("dicton"));
	        /* Pr�paration de l'objet formulaire */
	        form = new CalendrierController();
	
	        /* Traitement de la requ�te et r�cup�ration du bean en r�sultant */
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
