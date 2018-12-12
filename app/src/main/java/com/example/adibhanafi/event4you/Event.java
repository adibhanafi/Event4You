package com.example.adibhanafi.event4you;

public class Event {
    private String name;
    private String date;
    private String address;

    public Event () {
        //empty constructor needed
    }

    public Event(String name, String date, String address)
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
