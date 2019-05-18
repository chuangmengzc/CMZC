package com.chuangmeng.cmzc.commons.PO;


public class TbOrder {

  private String orderId;
  private String userId;
  private long orderStatus;
  private java.sql.Timestamp orderTime;
  private long orderMoney;
  private long orderPachageNum;
  private long orderInvoice;
  private String orderPackageId;
  private String orderPostNum;
  private String orderSpare1;
  private String orderSpare2;


  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }


  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }


  public long getOrderStatus() {
    return orderStatus;
  }

  public void setOrderStatus(long orderStatus) {
    this.orderStatus = orderStatus;
  }


  public java.sql.Timestamp getOrderTime() {
    return orderTime;
  }

  public void setOrderTime(java.sql.Timestamp orderTime) {
    this.orderTime = orderTime;
  }


  public long getOrderMoney() {
    return orderMoney;
  }

  public void setOrderMoney(long orderMoney) {
    this.orderMoney = orderMoney;
  }


  public long getOrderPachageNum() {
    return orderPachageNum;
  }

  public void setOrderPachageNum(long orderPachageNum) {
    this.orderPachageNum = orderPachageNum;
  }


  public long getOrderInvoice() {
    return orderInvoice;
  }

  public void setOrderInvoice(long orderInvoice) {
    this.orderInvoice = orderInvoice;
  }


  public String getOrderPackageId() {
    return orderPackageId;
  }

  public void setOrderPackageId(String orderPackageId) {
    this.orderPackageId = orderPackageId;
  }


  public String getOrderPostNum() {
    return orderPostNum;
  }

  public void setOrderPostNum(String orderPostNum) {
    this.orderPostNum = orderPostNum;
  }


  public String getOrderSpare1() {
    return orderSpare1;
  }

  public void setOrderSpare1(String orderSpare1) {
    this.orderSpare1 = orderSpare1;
  }


  public String getOrderSpare2() {
    return orderSpare2;
  }

  public void setOrderSpare2(String orderSpare2) {
    this.orderSpare2 = orderSpare2;
  }

}
