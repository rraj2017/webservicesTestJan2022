package com.qa.api.testautomation.restclient;

import java.io.File;
import java.util.Map;

import com.qa.api.testautomation.util.TestUtil;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * This class is having all HTTP methods which will call the apis and having
 * generic methods for getting the response and fetch the values from response.
 * 
 * @author rituraj
 *
 */
public class RestClient {

	// HTTP Methods: GET POST PUT DELETE
	/**
	 * This method is used to call GET API
	 * 
	 * @param contentType
	 * @param baseURI
	 * @param basePath
	 * @param token
	 * @param paramsMap
	 * @param log
	 * @return this method is returning response from the GET call
	 */
	public static Response doGet(String contentType, String baseURI, String basePath, Map<String ,String> token,
			Map<String, String> paramsMap, boolean log) {
		if (setBaseURI(baseURI)) {
			RequestSpecification request = createRequest(contentType, token, paramsMap, log);
			return getResponse("GET", request, basePath);
		}
		return null;

	}

	/**
	 * This method is used to call POST API
	 * 
	 * @param contentType
	 * @param baseURI
	 * @param basePath
	 * @param token
	 * @param paramsMap
	 * @param log
	 * @param obj
	 * @return this method is returning response from the POST call
	 */
	public static Response doPost(String contentType, String baseURI, String basePath, Map<String, String> token,
			Map<String, String> paramsMap, boolean log, Object obj) {
		if (setBaseURI(baseURI)) {
			RequestSpecification request = createRequest(contentType, token, paramsMap, log);
			addRequestPayload(request, obj);
			return getResponse("POST", request, basePath);
		}
		return null;

	}

	public static void addRequestPayload(RequestSpecification request, Object obj) {
		if (obj instanceof Map) {
			request.formParams((Map<String, String>) obj);
		} else {
			String jsonPayload = TestUtil.getSerializedJSON(obj);
			request.body(jsonPayload);
		}
	}

	/**
	 * 
	 * @param baseURI
	 */
	private static boolean setBaseURI(String baseURI) {
		if (baseURI == null || baseURI.isEmpty()) {
			System.out.println("Please pass the correct base URI.. either it is null or blank/empty");
			return false;
		}
		try {
			RestAssured.baseURI = baseURI;
			return true;
		} catch (Exception e) {
			System.out.println("some exception got occured while assigning the base URI with Rest Assured...");
			return false;
		}

	}

	private static RequestSpecification createRequest(String contentType, Map<String, String> token,
			Map<String, String> paramsMap, boolean log) {
		RequestSpecification request;
		if (log) {
			request = RestAssured.given().log().all();
		} else {
			request = RestAssured.given();
		}
		if (token.size() > 0) {
			// request.header("Authorization" ,"Bearer " + token);
			request.headers(token);
		}
		if (!(paramsMap == null)) {
			request.queryParams(paramsMap);
		}
		if (contentType != null) {
			if (contentType.equalsIgnoreCase("JSON")) {
				request.contentType(ContentType.JSON);
			} else if (contentType.equalsIgnoreCase("XML")) {
				request.contentType(ContentType.XML);
			} else if (contentType.equalsIgnoreCase("TEXT")) {
				request.contentType(ContentType.TEXT);
			} else if (contentType.equalsIgnoreCase("multipart")) {
				request.multiPart("image", new File("/Users/rituraj/Downloads/pratibhahindisign.jpg"));
			}
		}
		return request;
	}

	private static Response getResponse(String httpMethod, RequestSpecification request, String basePath) {
		return executeAPI(httpMethod, request, basePath);
	}

	private static Response executeAPI(String httpMethod, RequestSpecification request, String basePath) {
		Response response = null;
		switch (httpMethod) {
		case "GET":
			response = request.get(basePath);
			break;
		case "POST":
			response = request.post(basePath);
			break;
		case "PUT":
			response = request.put(basePath);
			break;
		case "DELETE":
			response = request.delete(basePath);
			break;

		default:
			System.out.println("Please pass the correct HTTP Method.....");
			break;
		}
		return response;
	}

}
