package com.example.susmitharajan.filemanageroriginal;

/**
 * Created by susmitharajan on 24/03/18.
 */

public class custom_internal {
    int image;
    String name;
    String modified;
    String path;

    public custom_internal(int image, String name, String modified,String path) {
        this.image = image;
        this.name = name;
        this.modified = modified;
        this.path = path;
    }

    public int getImage() {
        return image;
    }

    public String getPath(){
        return path;
    }

    public String getName() {
        return name;
    }

    public String getModified() {
        return modified;
    }
}
