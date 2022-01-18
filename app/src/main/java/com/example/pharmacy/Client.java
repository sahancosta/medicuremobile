package com.example.pharmacy;

public class Client {

    private Integer idclient;


    private String cname;

    private String cemail;

    private String caddress;

    private ClientLogin clientLogin;

    public Integer getIdclient() {
        return idclient;
    }

    public void setIdclient(Integer idclient) {
        this.idclient = idclient;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCemail() {
        return cemail;
    }

    public void setCemail(String cemail) {
        this.cemail = cemail;
    }

    public String getCaddress() {
        return caddress;
    }

    public void setCaddress(String caddress) {
        this.caddress = caddress;
    }

    public ClientLogin getClientLogin() {
        return clientLogin;
    }

    public void setClientLogin(ClientLogin clientLogin) {
        this.clientLogin = clientLogin;
    }

    public Client(String cname, String cemail, String caddress, ClientLogin clientLogin) {
        this.cname = cname;
        this.cemail = cemail;
        this.caddress = caddress;
        this.clientLogin = clientLogin;
    }
}

