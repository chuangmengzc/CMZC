package com.chuangmeng.cmzc.commons.dto;


import java.util.Date;
import java.util.List;

public class FirFProject {
  private String projectId;
  private String typeId;
  private String businessId;
  private String projectName;
  private long projectExpectMoney;
  private long projectRealMoney;
  private Date projectStartTime;
  private Date projectEndTime;
  private long projectZan;
  private String projectVideo;
  private String projectImgs;
  private String projectPic;
  private String projectTitlePic1;
  private String projectTitlePic2;
  private long projectStatus;
  private String projectSpare1;
  private String projectSpare2;
  private long time;
  private List<EndProject> projects;

  public long getTime() {
    return time;
  }

  public void setTime(long time) {
    this.time = time;
  }

  public String getProjectId() {
    return projectId;
  }

  public void setProjectId(String projectId) {
    this.projectId = projectId;
  }

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

  public Date getProjectStartTime() {
    return projectStartTime;
  }

  public void setProjectStartTime(Date projectStartTime) {
    this.projectStartTime = projectStartTime;
  }

  public Date getProjectEndTime() {
    return projectEndTime;
  }

  public void setProjectEndTime(Date projectEndTime) {
    this.projectEndTime = projectEndTime;
  }

  public long getProjectZan() {
    return projectZan;
  }

  public void setProjectZan(long projectZan) {
    this.projectZan = projectZan;
  }

  public String getProjectVideo() {
    return projectVideo;
  }

  public void setProjectVideo(String projectVideo) {
    this.projectVideo = projectVideo;
  }

  public String getProjectImgs() {
    return projectImgs;
  }

  public void setProjectImgs(String projectImgs) {
    this.projectImgs = projectImgs;
  }

  public String getProjectPic() {
    return projectPic;
  }

  public void setProjectPic(String projectPic) {
    this.projectPic = projectPic;
  }

  public String getProjectTitlePic1() {
    return projectTitlePic1;
  }

  public void setProjectTitlePic1(String projectTitlePic1) {
    this.projectTitlePic1 = projectTitlePic1;
  }

  public String getProjectTitlePic2() {
    return projectTitlePic2;
  }

  public void setProjectTitlePic2(String projectTitlePic2) {
    this.projectTitlePic2 = projectTitlePic2;
  }

  public long getProjectStatus() {
    return projectStatus;
  }

  public void setProjectStatus(long projectStatus) {
    this.projectStatus = projectStatus;
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

  public List<EndProject> getProjects() {
    return projects;
  }

  public void setProjects(List<EndProject> projects) {
    this.projects = projects;
  }
}
