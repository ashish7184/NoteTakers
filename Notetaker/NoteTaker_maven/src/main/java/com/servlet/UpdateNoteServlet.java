package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.entite.Note;
import com.helper.FactoryProvider;

/**
 * Servlet implementation class UpdateNoteServlet
 */
@WebServlet("/UpdateNoteServlet")
public class UpdateNoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateNoteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  try {
		 
		  String title=request.getParameter("title");
		  String content=request.getParameter("content");
		  int noteID=Integer.parseInt(request.getParameter("noteid").trim());
		  Session s=FactoryProvider.getFactory().openSession();
		  Transaction tx=s.beginTransaction();
		  Note note=s.get(Note.class, noteID);
		  note.setTitle(title);
		  note.setContent(content);
		  note.setAddedDate(new Date());
		  tx.commit();
		  s.close();
		  response.sendRedirect("All_Notes.jsp");
		  
	  }catch(Exception e) {
		  e.printStackTrace();
	  }
	}

}
