package com.company.server;

import com.company.models.Stock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentMap;

import static java.lang.Double.parseDouble;

public class ServerThread extends Thread {

    private Socket socket;
    private ArrayList<ServerThread> threadList;
    private PrintWriter output;
    public static StockMarket stockMarket;

    public ServerThread(Socket socket, ArrayList<ServerThread> threads, StockMarket stockMarket) {
        this.socket = socket;
        this.threadList = threads;
        this.stockMarket = stockMarket;
    }

    @Override
    public void run() {
        try {
            // Reading input from Client
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            output = new PrintWriter(socket.getOutputStream(), true);

            while (true) {
                String outputString = input.readLine();
                if (outputString.equals("exit")) {
                    System.out.println("...client exited");
                    break;
                }

                if (outputString.equals("buy")) {
                    System.out.println("...server handles buy request");
                    handleBuy(input, output);
                }

                if (outputString.equals("sell")) {
                    System.out.println("...server handles sell request");
                    handleSell(input, output);
                }

                if (outputString.equals("trades")) {
                    handleTrades(input, output);
                }

                if (outputString.equals("history")) {
                    handleHistory(input, output);
                }

                System.out.println();
            }

        } catch (Exception e) {
            System.out.println("Error occured in server thread (run): " + e.toString());
        }
    }

    private void handleBuy(BufferedReader input, PrintWriter output) throws IOException {

        String name = input.readLine();
        double number = parseDouble(input.readLine());
        double price = parseDouble(input.readLine());

        System.out.print("Number of stocks: ");
        System.out.println(number);
        System.out.print("Price per stock: ");
        System.out.println(price);

        stockMarket.buyAt(name, number, price);
    }

    private void handleSell(BufferedReader input, PrintWriter output) throws IOException {

        String name = input.readLine();
        double number = parseDouble(input.readLine());
        double price = parseDouble(input.readLine());

        System.out.print("Number of stocks: ");
        System.out.println(number);
        System.out.print("Price per stock: ");
        System.out.println(price);

        stockMarket.sellAt(name, number, price);
    }

    private void handleTrades(BufferedReader input, PrintWriter output) throws IOException {
        ConcurrentMap<Double, ArrayList<Stock>> offers = stockMarket.getOffers();
        ConcurrentMap<Double, ArrayList<Stock>> requests = stockMarket.getRequests();
        output.println("------ OFFERS ------");
        for(double key: offers.keySet()) {
            output.print("Offers for price: " + key + ": ");
            output.println(offers.get(key));
        }
        output.println("------ REQUESTS ------");
        for(double key: requests.keySet()) {
            output.print("Offers for price: " + key + ": ");
            output.println(requests.get(key));
        }
        output.println("last");
    }

    private void handleHistory(BufferedReader input, PrintWriter output) throws IOException {
        ArrayList<String> history = stockMarket.getHistory();
        output.println("------ HISTORY OF TRADES ------");
        for (String s : history) {
            output.println(s.toString());
        }
        output.println("last");
    }

    private void printToAllClients(String outputString) {
        for (ServerThread serverThread : threadList) {
            serverThread.output.println(outputString);
        }
    }

}
