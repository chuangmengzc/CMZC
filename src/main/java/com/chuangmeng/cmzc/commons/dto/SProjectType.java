package com.chuangmeng.cmzc.commons.dto;

import java.util.List;

public class SProjectType {
    private  String typeName2;
    private List<FirFProject> sixFProjects;
    private List<FirFProject> sixFProjects2;
    public String getTypeName2() {
        return typeName2;
    }

    public void setTypeName2(String typeName2) {
        this.typeName2 = typeName2;
    }

    public List<FirFProject> getSixFProjects() {
        return sixFProjects;
    }

    public List<FirFProject> getSixFProjects2() {
        return sixFProjects2;
    }

    public void setSixFProjects2(List<FirFProject> sixFProjects2) {
        this.sixFProjects2 = sixFProjects2;
    }

    public void setSixFProjects(List<FirFProject> sixFProjects) {
        this.sixFProjects = sixFProjects;
    }
}
