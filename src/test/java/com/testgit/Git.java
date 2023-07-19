package com.testgit;

import static io.restassured.RestAssured.baseURI;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.base.UtilityClass;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class Git extends UtilityClass {
	@BeforeMethod
	public void loadBaseURI() throws IOException {
		baseURI = getPropertyFileValue("baseURI");
	}
	
	@Test(priority = 0)
	public void getUser() {
		initializeRestAssured();
		Response response = requestType("GET", "users/SAral0027");
		int code = getStatusCode(response);
		Assert.assertEquals(code, 200);
	}
	
	@Test(priority = 1)
	public void getRepo() {
		initializeRestAssured();
		Response response = requestType("GET", "users/SAral0027/repos");
		int code = getStatusCode(response);
		Assert.assertEquals(code, 200);
	}
	
	@Test(priority = 2)
	public void createRepo() throws IOException {
		initializeRestAssured();
		List<Header> listHeaders = new ArrayList<Header>();
		Header h1 = new Header("Authorization", "Bearer " + getPropertyFileValue("token"));
		Header h2 = new Header("Content-Type", "application/json");
		listHeaders.add(h1);
		listHeaders.add(h2);
		Headers headers = new Headers(listHeaders);
		addHeaders(headers);
		addBody(new File(getPropertyFileValue("jsonPathOfCreateRepo")));
 		Response response = requestType("POST", "user/repos");
		int code = getStatusCode(response);
		Assert.assertEquals(code, 201);
	}
	
	@Test(priority = 3)
	public void updateRepo() throws IOException {
		initializeRestAssured();
		List<Header> listHeaders = new ArrayList<Header>();
		Header h1 = new Header("Authorization", "Bearer " + getPropertyFileValue("token"));
		Header h2 = new Header("Content-Type", "application/json");
		listHeaders.add(h1);
		listHeaders.add(h2);
		Headers headers = new Headers(listHeaders);
		addHeaders(headers);
		addBody(new File(getPropertyFileValue("jsonPathOfUpdateRepo")));
 		Response response = requestType("PATCH", "repos/SAral0027/REST");
		int code = getStatusCode(response);
		Assert.assertEquals(code, 200);
	}
	
	@Test(priority = 4)
	public void deleteRepo() throws IOException {
		initializeRestAssured();
		addHeader("Authorization", "Bearer " + getPropertyFileValue("token"));
		Response response = requestType("DELETE", "repos/SAral0027/RESTAssured");
		int code = getStatusCode(response);
		Assert.assertEquals(code, 204);
	}
}
