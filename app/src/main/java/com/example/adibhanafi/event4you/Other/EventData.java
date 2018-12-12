package com.example.adibhanafi.event4you.Other;

public class EventData {
    private String name;
    private String date;
    private String address;

    public EventData() {
        //empty constructor needed
    }

    public EventData(String name, String date, String address)
    {
        this.name = name;
        this.date = date;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getAddress() {
        return address;
    }
}
