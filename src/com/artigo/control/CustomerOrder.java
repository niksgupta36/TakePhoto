package com.artigo.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/CustomerOrder")
public class CustomerOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public CustomerOrder() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/plain");
		HttpSession session = request.getSession(false);
		PrintWriter out = response.getWriter();
		if(session!=null) {
			String custname = (String) session.getAttribute("custname");
			 //out.println("Your name is : " + custname);
			int order = new Random().nextInt(100000);
			OrderDAOImpl setorder = new OrderDAOImpl();
			
			setorder.addOrder(custname, order);
			out.println("Your order will be ready in 30 minutes."+"\n");
			 out.println("Your orderId is : " + order);
			
		}
		
     //   PrintWriter out = response.getWriter();
     //   out.println("Your photo has been taken successfully.\n"+"Your order will be ready in 30 minutes."+"\n");
  //      out.println("Your orderId is : " + order);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}



}
