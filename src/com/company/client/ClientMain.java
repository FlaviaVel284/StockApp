package com.company.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

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
                    System.out.print("Enter your option (buy/sell/trades/exit): ");
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
                        output.println(quantityInput);
                        output.println(priceInput);
                    }

                    if (userInput.equals("sell")) {
                        System.out.print("Enter the price you want to sell at: ");
                        String priceInput = scanner.nextLine();
                        System.out.print("Enter the quantity you want to sell: ");
                        String quantityInput = scanner.nextLine();
                        output.println("sell");
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
                    System.out.println();
                }
            } while (!userInput.equals("exit"));

        } catch (Exception e) {
            System.out.println("Exception occured in client main: " + e.getStackTrace());
        }
    }

}
