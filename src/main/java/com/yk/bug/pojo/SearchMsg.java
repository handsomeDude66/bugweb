package com.yk.bug.pojo;

public class SearchMsg {
    private int id;
    private String path;
    private double price;
    private String commodity;

    public SearchMsg() {}
    public SearchMsg(int id,  String commodity, double price, String path ) {
        this.path = path;
        this.price = price;
        this.commodity = commodity;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCommodity() {
        return commodity;
    }

    public void setCommodity(String commodity) {
        this.commodity = commodity;
    }
}
