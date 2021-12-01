package com.company.models;

import java.util.Objects;
import java.sql.Timestamp;

public class Stock {

    private String name;
    private double number;
    private double price;
    private Timestamp timestamp;

    public Stock() {}

    public Stock(String name, double number, double price) {
        this.name = name;
        this.number = number;
        this.price = price;
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

    public String getName() {return name; }

    public void setName(String name) {this.name = name; }

    public double getNumber() {
        return number;
    }

    public void setNumber(double number) {
        this.number = number;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return Double.compare(stock.number, number) == 0 && Double.compare(stock.price, price) == 0 && Objects.equals(name, stock.name) && Objects.equals(timestamp, stock.timestamp);
    }

    @Override
    public String toString() {
        return "Stock{" +
                "name=" + name +
                ", number=" + number +
                ", price=" + price +
                ", timestamp=" + timestamp +
                '}';
    }
}
