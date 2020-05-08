package Servlet;

import dao.*;
import entity.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import static java.util.Objects.hash;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
        @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		request.getRequestDispatcher("link.html").include(request, response);
		String name=request.getParameter("name");
		String password=request.getParameter("password");
                
                DAO dao = new DAO();
                List<User> listaUser = dao.getUserDao().findByName(name);
                boolean realUser = false;
                for (User u : listaUser){
                    int hashedPsw = hash( u.getSalt() + password);
                           System.out.println(hashedPsw);
                    if(name.equals(u.getUsername()) && hashedPsw == u.getPassword()){
                            HttpSession session=request.getSession();
                            session.setAttribute("name",name);
                            realUser = true;
                            break;
                    }
                }
		if(realUser){
                    out.print("Welcome back" + name);
                }else{
                    if(listaUser.isEmpty()){    
                        out.println("Please register before login!");
                        request.getRequestDispatcher("registration.html").include(request, response);
                    }else{
                        out.println("Sorry, password error!");
                        request.getRequestDispatcher("login.html").include(request, response);
                    }
                }
		out.close();
        }
}
