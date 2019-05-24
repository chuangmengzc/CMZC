package com.chuangmeng.cmzc.commons.dto;

import java.util.List;

public class FProjectType {
    private  String typeName1;
    private List<SProjectType> sProjectTypes;
    public String getTypeName1() {
        return typeName1;
    }

    public void setTypeName1(String typeName1) {
        this.typeName1 = typeName1;
    }

    public List<SProjectType> getsProjectTypes() {
        return sProjectTypes;
    }

    public void setsProjectTypes(List<SProjectType> sProjectTypes) {
        this.sProjectTypes = sProjectTypes;
    }

}
