package com.artigo.control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;


@WebServlet("/StoreCustomer")
public class StoreCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public StoreCustomer() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			StringBuffer buffer = new StringBuffer();
			Reader reader = request.getReader();
			int current;
			while ((current = reader.read()) >= 0)
				buffer.append((char) current);
			String data = new String(buffer);
			data = data.substring(data.indexOf(",") + 1);
			// System.out.println("PNG image data on Base64: " + data);
			String filename = new Random().nextInt(100000) + ".jpg";

			File file = new File("/Users/nikhil.gupta/Desktop/" + filename);
			//System.out.println(file);
			FileOutputStream output = new FileOutputStream(file);
			
			output.write(Base64.decodeBase64(data));
			FaceDetect detect = new FaceDetect();
			
			String faceid = detect.getFaceId(file);
			
			CustomerDAOImpl cust = new CustomerDAOImpl();
			String custname = cust.getName(faceid);
		//	System.out.println(orderid);
			
			OrderDAOImpl order = new OrderDAOImpl();
			
			int getorder1 = order.getOrder(custname);
			
			order.changestatus(getorder1);
			
			response.setContentType("text/plain");
	        PrintWriter out = response.getWriter();
	        out.println("Welcome "+custname);
	        out.println("Your photo has been taken successfully\n");
	        out.println("Your orderId is : " + getorder1);
			
			if(getorder1!=0) System.out.println("Your order is : "+getorder1);
			else System.out.println("Please try again!");
			
			output.flush();
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
		
	}


