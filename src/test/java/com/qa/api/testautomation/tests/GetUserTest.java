package com.qa.api.testautomation.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.qa.api.testautomation.restclient.RestClient;

import io.restassured.response.Response;

public class GetUserTest {
	String baseURI = "https://gorest.co.in";
	String basePath = "/public/v1/users";
	String token = "abc40b51bf18322386dc7cda246662a35323dae4ced01c490f063677d03cc1f6";
	
	@Test
	public void getAllUserListAPITest() {
		Map<String , String> authTokenMap = new HashMap<String , String>();
		authTokenMap.put("Authorization", "Bearer " +token);
		Response response = RestClient.doGet("JSON", baseURI, basePath, authTokenMap, null, true);
		System.out.println(response.getStatusCode());
		response.prettyPrint();
	}
	
	@Test
	public void getUserWithQueryParamsAPITest() {
		Map<String , String> authTokenMap = new HashMap<String , String>();
		authTokenMap.put("Authorization", "Bearer " +token);
		Map<String ,String> params = new HashMap<String ,String>();
		params.put("name", "Gopee");
		params.put("gender", "male");
		Response response = RestClient.doGet("JSON", baseURI, basePath, authTokenMap, params, true);
		System.out.println(response.getStatusCode());
		response.prettyPrint();
	}

}
