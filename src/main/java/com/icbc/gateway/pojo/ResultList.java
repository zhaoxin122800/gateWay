package com.icbc.gateway.pojo;

import java.util.List;

public class ResultList {
    private  boolean flag;
    private List<String> message;

    public ResultList(boolean flag, List<String> message) {
        this.flag = flag;
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResultList{" +
                "flag=" + flag +
                ", message=" + message +
                '}';
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }
}
