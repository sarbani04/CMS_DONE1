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

public class VendorRestTest {

	@Test
	public void testMenuList() throws AssertionError, URISyntaxException {
	Vendor[] res = given().when().accept(ContentType.JSON)
		.get(CommonUtil.getURI("/api/vendor")).getBody().as(Vendor[].class);
	assertEquals(6, res.length);
	}




	@Test
	public void testVendorByName404() throws AssertionError, URISyntaxException {
		given().accept(ContentType.JSON).when()
		.get(CommonUtil.getURI("/api/vendor/xyz")).then().assertThat().statusCode(204);
	}
	@Test
	public void testVendorByName200() throws AssertionError, URISyntaxException {
		given().accept(ContentType.JSON).when()
		.get(CommonUtil.getURI("/api/vendor/ranjan")).then().assertThat().statusCode(200);
	}
	@Test
	public void testCustomerAuthenticate() throws AssertionError, URISyntaxException {
	Integer m = given().
				accept(ContentType.JSON).
				when().
				get(CommonUtil.getURI("/api/vendor/Ranjan/Ranjan123")).
			getBody().as(Integer.class);
		int x=Integer.valueOf(m);
	assertEquals(1, x);
	}
	
}
