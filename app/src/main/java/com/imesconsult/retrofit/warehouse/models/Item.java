package com.imesconsult.retrofit.warehouse.models;

public class Item {
    public String name;
    public String manufacturer;
    public int quantity;
    public float price;

    @Override
    public String toString() {
        return name + " (" + manufacturer + ") qnt: " + quantity + " prc: "+price;
    }

    public Item(String name, String manufacturer, int quantity, float price) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.quantity = quantity;
        this.price = price;
    }
}
