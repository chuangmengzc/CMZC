package com.chuangmeng.cmzc.commons.VO.BackStage;

import java.sql.Timestamp;

public class QueryBill {
    private String billId;
    private String projectId;
    private int billCash;
    private String billDate;
    private String billDescribe;

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

    public int getBillCash() {
        return billCash;
    }

    public void setBillCash(int billCash) {
        this.billCash = billCash;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(Timestamp billDate) {

        this.billDate = billDate.toString().replace(".0","");
    }

    public String getBillDescribe() {
        return billDescribe;
    }

    public void setBillDescribe(String billDescribe) {
        this.billDescribe = billDescribe;
    }

    @Override
    public String toString() {
        return "QueryBill{" +
                "billId='" + billId + '\'' +
                ", projectId='" + projectId + '\'' +
                ", billCash=" + billCash +
                ", billDate=" + billDate +
                ", billDescribe='" + billDescribe + '\'' +
                '}';
    }
}
