package com.example.microServiceNoSQl.Model.Utilities;

import java.util.ArrayList;

public class newTopic {

    private String id;

    private String name;

    private String description;

    private ArrayList<dataInfoPair> nameType;

    private ArrayList<String> color;

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<String> getColor() {
        return color;
    }

    public ArrayList<dataInfoPair> getNameType() {
        return nameType;
    }

    public String getName() {
        return name;
    }
}
