package com.company.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Main {

    public static final int PORT = 5000;

    public static void main(String[] args) {

        ArrayList<ServerThread> threadList = new ArrayList<ServerThread>();
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket socket = serverSocket.accept();
                ServerThread serverThread = new ServerThread(socket, threadList);

                threadList.add(serverThread);
                serverThread.start();
            }
        } catch (Exception e) {
            System.out.println("Error occured in main: " + e.getStackTrace());
        }

    }

}
