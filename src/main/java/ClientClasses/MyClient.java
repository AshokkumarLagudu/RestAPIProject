package ClientClasses;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.util.TestUtil;

public class MyClient {
    public static int statuscode;
    public JSONObject responseJson;
    TestUtil testutil;
    public String perpagevalue;
	public void get(String url) throws ClientProtocolException, IOException {

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url);
		CloseableHttpResponse closeableresponse = httpclient.execute(httpget);
		statuscode = closeableresponse.getStatusLine().getStatusCode();
		//System.out.println("Satus Code---:" + statuscode);
		String respondecode = EntityUtils.toString(closeableresponse.getEntity(), "UTF-8");

	    responseJson = new JSONObject(respondecode);
		System.out.println("Response Json---> " + responseJson);
		
		perpagevalue=testutil.getValueByJPath(responseJson, "/per_page");
		
		
		
		Header[] headerarray = closeableresponse.getAllHeaders();
		HashMap<String, String> allheaders = new HashMap<String, String>();
		for (Header header : headerarray) {
			
	    allheaders.put(header.getName(), header.getValue());

		}
		System.out.println("");
		System.out.println("All Headers----> "+allheaders);

	}
	
	public int status(){
		
		//System.out.println("Satus Code---:" + statuscode);
		return statuscode;
	}
	
	//Post Method
       public CloseableHttpResponse post(String url,String entryString,HashMap<String,String> headermap) throws ClientProtocolException, IOException{
    	   
    	   CloseableHttpClient httpclient= HttpClients.createDefault();
    	   HttpPost httppost=new HttpPost(url);//request
    	   httppost.setEntity(new StringEntity(entryString));//Payload
    	   
    	   //For Headers
    	  for(Map.Entry<String, String> entry : headermap.entrySet()){
    		  httppost.addHeader(entry.getKey(),entry.getValue());
    		  
    	  }
    	   
    	  CloseableHttpResponse closeableHttpResponse= httpclient.execute(httppost);
    	  
    	  return closeableHttpResponse;
       }
	
	
	

}
























