package com.qa.api.testautomation.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.api.testautomation.pojo.User;
import com.qa.api.testautomation.restclient.RestClient;
import com.qa.api.testautomation.util.ExcelUtil;

import io.restassured.response.Response;

public class CreateUserTest {
	
	String baseURI = "https://gorest.co.in";
	String basePath = "/public/v1/users";
	String token = "abc40b51bf18322386dc7cda246662a35323dae4ced01c490f063677d03cc1f6";
	
	@DataProvider
	public Object[][] getUserData() {
		Object userData[][] = ExcelUtil.getTestData("usersdata");
		return userData;
	}
	@Test(dataProvider = "getUserData")
	public void createUserAPIPOSTTest(String name ,String gender ,String dob ,String email ,String phoneNumber ,String status) {
		Map<String , String> authTokenMap = new HashMap<String , String>();
		authTokenMap.put("Authorization", "Bearer " +token);
	//	User user = new User("George_2" ,"male" ,"20-01-2012" ,"testautomation3@gmail.com" ,"+91-8885858","active");
		User user = new User(name , gender , dob , email , phoneNumber , status);
		Response response = RestClient.doPost("JSON", baseURI, basePath, authTokenMap, null, true, user);
		System.out.println(response.getStatusCode());
		response.prettyPrint();
		System.out.println("-------------------------------");
	}

}
