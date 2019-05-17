package com.chuangmeng.cmzc.commons;

public enum DIR {
    PROJECT("project/"),USER("user/"),PACKAGE("package/"),ROOT("");

    private String dir;
    DIR(String str) {
        this.dir =str;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }
}
