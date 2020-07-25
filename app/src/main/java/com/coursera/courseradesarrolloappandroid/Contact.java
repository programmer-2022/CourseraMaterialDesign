package com.coursera.courseradesarrolloappandroid;

import java.io.Serializable;

public class Contact implements Serializable {

    private String name;
    private String date;
    private String phone;
    private String email;
    private String description;

    public Contact() { }

    public Contact(String name, String date, String phone, String email, String description) {
        this.name = name;
        this.date = date;
        this.phone = phone;
        this.email = email;
        this.email = email;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}