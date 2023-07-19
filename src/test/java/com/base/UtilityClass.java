package com.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UtilityClass {
	RequestSpecification reqSpec;
	Response response;
	
	public void initializeRestAssured() {
		reqSpec = RestAssured.given();
	}
	
	public void addHeader(String key, String value) {
		reqSpec = reqSpec.header(key, value);
	}
	
	public void addHeaders(Headers headers) {
		reqSpec = reqSpec.headers(headers);
	}
	
	public void addBody(File file) {
		reqSpec = reqSpec.body(file);
	}
	
	public Response requestType(String type, String endpoint) {
		switch (type) {
		case "GET":
			response = reqSpec.log().all().get(endpoint);
			break;
		case "POST":
			response = reqSpec.log().all().post(endpoint);
			break;
		case "PUT":
			response = reqSpec.log().all().put(endpoint);
			break;
		case "DELETE":
			response = reqSpec.log().all().delete(endpoint);
			break;
		case "PATCH":
			response = reqSpec.log().all().patch(endpoint);
			break;

		default:
			break;
		}
		return response;
	}
	
	public int getStatusCode(Response response) {
		return response.getStatusCode();
	}
	
	public static String getProjectPath() {
		return System.getProperty("user.dir");
	}
	
	public static String getPropertyFileValue(String key) throws IOException {
		FileInputStream file = new FileInputStream("src\\test\\resources\\PropertyFiles\\Config.properties");
		Properties p = new Properties();
		p.load(file);
		return p.getProperty(key);
	}
	
}
