package com.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseClass {
     public int Status_Code_200=200;
     public int Status_Code_201=201;
     public int Status_Code_400=400;
     public int Status_Code_401=401;
     public int Status_Code_500=500;
	public Properties prop;
	
	public BaseClass(){
		
		try {
			prop=new Properties();
			FileInputStream fis= new FileInputStream("C:\\Users\\ASHOK\\workspace\\RestApiProject\\src\\"
					+ "main\\java\\com\\config\\config.properties");
			
			prop.load(fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
