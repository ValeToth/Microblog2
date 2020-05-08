package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import dao.*;
import entity.*; 


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class NewComment extends HttpServlet {
        @Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		request.getRequestDispatcher("link.html").include(request, response);
                HttpSession session=request.getSession(false);
                
                DAO dao = new DAO();
                Comment comment = new Comment();
                
                comment.setComment(request.getParameter("comment").trim());
                comment.setPostId(request.getParameter("postId"));
                comment.setUserId((String) session.getAttribute("name"));
                dao.getCommentDao().insertComment(comment);
                
                out.println("Comment added!");
                out.print("<a href=\"ShowPosts\" \">See Post</a>");
			
        }
}

