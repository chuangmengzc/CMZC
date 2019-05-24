package com.chuangmeng.cmzc.commons.PO;


public class TbBill {

  private String billId;
  private String projectId;
  private long billCash;
  private java.sql.Timestamp billDate;
  private String billDescribe;
  private String billSpare2;


  public String getBillId() {
    return billId;
  }

  public void setBillId(String billId) {
    this.billId = billId;
  }


  public String getProjectId() {
    return projectId;
  }

  public void setProjectId(String projectId) {
    this.projectId = projectId;
  }


  public long getBillCash() {
    return billCash;
  }

  public void setBillCash(long billCash) {
    this.billCash = billCash;
  }


  public java.sql.Timestamp getBillDate() {
    return billDate;
  }

  public void setBillDate(java.sql.Timestamp billDate) {
    this.billDate = billDate;
  }


  public String getBillDescribe() {
    return billDescribe;
  }

  public void setBillDescribe(String billDescribe) {
    this.billDescribe = billDescribe;
  }


  public String getBillSpare2() {
    return billSpare2;
  }

  public void setBillSpare2(String billSpare2) {
    this.billSpare2 = billSpare2;
  }

}
