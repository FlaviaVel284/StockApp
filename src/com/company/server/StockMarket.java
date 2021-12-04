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
                        history.add(offer.getName() + " sold to " + request.toString() + " 1");
                        offer.setNumber(offer.getNumber() - request.getNumber());
                        stockIterator.remove();
                        //request.setNumber(0.0);
                    } else if(offer.getNumber() < request.getNumber()){
                        history.add(offer.toString() + " sold to " + request.getName() + " 2");
                        request.setNumber(request.getNumber() - offer.getNumber());
                        offersIterator.remove();
                        //offer.setNumber(0.0);
                    } else if(offer.getNumber() == request.getNumber()){
                        history.add(offer.toString() + " sold to " + request.getName() + " 3");
                        stockIterator.remove();
                        offersIterator.remove();
                        //request.setNumber(0.0);
                        //offer.setNumber(0.0);
                    }
                }
            }
        }
    }

    void buyAt(String name, double number, double price) {
        requests.add(new Stock(name, number, price));
        try {
            Thread.sleep(5000);
        } catch(Exception e){
            System.out.println(e);
        }
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
