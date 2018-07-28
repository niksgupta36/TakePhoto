package com.artigo.control;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CustLogin")
public class CustLogin extends HttpServlet { 
	private static final long serialVersionUID = 1L;
       
    public CustLogin() {
    	super();
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String adminusername = request.getParameter("custname");
		String adminpassword = request.getParameter("custpassword");
		String submittype = request.getParameter("submit");
		CustomerDAOImpl cust = new CustomerDAOImpl();
		
		String pwd = cust.logincheck(adminusername);
		if(adminpassword.equals(pwd)){
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("wronguser.html").forward(request, response);
		}
		
		
	}

}
