package com.company.server;

import com.company.client.Client;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread extends Thread {

    private Socket socket;
    private ArrayList<ServerThread> threadList;
    private PrintWriter output;

    public ServerThread (Socket socket, ArrayList<ServerThread> threads) {
        this.socket = socket;
        this.threadList = threads;
    }

    @Override
    public void run () {
        try {
            // Reading input from Client
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            //output = new PrintWriter(socket.getOutputStream(), true);

            while (true) {
                Client outputClient = (Client)inputStream.readObject();

                System.out.println("Server recieved " + outputClient);
            }

        } catch (Exception e) {
            System.out.println("Error occured " + e.getStackTrace());
        }
    }

    private void printToAllClients(String outputString) {
        for (ServerThread serverThread: threadList) {
            serverThread.output.println(outputString);
        }
    }

}
