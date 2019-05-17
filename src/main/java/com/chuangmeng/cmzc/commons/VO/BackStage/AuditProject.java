package com.chuangmeng.cmzc.commons.VO.BackStage;

public class AuditProject {
    private String projectId;
    private String projectName;
    private String businessId;
    private String businessName;
    private int projectRealMoney;
    private String typeName;

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

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public int getProjectRealMoney() {
        return projectRealMoney;
    }

    public void setProjectRealMoney(int projectRealMoney) {
        this.projectRealMoney = projectRealMoney;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "AuditProject{" +
                "projectId='" + projectId + '\'' +
                ", project_name='" + projectName + '\'' +
                ", businessId='" + businessId + '\'' +
                ", businessName='" + businessName + '\'' +
                ", projectRealMoney='" + projectRealMoney + '\'' +
                ", typeName='" + typeName + '\'' +
                '}';
    }
}
