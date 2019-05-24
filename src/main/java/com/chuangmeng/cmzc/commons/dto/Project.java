package com.chuangmeng.cmzc.commons.dto;


public class Project {

  private String typeId;
  private String businessId;
  private String projectName;
  private long projectExpectMoney;
  private long projectRealMoney;
  private String projectSpare1;
  private String projectSpare2;


  public String getTypeId() {
    return typeId;
  }

  public void setTypeId(String typeId) {
    this.typeId = typeId;
  }

  public String getBusinessId() {
    return businessId;
  }

  public void setBusinessId(String businessId) {
    this.businessId = businessId;
  }

  public String getProjectName() {
    return projectName;
  }

  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }

  public long getProjectExpectMoney() {
    return projectExpectMoney;
  }

  public void setProjectExpectMoney(long projectExpectMoney) {
    this.projectExpectMoney = projectExpectMoney;
  }

  public long getProjectRealMoney() {
    return projectRealMoney;
  }

  public void setProjectRealMoney(long projectRealMoney) {
    this.projectRealMoney = projectRealMoney;
  }

  public String getProjectSpare1() {
    return projectSpare1;
  }

  public void setProjectSpare1(String projectSpare1) {
    this.projectSpare1 = projectSpare1;
  }

  public String getProjectSpare2() {
    return projectSpare2;
  }

  public void setProjectSpare2(String projectSpare2) {
    this.projectSpare2 = projectSpare2;
  }
}
