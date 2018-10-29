package com.icbc.gateway.pojo;

public class GTH {
    private String users;
    private String id;
    private String url;
    private String name;
    private String psw;
    private String startShell;
    private String resetShell;
    private String breakShell;

    public GTH() {
    }

    @Override
    public String toString() {
        return "GTH{" +
                "users='" + users + '\'' +
                ", id='" + id + '\'' +
                ", url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", psw='" + psw + '\'' +
                ", startShell='" + startShell + '\'' +
                ", resetShell='" + resetShell + '\'' +
                ", breakShell='" + breakShell + '\'' +
                '}';
    }

    public GTH(String users, String id, String url, String name, String psw, String startShell, String resetShell, String breakShell) {
        this.users = users;
        this.id = id;
        this.url = url;
        this.name = name;
        this.psw = psw;
        this.startShell = startShell;
        this.resetShell = resetShell;
        this.breakShell = breakShell;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public String getStartShell() {
        return startShell;
    }

    public void setStartShell(String startShell) {
        this.startShell = startShell;
    }

    public String getResetShell() {
        return resetShell;
    }

    public void setResetShell(String resetShell) {
        this.resetShell = resetShell;
    }

    public String getBreakShell() {
        return breakShell;
    }

    public void setBreakShell(String breakShell) {
        this.breakShell = breakShell;
    }
}
