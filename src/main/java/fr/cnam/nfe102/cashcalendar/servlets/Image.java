package fr.cnam.nfe102.cashcalendar.servlets;

import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
@WebServlet( name="Image", urlPatterns = {"/imagePath"} )
public class Image extends HttpServlet {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
	{			
		doGet(request, response);		
	}
 
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	{	
		try {
			HttpSession session = request.getSession();
 
			DataOutput output = new DataOutputStream( response.getOutputStream() );
			response.setContentType("image/jpg");
 
			String nomImage = request.getParameter("ImageName");
 
			File file = null;
 
			FileInputStream in = null;			
 
			//String filePath = "C:\\PHP\\images\\"+nomImage /*+".jpg"*/;
			String filePath = "/home/sedira/cashcalendar/images/"+nomImage /*+".jpg"*/;
 
 
			file = new File(filePath);
 
			/*
			 * Dans le cas ou l'image n'est pas présente dans le répertoire
			 * On affiche une image par defaut 'Image Introuuvable'
			 */
			if(!file.exists()) {
				//String path = session.getServletContext().getRealPath("") + "/img/imageIntrouvable.jpg" + file = new File(path);
				String path = session.getServletContext() + "imageIntrouvable.jpg";
				file = new File(path);
			}
			
			in = new FileInputStream(file);
 
			response.setContentLength((int)file.length());
 
			byte buffer[]=new byte[4096];
			int nbLecture;
 
			while( (nbLecture = in.read(buffer)) != -1 )
			{				
				output.write(buffer,0,nbLecture);					
			}	
 
			in.close();
 
		} catch (IOException e) {
		}		
	}	
}