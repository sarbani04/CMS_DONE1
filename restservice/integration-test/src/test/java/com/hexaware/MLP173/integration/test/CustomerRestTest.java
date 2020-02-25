package com.hexaware.MLP173.integration.test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import org.junit.Test;
import static org.junit.Assert.*;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import static com.jayway.restassured.RestAssured.given;

public class CustomerRestTest {

	@Test
	public void testMenuList() throws AssertionError, URISyntaxException {
	Customer[] res = given().when().accept(ContentType.JSON)
		.get(CommonUtil.getURI("/api/customer")).getBody().as(Customer[].class);
	assertEquals(6, res.length);
	}




	@Test
	public void testCustomerByName404() throws AssertionError, URISyntaxException {
		given().accept(ContentType.JSON).when()
		.get(CommonUtil.getURI("/api/customer/xyz")).then().assertThat().statusCode(204);
	}
	@Test
	public void testCustomerByName200() throws AssertionError, URISyntaxException {
		given().accept(ContentType.JSON).when()
		.get(CommonUtil.getURI("/api/customer/rahul")).then().assertThat().statusCode(200);
	}
	@Test
	public void testCustomerAuthenticate() throws AssertionError, URISyntaxException {
	Integer m = given().
				accept(ContentType.JSON).
				when().
				get(CommonUtil.getURI("/api/customer/Rahul/Rahul1997@")).
			getBody().as(Integer.class);
		int x=Integer.valueOf(m);
	assertEquals(1, x);
	}
	
}
