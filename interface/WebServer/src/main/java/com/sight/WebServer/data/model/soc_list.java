package com.sight.WebServer.data.model;

public class soc_list {
    private String soc;

    private String name;

    public String getSoc() {
        return soc;
    }

    public void setSoc(String soc) {
        this.soc = soc == null ? null : soc.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}