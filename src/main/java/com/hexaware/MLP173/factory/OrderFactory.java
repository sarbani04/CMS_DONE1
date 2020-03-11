package com.hexaware.MLP173.factory;

import com.hexaware.MLP173.persistence.OrderDAO;
import com.hexaware.MLP173.persistence.DbConnection;
import java.util.List;
import java.util.Date;

import com.hexaware.MLP173.model.Customer;
import com.hexaware.MLP173.model.Menu;
import com.hexaware.MLP173.model.OrderDetail;
import com.hexaware.MLP173.model.OrderStatus;
import com.hexaware.MLP173.model.Wallet;
/**
 * OrderFactory class used to fetch order data from database.
 * @author hexware
 */
public class OrderFactory {
  /**
   *  Protected constructor.
   */
  protected OrderFactory() {

  }
  /**
   * Call the data base connection.
   * @return the connection object.
   */
  public static OrderDAO dao() {
    DbConnection db = new DbConnection();
    return db.getConnect().onDemand(OrderDAO.class);
  }
   /**
   * Call the data base connection.
   * @param menuId for accept menu Id
   * @return the array of menu object.
   */
  public static Menu showMenuItem(final int menuId) {
    Menu orderDetail = dao().findByMenuId(menuId);
    return orderDetail;
  }
  /**
   * Call the data base connection.
   * @return the array of menu object.
   */
  public static OrderDetail[] showOrderDetail() {
    List<OrderDetail> orderDetail = dao().show();
    return orderDetail.toArray(new OrderDetail[orderDetail.size()]);
  }
  /**
   * show customer pending orders.
   * @param cusId for pending orders.
   * @return the array of order object.
   */
  public static OrderDetail[] showpendingCustomeOrderDetails(final int cusId) {
    List<OrderDetail> orderDetail = dao().pendingCustomer(cusId);
    return orderDetail.toArray(new OrderDetail[orderDetail.size()]);
  }
  /**
   * show customer pending orders.
   * @param venId for pending orders.
   * @return the array of order object.
   */
  public static OrderDetail[] showpendingVendorOrderDetails(final int venId) {
    List<OrderDetail> orderDetail = dao().pendingVendor(venId);
    return orderDetail.toArray(new OrderDetail[orderDetail.size()]);
  }
  /**
   * show customer pending orders.
   * @param cusId for pending orders.
   * @return the array of order object.
   */
  public static OrderDetail[] showCustomerHistory(final int cusId) {
    List<OrderDetail> orderDetail = dao().customerHistory(cusId);
    return orderDetail.toArray(new OrderDetail[orderDetail.size()]);
  }
  /**
   * show customer pending orders.
   * @param venId for pending orders.
   * @return the array of order object.
   */
  public static OrderDetail[] showVendorHistory(final int venId) {
    List<OrderDetail> orderDetail = dao().vendorHistory(venId);
    return orderDetail.toArray(new OrderDetail[orderDetail.size()]);
  }
   /**
   * Show Vendor Orders History.
   * @param orderId for accept or reject.
   * @param cusId for accept or reject.
   * @param status for changing status.
   * @return the array of order object.
   */
  public static String cancelOrder(final int orderId, final int cusId, final String status) {
    OrderDetail orderdetail = dao().findByOrderId(orderId);
    int customerId = orderdetail.getCusId();
    OrderStatus ordStatus = orderdetail.getOrdStatus();
    String result = "";
    if (orderdetail != null) {
      if (ordStatus == OrderStatus.PENDING) {
        if (customerId == cusId) {
          if (status.equals("YES")) {
            String st = "CANCELLED";
            dao().acceptOrReject(st, orderId);
            double billAmount = orderdetail.getOrdAmount();
            String type = orderdetail.getWalType();
            billAmount = billAmount - (billAmount / 10);
            dao().refundAmount(billAmount, type, cusId);
            result = "Order Cancelled Successfully...";
          }
        } else {
          result = "You are Unauthorized Customer";
        }
      } else {
        result = "You cannot cancel this order..";
      }
    } else {
      result = "Invalid OrderId...";
    }
    return result;
  }

  /**
   * Show Vendor Orders History.
   * @param orderId for accept or reject.
   * @param vendorId for accept or reject.
   * @param status for changing status.
   * @return the array of order object.
   */
  public static String acceptOrRejectOrder(final int orderId, final int vendorId, final String status) {
    OrderDetail orderdetail = dao().findByOrderId(orderId);
    String result = "";
    if (orderdetail != null) {
      if (orderdetail.getVenId() == vendorId) {
        if (status.equals("ACCEPTED")) {
          dao().acceptOrReject(status, orderId);
          result = "Order Accepted Successfully...";
        }
        if (status.equals("REJECTED")) {
          dao().acceptOrReject(status, orderId);
          String type = orderdetail.getWalType();
          int cusId = orderdetail.getCusId();
          double billAmount = orderdetail.getOrdAmount();
          dao().refundAmount(billAmount, type, cusId);
          result = "Order Rejected and Amount Refunded...";
        }
      } else {
        result = "You are Unauthorized Vendor for this order...";
      }
    } else {
      result = "Invalid OrderId...";
    }
    return result;
  }
 /**
   * Place order for customer.
   * @param orderdetail for accepting order details.
   * @return order status.
   */
  public static String placeOrder(final OrderDetail orderdetail) {
    Menu menu = dao().findByMenuId(orderdetail.getFoodId());
    Customer customer = dao().findByCusId(orderdetail.getCusId());
    Menu menu1 = dao().findByMenuId(orderdetail.getMenuId1());
    Menu menu2 = dao().findByMenuId(orderdetail.getMenuId2());
    Menu menu3 = dao().findByMenuId(orderdetail.getMenuId3());
    
    Wallet wallet = dao().getWallentInfo(orderdetail.getWalType(), orderdetail.getCusId());
    System.out.println("Wallet Amount " + wallet.getWalAmount());
    double walAmount = wallet.getWalAmount();
    //double price = menu.getFoodPrice();
    double price1 = menu1.getMenuCost();
    double price2 = menu2.getMenuCost();
    double price3 = menu3.getMenuCost();
    double price4 = price1+price2+price3;
    Date today = new Date();
    System.out.println(orderdetail.getOrdTime());
    long diffTime = orderdetail.getOrdTime().getTime() - today.getTime();
    long diffDays = diffTime / (60 * 60 * 1000 * 24);
    double totalAmount = price4 * orderdetail.getQtyOrder();
    int count =0;
    count=OrderFactory.checkGender(order.getCustomerId());
    Calendar calendar = Calendar.getInstance();
    Calendar calendar2 = Calendar.getInstance();
    calendar.setTime(order.getOrderDate());
    //calendar2.setTime(calendar.);
    int ordermonth= calendar.get(Calendar.MONTH);
    int currentmonth= calendar2.get(Calendar.MONTH);
    {
      if (count==1 && ordermonth==currentmonth){
        
        System.out.println("Happy Women's Day");
      }
      else{
        System.out.println("Normal day");
      }
    }
    if (walAmount < totalAmount) {
      return "Insufficient Funds...";
    } else if (diffDays < 0) {
      return "order cannot be placed for previous day";
    } else {
      double diff = walAmount - totalAmount;
      //System.out.println("Price is  " + menu.getMenuCost());
      System.out.println("Price is  " + totalAmount);
      order.setOrderStatus(OrderStatus.PENDING);
      order.setOrderTotalamount(totalAmount);
      dao().placeOrder(order);
      dao().updateBalance(diff, order.getWalletType(), order.getCustomerId());
      return "Order Placed Successfully...";
    }
    if (walAmount < totalAmount) {
      return "Insufficient Funds...";
    }  else if (diffDays < 0)  {
      return "Order Cannot placed yesterday...";
    }  else if (today.getMonth() == customer.getCusDob().getMonth()) {
      System.out.println("Happy Birthday " +customer.getCusName() +" You are getting 10% Discount on your food,Have fun");
      double discount = totalAmount - (totalAmount * 0.10);
      double diff = walAmount - discount;
      System.out.println("Price is  " + discount);
      orderdetail.setOrdStatus(OrderStatus.PENDING);
      orderdetail.setOrdAmount(totalAmount);
      dao().placeOrder(orderdetail);
      dao().updateBalance(diff, orderdetail.getWalType(), orderdetail.getCusId());
      return "Order Placed Successfully...";
    } else if (totalAmount >= 500) {
      System.out.println("You are getting 5% discount");
      double discount = totalAmount - (totalAmount * 0.05);
      double diff = walAmount - discount;
      System.out.println("Price is  " + discount);
      orderdetail.setOrdStatus(OrderStatus.PENDING);
      orderdetail.setOrdAmount(totalAmount);
      dao().placeOrder(orderdetail);
      dao().updateBalance(diff, orderdetail.getWalType(), orderdetail.getCusId());
      return "Order Placed Successfully...";
     } else {
      double diff = walAmount - totalAmount;
      System.out.println("Price is  " + menu.getFoodPrice());
      orderdetail.setOrdStatus(OrderStatus.PENDING);
      orderdetail.setOrdAmount(totalAmount);
      dao().placeOrder(orderdetail);
      dao().updateBalance(diff, orderdetail.getWalType(), orderdetail.getCusId());
      return "Order Placed Successfully...";
    }
  }
}
