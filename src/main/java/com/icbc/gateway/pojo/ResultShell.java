package com.icbc.gateway.pojo;

import java.io.Serializable;

public class ResultShell implements Serializable {

    private  boolean flag;
    private  String message;
    private  GTH    gth;
    private  String shellParameter;
    private  String shellValue;

    public ResultShell() {
    }

    public ResultShell(boolean flag, String message, GTH gth, String shellParameter, String shellValue) {
        this.flag = flag;
        this.message = message;
        this.gth = gth;
        this.shellParameter = shellParameter;
        this.shellValue = shellValue;
    }

    @Override
    public String toString() {
        return "ResultShell{" +
                "flag=" + flag +
                ", message='" + message + '\'' +
                ", gth=" + gth +
                ", shellParameter='" + shellParameter + '\'' +
                ", shellValue='" + shellValue + '\'' +
                '}';
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public GTH getGth() {
        return gth;
    }

    public void setGth(GTH gth) {
        this.gth = gth;
    }

    public String getShellParameter() {
        return shellParameter;
    }

    public void setShellParameter(String shellParameter) {
        this.shellParameter = shellParameter;
    }

    public String getShellValue() {
        return shellValue;
    }

    public void setShellValue(String shellValue) {
        this.shellValue = shellValue;
    }
}
