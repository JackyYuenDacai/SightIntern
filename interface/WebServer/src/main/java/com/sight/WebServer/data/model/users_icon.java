package com.sight.WebServer.data.model;

public class users_icon {
    private String id;

    private String iconId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getIconId() {
        return iconId;
    }

    public void setIconId(String iconId) {
        this.iconId = iconId == null ? null : iconId.trim();
    }
}