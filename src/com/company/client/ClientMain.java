package com.company.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ClientMain {

    public static final String ADDRESS = "localhost";
    public static final int PORT = 5000;

    public static void main(String[] args) {
        try (Socket socket = new Socket(ADDRESS, PORT)) {

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);
            String userInput;
            String response;
            String clientName = "empty";

            do {
                if (clientName.equals("empty")) {
                    System.out.print("Enter your name: ");
                    userInput = scanner.nextLine();
                    clientName = userInput;
                    if (userInput.equals("exit")) {
                        break;
                    }
                } else {
                    System.out.print("Enter your option (buy/sell/trades/history/edit/exit): ");
                    userInput = scanner.nextLine();

                    if (userInput.equals("exit")) {
                        output.println("exit");
                    }

                    if (userInput.equals("buy")) {
                        System.out.print("Enter the price you want to buy at: ");
                        String priceInput = scanner.nextLine();
                        System.out.print("Enter the quantity you want to buy: ");
                        String quantityInput = scanner.nextLine();

                        output.println("buy");
                        output.println(clientName);
                        output.println(quantityInput);
                        output.println(priceInput);
                    }

                    if (userInput.equals("sell")) {
                        System.out.print("Enter the price you want to sell at: ");
                        String priceInput = scanner.nextLine();
                        System.out.print("Enter the quantity you want to sell: ");
                        String quantityInput = scanner.nextLine();

                        output.println("sell");
                        output.println(clientName);
                        output.println(quantityInput);
                        output.println(priceInput);
                    }

                    if (userInput.equals("trades")) {
                        output.println("trades");
                        String trade = input.readLine();
                        while (!trade.equals("last")) {
                            System.out.println(trade);
                            trade = input.readLine();
                        }
                    }

                    if (userInput.equals("history")) {
                        output.println("history");
                        String trade = input.readLine();
                        while (!trade.equals("last")) {
                            System.out.println(trade);
                            trade = input.readLine();
                        }
                    }

                    if (userInput.equals("edit")) {
                        output.println("edit");
                        output.println(clientName);

                        String trade = input.readLine();
                        while (!trade.equals("last")) {
                            System.out.println(trade);
                            trade = input.readLine();
                        }

                        String branch = input.readLine();
                        System.out.println(branch);
                        if(!Objects.equals(branch, "You have no stocks on the market.")){
                            System.out.print("Enter the number for the stock you want to edit: ");
                            String stockNumber = scanner.nextLine();
                            output.println(stockNumber);
                            System.out.println("-----Editing the stock no " + stockNumber + "-----");
                            System.out.print("Enter the new price: ");
                            String price = scanner.nextLine();
                            System.out.print("Enter the new quantity: ");
                            String quantity = scanner.nextLine();

                            output.println(price);
                            output.println(quantity);
                        }
                    }

                    System.out.println();
                }
            } while (!userInput.equals("exit"));

        } catch (Exception e) {
            System.out.println("Exception in client main: " + e);
        }
    }

}
