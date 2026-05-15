package chatapp;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server {

    static Vector<ClientHandler> clients = new Vector<>();

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Server started...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");

                ClientHandler client = new ClientHandler(socket);
                clients.add(client);

                Thread thread = new Thread(client);
                thread.start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}