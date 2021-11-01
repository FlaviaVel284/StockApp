package com.company.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Main {

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
            ClientThread clientThread = new ClientThread(socket);
            clientThread.start();

            do {
                if (clientName.equals("empty")) {
                    System.out.println("Enter your name ");
                    userInput = scanner.nextLine();
                    clientName = userInput;
                    if (userInput.equals("exit")) {
                        break;
                    }
                } else {
                    String message = ( "(" + clientName + ")" + " message: " );
                    System.out.println(message);
                    userInput = scanner.nextLine();
                    output.println(message + userInput);
                    if (userInput.equals("exit")) {
                        break;
                    }
                }
            } while (!userInput.equals("exit"));

        } catch (Exception e) {
            System.out.println("Exception occured in client main: " + e.getStackTrace());
        }
    }

}
