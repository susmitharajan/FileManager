package com.example.susmitharajan.filemanageroriginal;

/**
 * Created by susmitharajan on 28/03/18.
 */

public class HomeModel {
    int image;
    String name;

    public HomeModel(int image, String name) {
        this.image = image;
        this.name = name;

    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }
}
