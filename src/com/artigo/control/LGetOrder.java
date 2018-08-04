package com.artigo.control;




import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;




public class LGetOrder 
    {
	
	
 
public static final String POST_DATA = "";
    //public static final String POST_DATA = "";

    public  ArrayList getOrderID(String faceid)
    {
    	String POST_URL = "https://api.backendless.com/7AACD2FF-30C9-55BC-FF08-02D8062DEF00/5C14247F-7ED9-6D78-FFEB-9067A9997900/services/services/getCurrentOrders";
    	List<String> list = new ArrayList();
    	try {
    		
        String[] details = {};
     //  System.out.println(Arrays.toString(details));
       
        URL line_api_url = new URL(POST_URL);
    //    String payload = POST_DATA;
        
        HttpURLConnection linec = (HttpURLConnection) line_api_url
                .openConnection();
        linec.setDoInput(true);
        linec.setDoOutput(true);
        linec.setRequestMethod("GET");
        linec.setRequestProperty("Content-Type", "application/json");
        
        OutputStreamWriter writer = new OutputStreamWriter(
                linec.getOutputStream(), "UTF-8");
       // writer.write(payload);

        BufferedReader in = new BufferedReader(new InputStreamReader(
                linec.getInputStream()));
        String inputLine;
        int i=0;
        FaceVerify verify = new FaceVerify();
        while ((inputLine = in.readLine()) != null) {
       	 String jsonString = (inputLine).trim();
       	 
     //  	System.out.println(inputLine); 
       if (jsonString.charAt(0) == '[') {
    	   
           JSONArray jsonArray = new JSONArray(jsonString);
           while(i<jsonArray.length()) {
           JSONObject obj=(JSONObject) jsonArray.get(i);
           if(verify.getVerify(faceid, (String) obj.get("faceID"))) {
        	   Thread.sleep(100);

        	   list.add((String) obj.get("orderID"));
        	   
           }
            
           i++;
       }}
    }
    	}
    	catch(Exception e) {
    		System.out.println(e);
    	}
    	
		return (ArrayList) list;
}
    
    public  ArrayList getDetails(List orderid)
    {
    	List<String> list = new ArrayList();
//    	StringBuilder PARAMS= new StringBuilder();
//    	PARAMS.append("{\"ids\": [");
    	String POST_URL = "https://api.backendless.com/7AACD2FF-30C9-55BC-FF08-02D8062DEF00/5C14247F-7ED9-6D78-FFEB-9067A9997900/services/services/getOrdersFromFaces";
//    	for(int i=0;i<orderid.size()-1;i++) {
//    		String s = "\"" + orderid.get(i) + "\"," ;
//    		PARAMS.append(s);
//    		
//    				
//    	}
    	if(orderid.size()>0) {
    	//	String h="\"" + orderid.get(orderid.size()-1) + "\"]}" ;
    	//	PARAMS.append(h);
    	}else {
    		return null;
    	}
    		
    	String json = new Gson().toJson(orderid );
    //	System.out.println(json);
    	String POST_PARAMS = "{\"ids\":"+json+"}";
    //	String POST_PARAMS = PARAMS.toString();
    	//System.out.println(POST_PARAMS);
    try {
    URL obj = new URL(POST_URL);
    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
    con.setRequestMethod("POST");
    con.setRequestProperty("Content-Type", "application/json");
    //con.setRequestProperty("User-Agent", USER_AGENT);

    // For POST only - START
    con.setDoOutput(true);
    OutputStream os = con.getOutputStream();
    os.write(POST_PARAMS.getBytes());
    os.flush();
    os.close();
    // For POST only - END
    
    int responseCode = con.getResponseCode();
    System.out.println("POST Response Code :: " + responseCode);

    if (responseCode == HttpURLConnection.HTTP_OK) { //success
    	BufferedReader in = new BufferedReader(new InputStreamReader(
    			con.getInputStream()));
    	String inputLine;
    	StringBuffer response = new StringBuffer();
    	int i=0;
    	while ((inputLine = in.readLine()) != null) {
//    		response.append(inputLine);
    		 String jsonString = (inputLine).trim();
           	 
    	     //  	System.out.println(inputLine); 
    	       if (jsonString.charAt(0) == '[') {
    	    	   
    	           JSONArray jsonArray = new JSONArray(jsonString);
    	           while(i<jsonArray.length()) {
    	           JSONObject obj1=(JSONObject) jsonArray.get(i);
    	          
    	        	   
    	        	   list.add((String) obj1.get("details"));
    	        	   
    	           
    	            
    	           i++;
    	       }}
    	}
    	in.close();

    	// print result
    	System.out.println(response.toString());
    } else {
    	System.out.println("POST request not worked- details");
    }
    }
    catch(Exception e) {
    	System.out.println(e);
    }
    	
		return (ArrayList) list;
}
    
    public  void changeStatus(List orderid)
    {
    	List<String> list = new ArrayList();
//    	StringBuilder PARAMS= new StringBuilder();
//    	PARAMS.append("{\"ids\": [");
    	String POST_URL = "https://api.backendless.com/7AACD2FF-30C9-55BC-FF08-02D8062DEF00/5C14247F-7ED9-6D78-FFEB-9067A9997900/services/services/confirmPickups";
//    	for(int i=0;i<orderid.size()-1;i++) {
//    		String s = "\"" + orderid.get(i) + "\"," ;
//    		PARAMS.append(s);
//    		
//    				
//    	}
    	if(orderid.size()>0) {
//    		String h="\"" + orderid.get(orderid.size()-1) + "\"]}" ;
//    		PARAMS.append(h);
    	}else {
    		return ;
    	}
    	String json = new Gson().toJson(orderid );
    	//System.out.println(json);
    	String POST_PARAMS = "{\"ids\":"+json+"}";
    //	String POST_PARAMS = PARAMS.toString();
    	//System.out.println(POST_PARAMS);
    try {
    URL obj = new URL(POST_URL);
    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
    con.setRequestMethod("POST");
    con.setRequestProperty("Content-Type", "application/json");
    //con.setRequestProperty("User-Agent", USER_AGENT);

    // For POST only - START
    con.setDoOutput(true);
    OutputStream os = con.getOutputStream();
    os.write(POST_PARAMS.getBytes());
    os.flush();
    os.close();
    // For POST only - END
    
    int responseCode = con.getResponseCode();
    System.out.println("POST Response Code :: " + responseCode);

    if (responseCode == HttpURLConnection.HTTP_OK) { //success
    	BufferedReader in = new BufferedReader(new InputStreamReader(
    			con.getInputStream()));
    	String inputLine;
    	StringBuffer response = new StringBuffer();
    	int i=0;
    	while ((inputLine = in.readLine()) != null) {
//    		response.append(inputLine);
    		 String jsonString = (inputLine).trim();
           	 
    	     //  	System.out.println(inputLine); 
//    	       if (jsonString.charAt(0) == '[') {
//    	    	   
//    	           JSONArray jsonArray = new JSONArray(jsonString);
//    	           while(i<jsonArray.length()) {
//    	           JSONObject obj1=(JSONObject) jsonArray.get(i);
//    	          
//    	        	   
//    	        	  // list.add((String) obj1.get("details"));
//    	        	   
//    	           
//    	            
//    	           i++;
//    	       }}
    	}
    	in.close();

    	// print result
    	System.out.println(response.toString());
    } else {
    	System.out.println("POST request not worked-status");
    }
    }
    catch(Exception e) {
    	System.out.println(e);
    }
    	
		//return (ArrayList) list;
}
    
    
    }
    
    	