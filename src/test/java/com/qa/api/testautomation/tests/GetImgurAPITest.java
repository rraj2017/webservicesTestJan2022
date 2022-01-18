package com.qa.api.testautomation.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.api.testautomation.restclient.RestClient;
import com.qa.api.testautomation.util.Token;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class GetImgurAPITest {
	Map<Object , Object> tokenMap;
	String accessToken;
	String accountUserName;
	String refreshToken;
	String baseURI ="https://api.imgur.com";
	String basePath = "/account/v1/"+accountUserName+"/block";
	String basePath_1 = "/3/account/me/images";
	String basePath_2 = "/3/upload";
	
	@BeforeMethod
	public void setUp() {
		tokenMap = Token.getAccessToken();
		accessToken = tokenMap.get("access_token").toString();
		accountUserName = tokenMap.get("account_username").toString();
		refreshToken = tokenMap.get("refresh_token").toString();
	}
	
	@Test
	public void getAccountBlockStatusTest() {
		Map<String , String> authTokenMap = Token.getAuthToken();
		Response response = RestClient.doGet(null, baseURI, basePath, authTokenMap, null, true);
		System.out.println(response.getStatusCode());
		response.prettyPrint();
		
	}
	@Test
	public void getAccountImagesTest() {
		Map<String , String> authTokenMap = Token.getAuthToken();
		Response response = RestClient.doGet(null, baseURI, basePath_1, authTokenMap, null, true);
		System.out.println(response.getStatusCode());
		response.prettyPrint();
		System.out.println(response.getBody().jsonPath().get("data.id[1]"));
		response.then().assertThat().body(matchesJsonSchemaInClasspath("ImgurImagesSchema.json"));	
	}
	@Test
	public void uploadImagePostAPITest() {
		Map<String , String> clientIdMap = Token.getClientId();
		Map<String ,String> formMap = new HashMap<String , String>();
		formMap.put("title", "posting title as test title API");
		formMap.put("description", "posting desc for the title");
		Response response = RestClient.doPost("multipart", baseURI, basePath_2, clientIdMap, null, true, formMap);
		response.prettyPrint();
		System.out.println(response.statusCode());
	}
}
