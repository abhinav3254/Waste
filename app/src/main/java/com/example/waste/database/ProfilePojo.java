package com.example.waste.database;

public class ProfilePojo {
    String id;
    String name;
    String amount;

    public ProfilePojo(String id, String name, String amount) {
        this.id = id;
        this.name = name;
        this.amount = amount;
    }

    public ProfilePojo(String name, String amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAmount() {
        return amount;
    }
}
