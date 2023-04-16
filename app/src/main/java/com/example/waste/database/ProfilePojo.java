package com.example.waste.database;

public class ProfilePojo {
    String id;
    String name;

    public ProfilePojo(String name) {
        this.name = name;
    }

    public ProfilePojo(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
