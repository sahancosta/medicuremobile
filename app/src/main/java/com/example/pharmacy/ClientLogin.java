package com.example.pharmacy;

public class ClientLogin {
    private Integer clientid;
    private String clientusername;
    private String clientpassword;
    private int clientstatus;

    public ClientLogin() {
    }

    public ClientLogin(String clientusername, String clientpassword) {
        this.clientid = clientid;
        this.clientusername = clientusername;
        this.clientpassword = clientpassword;
        this.clientstatus = clientstatus;
    }

    public Integer getClientid() {
        return clientid;
    }

    public void setClientid(Integer clientid) {
        this.clientid = clientid;
    }

    public String getClientusername() {
        return clientusername;
    }

    public void setClientusername(String clientusername) {
        this.clientusername = clientusername;
    }

    public String getClientpassword() {
        return clientpassword;
    }

    public void setClientpassword(String clientpassword) {
        this.clientpassword = clientpassword;
    }

    public int getClientstatus() {
        return clientstatus;
    }

    public void setClientstatus(int clientstatus) {
        this.clientstatus = clientstatus;
    }
}
