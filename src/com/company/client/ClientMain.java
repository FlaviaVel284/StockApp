package com.company.client;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientMain {

    public static final String ADDRESS = "localhost";
    public static final int PORT = 5000;

    public static void main(String[] args) {
        try (Socket socket = new Socket(ADDRESS, PORT)) {

            //BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

            Scanner scanner = new Scanner(System.in);
            String response;
            Client client = new Client(1);
            ClientThread clientThread = new ClientThread(socket);
            clientThread.start();

            String userInput = null;
            do {
                if (client.getName() == null) {
                    System.out.println("Enter your client details ");
                    System.out.println("Name: ");
                    userInput = scanner.nextLine();
                    client.setName(userInput);
                    System.out.println("Number of stocks to buy: ");
                    userInput = scanner.nextLine();
                    client.setNumberOfStocksToBuy(Integer.parseInt(userInput));
                    System.out.println("Price per stock to buy: ");
                    userInput = scanner.nextLine();
                    client.setPricePerStockToBuy(Float.parseFloat(userInput));
                    System.out.println("Number of stocks to sell: ");
                    userInput = scanner.nextLine();
                    client.setNumberOfStocksToSell(Integer.parseInt(userInput));
                    System.out.println("Price per stock to sell: ");
                    userInput = scanner.nextLine();
                    client.setPricePerStockToSell(Float.parseFloat(userInput));
                    if (userInput.equals("exit")) {
                        break;
                    }
                } else {
                    String message = ( "(" + client.getName() + ")" + " message: " );
                    System.out.println(message);
                    outputStream.writeObject(client);
                }
            } while (!userInput.equals("exit"));

        } catch (Exception e) {
            System.out.println("Exception occured in client main: " + e.getStackTrace());
        }
    }

}
