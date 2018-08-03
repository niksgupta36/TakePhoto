package com.artigo.control;

import java.awt.List;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Random;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;


@WebServlet("/LStoreCustomer")
public class LStoreCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public LStoreCustomer() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	response.getWriter().append("Served at: ").append(request.getContextPath());
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
			
			
			
			
			
			
			
			LGetOrder getorder = new LGetOrder();
			ArrayList<String> list2 = new ArrayList();
			list2 = getorder.getOrderID(faceid);
//			CustomerDAOImpl cust = new CustomerDAOImpl();
//			String custname = cust.getName(faceid);
		//	System.out.println(orderid);
			
//			OrderDAOImpl order = new OrderDAOImpl();
			
//			int getorder1 = order.getOrder(custname);
//			
//			order.changestatus(getorder1);
			ArrayList<String> list1 = new ArrayList();
			list1 = getorder.getDetails(list2);
			System.out.println(list1);
			response.setContentType("text/plain");
	        PrintWriter out = response.getWriter();
	    //    out.println("Welcome "+custname);
	        out.println("Your photo has been taken successfully\n");
	        
	        for(int i=0;i<list2.size();i++) {
	        	//System.out.println(i);
	        	out.println("Your orderId is : "+ list2.get(i) );
	        	for(int j=0;j<list1.size();j++)
	        	out.println(" ( "+ list1.get(j) + ")" );
	        }
	        if(list1==null) {
	        	out.println("But No Order found! Please click on 'Take Photo' and try again" );
	        }
	        
			getorder.changeStatus(list2);
//			if(getorder1!=0) System.out.println("Your order is : "+getorder1);
//			else System.out.println("Please try again!");
			
			output.flush();
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
