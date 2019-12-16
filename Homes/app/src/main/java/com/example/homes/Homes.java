package com.example.homes;

import java.io.Serializable;

public class Homes {

    private int imgHome;
    private String description;

    public Homes(int imgHome, String description) {
        this.imgHome = imgHome;
        this.description = description;
    }

    public int getImgHome() {
        return imgHome;
    }

    public String getDescription() {
        return description;
    }
}
