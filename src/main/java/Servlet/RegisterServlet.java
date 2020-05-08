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

public class RegisterServlet extends HttpServlet {
        @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
            try (PrintWriter out = response.getWriter()) {
                request.getRequestDispatcher("link.html").include(request, response);
                
                String name=request.getParameter("name");
                String password=request.getParameter("password");
                
                double x = Math.random();
                String salt = Hash.generateSalt((int)x*100);
                System.out.println(salt);
                
                int hashedPsw = hash(salt + password);
                System.out.println(hashedPsw);
                
                DAO dao = new DAO();
                User utente = new User();
                List<User> listaUtenti = dao.getUserDao().findByName(name);
                if(!listaUtenti.isEmpty()){
                    out.print("Registration error! Existing user, change username!"); 
                    request.getRequestDispatcher("registration.html").include(request, response);
                }else{
                    utente.setUsername(name);
                    utente.setPassword(hashedPsw);
                    utente.setSalt(salt);
                    dao.getUserDao().insertUtente(utente);
                    out.print("Registration completed! " + name); 
                    request.getRequestDispatcher("login.html").include(request, response);
                }
            }
	}
        
        

}
