package com.TestClasses;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.util.TestUtil;

import ClientClasses.MyClient;

public class GetApiTest extends BaseClass {

	BaseClass baseclass;
	String serviceurl1;
	String apiurl;
	String url;
	MyClient myclient;
	int statuscode;
	TestUtil testutil;

	@BeforeMethod
	public void setup() throws ClientProtocolException, IOException {

		baseclass = new BaseClass();
		serviceurl1 = prop.getProperty("url");
		apiurl = prop.getProperty("serviceurl");
		url = serviceurl1 + apiurl;

	}

	@Test
	public void gettest() throws ClientProtocolException, IOException {

		myclient = new MyClient();
		myclient.get(url);
		Assert.assertEquals(myclient.status(), 200);
		System.out.println("Status code is---->:" + myclient.statuscode);

	}

	@Test
	public void perpagetest() {

		System.out.println("Perpage value is-------->" + myclient.perpagevalue);

		Assert.assertEquals(Integer.parseInt(myclient.perpagevalue), 3);

		String total = testutil.getValueByJPath(myclient.responseJson, "/total");

		Assert.assertEquals(Integer.parseInt(total), 12);
		System.out.println("Value of the total is------->" + total);
		
		for(int i=0;i<=2;i++){
		String lastname = testutil.getValueByJPath(myclient.responseJson, "/data["+i+"]/last_name");
		String id = testutil.getValueByJPath(myclient.responseJson,  "/data["+i+"]/id");
		String avatar= testutil.getValueByJPath(myclient.responseJson,  "/data["+i+"]/avatar");
		String firstname = testutil.getValueByJPath(myclient.responseJson,  "/data["+i+"]/first_name");
		System.out.println("-----------------------------------");
        System.out.println("lastname :"+lastname);
        System.out.println("Id :"+id);
        System.out.println("avatar :"+avatar);
        System.out.println("firstname :"+firstname);
		}

	}

	@Test
	public void getjsonarray() {
		
		myclient = new MyClient();
	    testutil=new TestUtil();
		String total = testutil.getValueByJPath(myclient.responseJson, "/total");

		Assert.assertEquals(Integer.parseInt(total), 12);
		System.out.println("Value of the total is------->" + total);
		
			
		for(int i=0;i<=2;i++){
			String lastname = testutil.getValueByJPath(myclient.responseJson, "/data["+i+"]/last_name");
			String id = testutil.getValueByJPath(myclient.responseJson,  "/data["+i+"]/id");
			String avatar= testutil.getValueByJPath(myclient.responseJson,  "/data["+i+"]/avatar");
			String firstname = testutil.getValueByJPath(myclient.responseJson,  "/data["+i+"]/first_name");
			System.out.println("-----------------------------------");
	        System.out.println("lastname :"+lastname);
	        System.out.println("Id :"+id);
	        System.out.println("avatar :"+avatar);
	        System.out.println("firstname :"+firstname);
			}
		
	}

}
