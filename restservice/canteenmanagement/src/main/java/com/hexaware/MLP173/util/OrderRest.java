package com.hexaware.MLP173.util;

// import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
//import com.hexaware.MLP173.model.Wallet;
import com.hexaware.MLP173.model.OrderDetail;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;

// import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hexaware.MLP173.factory.OrderFactory;

/**
 * This class provides a REST interface for the employee entity.
 */
@Path("/orderdetail")
public class OrderRest {
  /**
   * Returns wallet details.
   * @return thewallet details
   */
  // @GET
  // @Produces(MediaType.APPLICATION_JSON)
  // public final Wallet[] listWallet() {
  //   final Wallet[] wallet = OrderFactory.showWallet();
  //   return wallet;
  // }
   /**
   * Returns order details.
   * @return the order details
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public final OrderDetail[] listOrderDetail() {
    final OrderDetail[] orderdetail = OrderFactory.showOrderDetail();
    return orderdetail;
  }
   /**
   * Returns order details.
   * @return the order details
   * @throws NotFoundException
   * @param cusId for place order object
   */
  @GET
  @Path("{cusId}")
  @Produces(MediaType.APPLICATION_JSON)
  public final OrderDetail[] showCustomerbyHistory(@PathParam("cusId") final int cusId) {
    final OrderDetail[] orderdetail = OrderFactory.showCustomerHistory(cusId);
    if (orderdetail == null) {
      throw new NotFoundException("No such customer ID: " + cusId);
    }
    return orderdetail;
  }
  /**
   * Returns order details.
   * @return the order details
   * @throws NotFoundException
   * @param venId for place order object
   */
  @GET
  @Path("{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public final OrderDetail[] showVendorbyHistory(@PathParam("id") final int venId) {
    final OrderDetail[] orderdetail = OrderFactory.showVendorHistory(venId);
    if (orderdetail == null) {
      throw new NotFoundException("No such customer ID: " + venId);
    }
    return orderdetail;
  }
  /**
   *@return the order details.
   *@param orderdetail detail for place order.
   */
  @POST
  @Path("/PlaceOrder")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public final String placeOrder(final OrderDetail orderdetail) {
    String result = OrderFactory.placeOrder(orderdetail);
    return result;
  }
   /**
   *@return the order details.
   *@param orderId detail for place order.
   *@param venId detail for place order.
   *@param status detail for place order.
   */
  @POST
  @Path("/AcceptOrReject")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public final String acceptReject(final int orderId, final int venId, final String status) {
    String result = OrderFactory.acceptOrRejectOrder(orderId, venId, status);
    return result;
  }
   /**
   *@return the order details.
   *@param orderId detail for place order.
   *@param cusId detail for place order.
   *@param status detail for place order.
   */
  @POST
  @Path("/cancelOrder")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public final String cancelorder(final int orderId, final int cusId, final String status) {
    String result = OrderFactory.cancelOrder(orderId, cusId, status);
    return result;
  }
}
