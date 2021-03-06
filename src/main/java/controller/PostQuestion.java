package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.QuestionDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.FacultyModel;
import model.QuestionModel;
import model.StudentModel;

/**
 * Servlet implementation class PostQuestion
 */
public class PostQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostQuestion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
           HttpSession session=request.getSession();
           ServletContext context=getServletContext();
           
           QuestionModel qm=new QuestionModel();
          
           String utype=(String)session.getAttribute("utype");
           StudentModel sm;
           FacultyModel fm;
           
           if(utype.equals("student"))
           { 
               sm=(StudentModel)session.getAttribute("userModel");
               qm.setUid(sm.getSid());
               qm.setUname(sm.getName());
           }
           
           else if(utype.equals("faculty"))
           { 
               fm=(FacultyModel)session.getAttribute("userModel");
               qm.setUid(fm.getFid());
               qm.setUname(fm.getName());
           }
           
           String tagstr[]=request.getParameter("tags").split(",");
           ArrayList<String> tags=new ArrayList<>(Arrays.asList(tagstr));
           
           
            System.out.println(request.getParameter("domain"));
            System.out.println(request.getParameter("askedquestion"));
            System.out.println(request.getParameter("tags"));
           
           qm.setDomain(Integer.parseInt(request.getParameter("domain")));
           qm.setQue((String)request.getParameter("askedquestion"));
           qm.setTags(tags);
           
           QuestionDao qd=new QuestionDao();
           qd.insertQuestion(qm, context, session);
           response.sendRedirect("MyFeed");
           
           
        }

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
