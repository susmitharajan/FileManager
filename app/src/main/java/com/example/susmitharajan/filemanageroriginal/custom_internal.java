package com.example.susmitharajan.filemanageroriginal;

/**
 * Created by susmitharajan on 24/03/18.
 */

public class custom_internal {
    int image;
    String name;
    String modified;

    public custom_internal(int image, String name, String modified) {
        this.image = image;
        this.name = name;
        this.modified = modified;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getModified() {
        return modified;
    }
}
