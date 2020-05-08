package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import dao.*;
import entity.*; 

import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ShowPosts extends HttpServlet {
        @Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		request.getRequestDispatcher("link.html").include(request, response);
                
                
                
                DAO dao = new DAO();
                List<Post> listPost = dao.getPostDao().findAll();
                List<Comment> listComment = dao.getCommentDao().findAll();
                
                out.println("Post Table");

                for(Post p: listPost){
                    out.println(p.getTitle());
                    out.println(p.getUndertitle());
                    out.println(p.getDataHour().toString());
                    out.println(p.getContent());
                    out.println();
                    
                    HttpSession session=request.getSession(false);
                    if(session!=null){
                        String name=(String)session.getAttribute("name");
                        out.println("<form action=\"NewComment\" method=\"post\">");
                        out.println("<input type=\"hidden\" name=\"postId\" value=\""+p.getId()+"\" readonly>");
                        out.println("Insert comment: <input type=\"text\" name=\"comment\"> <input type=\"submit\" value=\"Send\">");
                        out.println("");
                        out.println("</form>");
                    }
                    String str = p.getId().toString();
                    for(Comment c: listComment){
                        if(c.getPostId().equals(str)){
                            out.println("<p>Comment: "+c.getComment()+ "<br>User: " +c.getUserId()+ "</p>");
                        }
                    }

                }       
                out.close();
                
	}

}
