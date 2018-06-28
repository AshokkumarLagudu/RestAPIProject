package com.TestClasses;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Data.Users;
import com.base.BaseClass;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.util.TestUtil;

import ClientClasses.MyClient;

public class PostAPITest extends BaseClass {
	BaseClass baseclass;
	String serviceurl1;
	String apiurl;
	String url;
	MyClient myclient;
	int statuscode;
	TestUtil testutil;
	CloseableHttpResponse closeableHttpResponse;
	@BeforeMethod
	public void setup() throws ClientProtocolException, IOException {

		baseclass = new BaseClass();
		serviceurl1 = prop.getProperty("url");
		apiurl = prop.getProperty("serviceurl");
		url = serviceurl1 + apiurl;

	}
	@Test
	public void postMethodTest() throws JsonGenerationException, JsonMappingException, IOException{
		
		myclient=new MyClient();
		HashMap<String,String> headermap=new HashMap<String,String>();
		headermap.put("Content-Type", "application/json");
		
		//jackson API
		ObjectMapper mapper=new ObjectMapper();
		Users users=new Users("Ashok","Leader");
		
		//Object to JSon file
		
		mapper.writeValue(new File("C:\\Users\\ASHOK\\workspace\\RestApiProject\\src\\main\\java\\com\\Data\\users.json"), users);
	    
		//Object to json in String
		String usersJSonString=mapper.writeValueAsString(users);
		System.out.println(usersJSonString);
		
		closeableHttpResponse=myclient.post(url, usersJSonString, headermap);
		
		int statuscode=closeableHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statuscode, baseclass.Status_Code_201);
		
		String responseString=EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
		
		JSONObject resJSON= new JSONObject(responseString);
		System.out.println("Response JSON String: "+resJSON);
		//JSON to java Object
		Users userResObj=mapper.readValue(responseString, Users.class);
		System.out.println(userResObj);
		
		Assert.assertTrue(users.getName().equals(userResObj.getName()));
		Assert.assertTrue(users.getJob().equals(userResObj.getJob()));
		System.out.println(userResObj.getId());
		System.out.println(userResObj.getCreatedAt());
	}
	

}


















