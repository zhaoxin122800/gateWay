package com.icbc.gateway.pojo;

public class GTH {

    private String id;
    private String url;
    private String name;
    private String psw;
    private String startShell;
    private String resetShell;
    private String breakShell;

    public GTH() {
        super();
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
