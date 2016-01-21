package fr.cnam.nfe102.cashcalendar.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.cnam.nfe102.cashcalendar.modele.Calendrier;
import fr.cnam.nfe102.cashcalendar.controller.CalendrierController;

@WebServlet( name="Accueil", urlPatterns = {"/accueil"} )
public class Accueil extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String ATT_CALENDRIER         = "calendrier";
    public static final String VUE              = "/WEB-INF/accueil.jsp";
    public static final String VUE_ACHAT              = "/WEB-INF/achat.jsp";
    
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
    	
        CalendrierController form = new CalendrierController();
        
        
        List<Calendrier> listCalendrier = form.listCalendrier();
        
        request.setAttribute( ATT_CALENDRIER, listCalendrier );
    	
        /* Affichage de la page de Accueil */
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher( VUE_ACHAT ).forward( request, response );
    }
}