package com.company.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import static java.lang.Double.parseDouble;

public class ServerThread extends Thread {

    private Socket socket;
    private ArrayList<ServerThread> threadList;
    private PrintWriter output;
    public static StockMarket stockMarket;

    public ServerThread (Socket socket, ArrayList<ServerThread> threads, StockMarket stockMarket) {
        this.socket = socket;
        this.threadList = threads;
        this.stockMarket = stockMarket;
    }

    @Override
    public void run () {
        try {
            // Reading input from Client
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            output = new PrintWriter(socket.getOutputStream(), true);

            while (true) {
                String outputString = input.readLine();

                if (outputString.equals("exit")) {
                    break;
                }

                if (outputString.equals("buy")) {
                    handleBuy(input, output);
                    break;
                }

                if (outputString.equals("sell")) {
                    handleSell(input, output);
                    break;
                }

                printToAllClients(outputString);
                System.out.println("Server recieved " + outputString);
            }

        } catch (Exception e) {
            System.out.println("Error occured " + e.getStackTrace());
        }
    }

    private void handleBuy(BufferedReader input, PrintWriter output) throws IOException {
        output.println("Number of stocks: ");
        double number = parseDouble(input.readLine());
        output.println("Price per stocks: ");
        double price = parseDouble(input.readLine());

        stockMarket.buyAt(number, price);
    }

    private void handleSell(BufferedReader input, PrintWriter output) throws IOException {
        output.println("Number of stocks: ");
        double number = parseDouble(input.readLine());
        output.println("Price per stocks: ");
        double price = parseDouble(input.readLine());

        stockMarket.sellAt(number, price);
    }

    private void printToAllClients(String outputString) {
        for (ServerThread serverThread: threadList) {
            serverThread.output.println(outputString);
        }
    }

}
