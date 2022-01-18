package com.qa.api.testautomation.pojo;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.qa.api.testautomation.restclient.RestClient;
import com.qa.api.testautomation.util.TestUtil;

import io.restassured.response.Response;
/**
 * This test class is developed to test the scenario in which there are multiple POJO Java classes are getting
 * converted into Json
 * For this first we have create the objects of each POjO classes and then using serialization utility
 * @author rituraj
 *
 */
public class UserResult {
	@Test
	public void createUserWithFullJson() {
	String token = "abc40b51bf18322386dc7cda246662a35323dae4ced01c490f063677d03cc1f6";
	Self sf = new Self("https://www.sf.com");
	Edit ed = new Edit("https://www.edit.com");
	Avatar av = new Avatar("https://www.avatar.com");
	Links ln = new Links(sf,ed,av);
	UserInfo uf = new UserInfo("Kapil_11", "Male", "11-04-1961", "kapil11.dev1@gmail.com", "7509040302", "active", ln);
	
	String UserJsonPayload = TestUtil.getSerializedJSON(uf);
	System.out.println(UserJsonPayload);
	Map<String , String> authTokenMap = new HashMap<String , String>();
	authTokenMap.put("Authorization", "Bearer " +token);
	Response response = RestClient.doPost("JSON", "https://gorest.co.in", "/public/v1/users", authTokenMap, null, true, UserJsonPayload);
	 System.out.println(response.getStatusCode());
	 response.prettyPrint();
	}
	
}
