package com.company.client;


import java.io.Serializable;

public class Client implements Serializable {

    private int ID;
    private String name;
    private int numberOfStocksToBuy;
    private int numberOfStocksToSell;
    private int totalNumberOfStocks;
    private float pricePerStockToBuy;
    private float pricePerStockToSell;

    public Client(int ID) {
        this.ID = ID;
    }

    public Client(int ID, String name, int numberOfStocksToBuy, int numberOfStocksToSell, int totalNumberOfStocks, float pricePerStockToBuy, float pricePerStockToSell) {
        this.ID = ID;
        this.name = name;
        this.numberOfStocksToBuy = numberOfStocksToBuy;
        this.numberOfStocksToSell = numberOfStocksToSell;
        this.totalNumberOfStocks = totalNumberOfStocks;
        this.pricePerStockToBuy = pricePerStockToBuy;
        this.pricePerStockToSell = pricePerStockToSell;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfStocksToBuy() {
        return numberOfStocksToBuy;
    }

    public void setNumberOfStocksToBuy(int numberOfStocksToBuy) {
        this.numberOfStocksToBuy = numberOfStocksToBuy;
    }

    public int getNumberOfStocksToSell() {
        return numberOfStocksToSell;
    }

    public void setNumberOfStocksToSell(int numberOfStocksToSell) {
        this.numberOfStocksToSell = numberOfStocksToSell;
    }

    public int getTotalNumberOfStocks() {
        return totalNumberOfStocks;
    }

    public void setTotalNumberOfStocks(int totalNumberOfStocks) {
        this.totalNumberOfStocks = totalNumberOfStocks;
    }

    public float getPricePerStockToBuy() {
        return pricePerStockToBuy;
    }

    public void setPricePerStockToBuy(float pricePerStockToBuy) {
        this.pricePerStockToBuy = pricePerStockToBuy;
    }

    public float getPricePerStockToSell() {
        return pricePerStockToSell;
    }

    public void setPricePerStockToSell(float pricePerStockToSell) {
        this.pricePerStockToSell = pricePerStockToSell;
    }
}
