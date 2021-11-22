package com.company.server;

import com.company.models.Stock;

import java.util.ArrayList;

public class StockMarket {

    private ArrayList<Stock> offers;
    private ArrayList<Stock> requests;

    public StockMarket() {
        this.offers = new ArrayList<>();
        this.requests = new ArrayList<>();
    }

    void buyAt(double number, double price) {
        /*if(!offers.isEmpty())
            for (Stock s : offers) {
                if (s.getPrice() == price) {
                    if (s.getNumber() > number) {
                        s.setNumber(s.getNumber() - number);
                        System.out.println(number + " stocks bought at " + price);
                    } else if (s.getNumber() < number) {
                        System.out.println("Not enough stocks, only " + s.getNumber() + " stocks bought at " + price);
                        requests.add(new Stock(number - s.getNumber(), price));
                        offers.remove(s);
                    } else {
                        System.out.println("All " + number + " stocks bought at " + price);
                        offers.remove(s);
                    }
                    break;
                } else {
                    requests.add(new Stock(number, price));
                }
            }
        else
            requests.add(new Stock(number, price));*/
        requests.add(new Stock(number, price));
    }

    void sellAt(double number, double price) {
        /*if(!requests.isEmpty())
            for (Stock s : requests) {
                if (s.getPrice() == price) {
                    if (s.getNumber() > number) {
                        s.setNumber(s.getNumber() - number);
                        System.out.println("All " + number + " stocks sold at " + price);
                    } else if (s.getNumber() < number) {
                        System.out.println("Not enough stocks, only " + s.getNumber() + " stocks sold at " + price);
                        offers.add(new Stock(number - s.getNumber(), price));
                        requests.remove(s);
                    } else {
                        System.out.println("All " + number + " stocks sold at " + price);
                        requests.remove(s);
                    }
                    break;
                } else {
                    offers.add(new Stock(number, price));
                }
            }
        else
            offers.add(new Stock(number, price));*/
        offers.add(new Stock(number, price));
    }

    public ArrayList<Stock> getOffers() {
        return offers;
    }

    public ArrayList<Stock> getRequests() {
        return requests;
    }
}
