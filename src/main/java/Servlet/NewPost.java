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

public class NewPost extends HttpServlet {
        @Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		request.getRequestDispatcher("link.html").include(request, response);
                    
                if(request.getParameter("title").trim().equals("") || request.getParameter("content").trim().equals(""))
                {
                    out.println("Impossible to add the post. Title and text are missing"); 
                    request.getRequestDispatcher("post.html").include(request, response);
                    out.print("<a href=\"ShowPosts\" \">Show Post</a>");
		
                }
                else{
                    out.print("<a href=\"ShowPosts\" Show Post</a>");		
                    request.getRequestDispatcher("post.html").include(request, response);      
                    DAO dao = new DAO();
                    Post post = new Post();
                    post.setTitle(request.getParameter("title").trim());
                    post.setUndertitle(request.getParameter("subtitle").trim());
                   
                    LocalDateTime now = LocalDateTime.now(); 
                    post.setDataHour(Timestamp.valueOf(now));
                    
                    post.setContent(request.getParameter("content").trim());
                    dao.getPostDao().insertPost(post);
                    
                    out.println("Post added!");
                }    
        }
}

