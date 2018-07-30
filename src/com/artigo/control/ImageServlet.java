package com.artigo.control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.Random;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
//import java.util.Base64;

@WebServlet("/ImageServlet")
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
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
			GuestDAOImpl guest = new GuestDAOImpl();
			int orderid = guest.getorder(faceid);
			System.out.println(orderid);
			guest.changestatus(orderid);
			
			
			
			
			
			
			response.setContentType("text/plain");
	        PrintWriter out = response.getWriter();
	        if(custname!="") out.println("Welcome "+custname +"!\n");
	        if(custname.equals("")) out.println("Welcome Guest!");
	        out.println("Your photo has been taken successfully\n");
	        if(orderid!=0)     out.println("Your orderId is : " + orderid);
	        if(orderid==0) 	   out.println("\nWe did not find any order. \n Please try again!");
			
			if(orderid!=0) System.out.println("Your order is : "+orderid);
			else System.out.println("\nWe did not find any order. \n Please try again!");
			
			output.flush();
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
