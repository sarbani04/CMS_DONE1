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

public class OrderDetailRestTest {

    @Test
    public void testOrderDetailList() throws AssertionError, URISyntaxException {
    OrderDetail[] res = given().when().accept(ContentType.JSON)
        .get(CommonUtil.getURI("/api/orderdetail/")).getBody().as(OrderDetail[].class);
    assertEquals(33, res.length);
    assertEquals(1, res[0].getOrdId());
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String dt = sdf.format(res[0].getOrdTime());
    assertEquals("2020-01-01", dt);
    assertEquals(589.0, res[0].getOrdAmount(), 0.1);
    assertEquals("SIRUSERI", res[0].getOrdLocation());
    OrderStatus stat = res[0].getOrdStatus();
    assertEquals(OrderStatus.ACCEPTED, stat);
    assertEquals(2, res[0].getQtyOrder());
    assertEquals(1, res[0]. getCusId());
    assertEquals(1, res[0]. getFoodId());
    assertEquals(1, res[0].getVenId());
    assertEquals("PAYTM", res[0].  getWalType());
    assertEquals(null, res[0]. getOrdComments());
    }


    // public void testFoodDetails() throws AssertionError, URISyntaxException {
    // Menu m = given().
    //               accept(ContentType.JSON).
    //            when().
    //               get(CommonUtil.getURI("api/menu/1")).
    //            getBody().as(Menu.class);
    //            assertEquals(1, m.getFoodId());
    //            assertEquals("Dosa",m.getFoodName());
    //            assertEquals(300, m.getFoodPrice(), 0.1);
    // }

    // @Test
    // public void testOrderDetailById404() throws AssertionError, URISyntaxException {
    //  given().accept(ContentType.JSON).when()
    //  .get(CommonUtil.getURI("/api/orderdetail/historycus/20")).then().assertThat().statusCode(404);
    // }
    @Test
    public void testOrderDetailById200() throws AssertionError, URISyntaxException {
        given().accept(ContentType.JSON).when()
        .get(CommonUtil.getURI("/api/orderdetail/historycus/1")).then().assertThat().statusCode(200);
    }
    
}