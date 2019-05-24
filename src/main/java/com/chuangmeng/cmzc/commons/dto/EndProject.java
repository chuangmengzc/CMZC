package com.chuangmeng.cmzc.commons.dto;

import java.util.Date;

public class EndProject {
    private String projectId;
    private String projectName;
    private long projectExpectMoney;
    private long projectRealMoney;
    private long time;
    private java.util.Date projectStartTime;
    private java.util.Date projectEndTime;
    private String projectTitlePic2;
    private String projectSpare1;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
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

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
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

    public String getProjectTitlePic2() {
        return projectTitlePic2;
    }

    public void setProjectTitlePic2(String projectTitlePic2) {
        this.projectTitlePic2 = projectTitlePic2;
    }

    public String getProjectSpare1() {
        return projectSpare1;
    }

    public void setProjectSpare1(String projectSpare1) {
        this.projectSpare1 = projectSpare1;
    }
}
