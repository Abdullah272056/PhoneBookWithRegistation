package com.example.phonebookwithregistation;

public class Notes {

    int id ;
    String name ,location,phoneNumber;

    public Notes() {
    }

    public Notes(int id) {
        this.id = id;
    }

    public Notes(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Notes(String location, String phoneNumber) {
        this.location = location;
        this.phoneNumber = phoneNumber;
    }

    public Notes(String name, String location, String phoneNumber) {
        this.name = name;
        this.location = location;
        this.phoneNumber = phoneNumber;
    }

    public Notes(int id, String name, String location, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
