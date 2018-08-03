package com.artigo.control;



import java.net.URI;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class FaceVerify 

{
	public boolean getVerify(String s1, String s2) {
		// TODO Auto-generated method stub
		
	{
	       HttpClient httpclient = HttpClients.createDefault();

	       try
	       {
	          // URIBuilder builder = new URIBuilder("https://westcentralus.api.cognitive.microsoft.com/face/v1.0/verify");
	           URIBuilder builder = new 
	           	URIBuilder("https://westcentralus.api.cognitive.microsoft.com/face/v1.0/verify");

//	        	private static final String builder =
//	        	       "https://westcentralus.api.cognitive.microsoft.com/face/v1.0/detect";

	           URI uri = builder.build();
	           HttpPost request = new HttpPost(uri);
	           request.setHeader("Content-Type", "application/json");
	           request.setHeader("Ocp-Apim-Subscription-Key", "41ba76664c874969b77aa9d5963ddddb");
	           

	           // Request body
	           String str = "{ faceId1: '" + s1 + "', faceId2: '"+s2+"' }";
	           
	           StringEntity reqEntity = new StringEntity(str);
	           request.setEntity(reqEntity);

	           HttpResponse response = httpclient.execute(request);
	           HttpEntity entity = response.getEntity();
	           
	           String jsonString = EntityUtils.toString(entity).trim();
	           
	           if (entity != null) 
	           {
	        	   JSONObject jsonObject = new JSONObject(jsonString);
	               //   System.out.println(jsonObject.toString(2));
	                
	                 // return (String) jsonObject.get("faceId");
	               boolean b = jsonObject.getBoolean("isIdentical");
	             if(b==true) return b;
	             else return false;
	           }
	       }
	       catch (Exception e)
	       {
	       //    System.out.println(e.getMessage());
	       }
		
		
		
		return false;
	}
	}
}