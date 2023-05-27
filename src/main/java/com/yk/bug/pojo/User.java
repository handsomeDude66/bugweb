package com.yk.bug.pojo;


public class User {
    private String name;
    private String inv;
    private int id;

    public User() {
    }

    public User(String name, String inv, int id) {
        this.name = name;
        this.inv = inv;
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", inv='" + inv + '\'' +
                ", id=" + id +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInv() {
        return inv;
    }

    public void setInv(String inv) {
        this.inv = inv;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
