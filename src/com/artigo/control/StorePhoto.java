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

import com.artigo.control.FaceDetect;
import com.artigo.control.GuestDAOImpl;


@WebServlet("/StorePhoto")
public class StorePhoto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public StorePhoto() {
        super();
        // TODO Auto-generated constructor stub
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
			String filename = new Random().nextInt(100000) + ".jpg";

			File file = new File("/Users/nikhil.gupta/Desktop/" + filename);
			FileOutputStream output = new FileOutputStream(file);
			
			output.write(Base64.decodeBase64(data));
			FaceDetect detect = new FaceDetect();
			
			String faceid = detect.getFaceId(file);
			GuestDAOImpl guest = new GuestDAOImpl();
			int order = new Random().nextInt(100000);
			request.setAttribute("order", order);
			guest.insertorder(order, faceid);
			System.out.println("Your orderid is: " + order);
			
			response.getWriter().write(order+",");
			output.flush();
			output.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
