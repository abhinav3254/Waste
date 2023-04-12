package com.example.waste.database;

public class Pojo {

    String id;
    String title;
    String price;
    String desc;
    String date;


    public Pojo(String id, String title, String price, String desc, String date) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.desc = desc;
        this.date = date;
    }



    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }

    public String getDesc() {
        return desc;
    }

    public String getDate() {
        return date;
    }

    public Pojo(String title, String price, String desc, String date) {
        this.title = title;
        this.price = price;
        this.desc = desc;
        this.date = date;
    }
}
