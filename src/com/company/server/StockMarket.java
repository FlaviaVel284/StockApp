package com.company.server;

import com.company.models.Stock;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class StockMarket {

    private ArrayList<Stock> requests;
    private ConcurrentMap<Double, ArrayList<Stock>> offers;
    private ArrayList<String> history;

    public StockMarket() {
        this.offers = new ConcurrentHashMap<>();
        this.requests = new ArrayList<>();
        this.history = new ArrayList<>();
    }

    void processTrades(){
        Iterator<Stock> stockIterator = requests.iterator();
        while(stockIterator.hasNext()){
            Stock request = stockIterator.next();
            if(offers.containsKey(request.getPrice())) {
                Iterator<Stock> offersIterator = offers.get(request.getPrice()).iterator();
                while(offersIterator.hasNext()) {
                    Stock offer = offersIterator.next();
                    if (offer.getNumber() > request.getNumber()) {
                        offer.setNumber(offer.getNumber() - request.getNumber());
                        history.add(offer.getName() + " sold to " + request.getName() + ": " + request.getNumber() + " stocks at " + request.getPrice() + "$.");
                        stockIterator.remove();
                    } else if(offer.getNumber() < request.getNumber()){
                        history.add(offer.getName() + " sold to " + request.getName() + ": " + offer.getNumber() + " stocks at " + request.getPrice() + "$.");
                        request.setNumber(request.getNumber() - offer.getNumber());
                        offersIterator.remove();
                    } else {
                        history.add(offer.toString() + " sold to " + request.getName() + ": " + offer.getNumber() + " stocks at " + request.getPrice() + "$.");
                        stockIterator.remove();
                        offersIterator.remove();
                    }
                }
            }
        }
    }

    void buyAt(String name, double number, double price) {
        requests.add(new Stock(name, number, price));
        processTrades();
    }

    void sellAt(String name, double number, double price) {
        offers.putIfAbsent(price, new ArrayList<Stock>());
        offers.get(price).add(new Stock(name, number, price));
        processTrades();
    }

    public ConcurrentMap<Double, ArrayList<Stock>> getOffers() {
        return offers;
    }

    public ArrayList<Stock> getRequests() {
        return requests;
    }

    public ArrayList<String> getHistory() { return history; }
}
