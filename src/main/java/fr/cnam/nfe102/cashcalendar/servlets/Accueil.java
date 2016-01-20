package fr.cnam.nfe102.cashcalendar.servlets;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.cnam.nfe102.cashcalendar.modele.Calendrier;
import fr.cnam.nfe102.cashcalendar.modele.Produit;
import fr.cnam.nfe102.cashcalendar.modele.Utilisateur;
import fr.cnam.nfe102.cashcalendar.controller.CalendrierController;

@WebServlet( name="Accueil", urlPatterns = {"/accueil"} )
public class Accueil extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String ATT_CALENDRIER         = "calendrier";
    public static final String ATT_FORM         = "form";
	public static final String ATT_NOM         = "nom";
    //public static final String ATT_SESSION_CALENDRIER = "sessionCalendrier";
    public static final String VUE              = "/WEB-INF/accueil.jsp";
    public static final String VUE_ACHAT              = "/WEB-INF/achat.jsp";

    // Properties ---------------------------------------------------------------------------------

    private String imagePath;

    // Init ---------------------------------------------------------------------------------------

    public void init() throws ServletException {

        // Define base path somehow. You can define it as init-param of the servlet.
        this.imagePath = "C:/PHP/images";

        // In a Windows environment with the Applicationserver running on the
        // c: volume, the above path is exactly the same as "c:\var\webapp\images".
        // In Linux/Mac/UNIX, it is just straightforward "/var/webapp/images".
    }
    
    //public String changePath(String path, HttpServletResponse response) {
        /************************************************
        // Get requested image by path info.
        String requestedImage = path;

        // Check if file name is actually supplied to the request URI.
        if (requestedImage == null) {
            // Do your thing if the image is not supplied to the request URI.
            // Throw an exception, or send 404, or show default/warning image, or just ignore it.
        	System.out.println("1");
            //response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            //return;
        }

        // Decode the file name (might contain spaces and on) and prepare file object.
        File image = null;
		try {
			image = new File(imagePath, URLDecoder.decode(requestedImage, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // Check if file actually exists in filesystem.
        if (!image.exists()) {
            // Do your thing if the file appears to be non-existing.
            // Throw an exception, or send 404, or show default/warning image, or just ignore it.
        	System.out.println("2");
            //response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
           return imagePath + File.separator + "file_not_found.png";
        }
//
        // Get content type by filename.
        String contentType = getServletContext().getMimeType(image.getName());

        // Check if file is actually an image (avoid download of other files by hackers!).
        // For all content types, see: http://www.w3schools.com/media/media_mimeref.asp
        if (contentType == null || !contentType.startsWith("image")) {
            // Do your thing if the file appears not being a real image.
            // Throw an exception, or send 404, or show default/warning image, or just ignore it.
        	System.out.println("3");
            //response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            //return;
        }

        // Init servlet response.
        //response.reset();
        //response.setContentType(contentType);
        //response.setHeader("Content-Length", String.valueOf(image.length()));

        //System.out.println("@@@@@@@@@@@@@@@@@@@ " + image.toPath());
        // Write image content to response.
        //Files.copy(image.toPath(), response.getOutputStream());
        return image.toPath().toString();*/
    //}
    
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
    	
        CalendrierController form = new CalendrierController();
        
        //HttpSession session = request.getSession();
        
        
        List<Calendrier> listCalendrier = form.listCalendrier();
        int index = -1;
        String path = null;
        
        Iterator iterator = listCalendrier.iterator();

       // while (iterator.hasNext()) {
        	//Calendrier cal = (Calendrier) iterator.next();
        	//index = listCalendrier.indexOf(cal);
        	//if (cal.getProduit() != null) {
	        	//System.out.println("AVANT " + cal.getJour() + " : " + cal.getProduit().getDescriptionProduit());
	        	
	        	//path = changePath(cal.getProduit().getDescriptionProduit(), response);
	        	
	        	//cal.getProduit().setDescriptionProduit(path);
	        	//System.out.println("APRES " + cal.getJour() + " : " + cal.getProduit().getDescriptionProduit());
	        	//listCalendrier.set(index, cal);
        	//}
        //}
        
        

        /*************************************************/
        request.setAttribute( ATT_CALENDRIER, listCalendrier );
    	
        /* Affichage de la page de Accueil */
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

    	System.out.println("on passi isisisisisi");
        this.getServletContext().getRequestDispatcher( VUE_ACHAT ).forward( request, response );
    }
}