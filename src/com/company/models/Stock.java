package com.company.models;

public class Stock {

    private String name;
    private double number;
    private double price;

    public Stock() {}

    public Stock(String name, double number, double price) {
        this.name = name;
        this.number = number;
        this.price = price;
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
        return Double.compare(stock.number, number) == 0;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "name=" + name +
                ", number=" + number +
                ", price=" + price +
                '}';
    }
}
