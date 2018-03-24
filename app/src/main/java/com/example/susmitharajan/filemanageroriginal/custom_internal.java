package com.example.susmitharajan.filemanageroriginal;

/**
 * Created by susmitharajan on 24/03/18.
 */

public class custom_internal {
    int image;
    String name, team;

    public custom_internal(int image, String name, String team) {
        this.image = image;
        this.name = name;
        this.team = team;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getTeam() {
        return team;
    }
}
