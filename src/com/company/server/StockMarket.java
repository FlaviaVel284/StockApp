package com.company.server;

import com.company.models.Stock;

import java.util.ArrayList;
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
        ArrayList<Stock> requestsToDelete = new ArrayList<>();
        ArrayList<Stock> offersToDelete = new ArrayList<>();
        for(Stock request: requests){
            if(offers.containsKey(request.getPrice())) {
                ArrayList<Stock> specificOffers = offers.get(request.getPrice());
                for (Stock offer : specificOffers) {
                    if (offer.getNumber() >= request.getNumber()) {
                        history.add(request.toString() + " bought from " + offer.getName());
                        offer.setNumber(offer.getNumber() - request.getNumber());
                        requestsToDelete.add(request);
                    } else {
                        history.add(offer.toString() + " sold to " + request.getName());
                        request.setNumber(request.getNumber() - offer.getNumber());
                        offersToDelete.add(offer);
                    }
                }
                specificOffers.removeAll(offersToDelete);
            }
        }
        requests.removeAll(requestsToDelete);
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
