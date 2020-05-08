package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import dao.*;
import entity.*; 

import java.sql.Timestamp;
import java.time.LocalDateTime;    

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddPost extends HttpServlet {
        @Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		request.getRequestDispatcher("link.html").include(request, response);
                   
                HttpSession session=request.getSession(false);
		
                    request.getRequestDispatcher("post.html").include(request, response);      

                    DAO dao = new DAO();
                    Post post = new Post();
                    post.setTitle(request.getParameter("title"));
                    post.setUndertitle(request.getParameter("subtitle"));
                    
                    LocalDateTime now = LocalDateTime.now(); 
                    post.setDataHour(Timestamp.valueOf(now));
                    
                    post.setContent(request.getParameter("content"));
                    dao.getPostDao().insertPost(post);
                    
                    out.println("Post added correctly!");
                
               
	}

}
