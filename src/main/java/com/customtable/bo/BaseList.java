package com.customtable.bo;

public class BaseList {
    private String  listCode;//列表编号
    private String  listName;//列表名称
    private String  listDesc;//列表描述
    private boolean state;//0 可用 1禁用

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getListCode() {
        return listCode;
    }

    public void setListCode(String listCode) {
        this.listCode = listCode;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getListDesc() {
        return listDesc;
    }

    public void setListDesc(String listDesc) {
        this.listDesc = listDesc;
    }
}
