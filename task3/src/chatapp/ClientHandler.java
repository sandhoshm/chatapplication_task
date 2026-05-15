package chatapp;

import java.io.*;
import java.net.*;

public class ClientHandler implements Runnable {

    Socket socket;
    BufferedReader input;
    PrintWriter output;

    public ClientHandler(Socket socket) {
        this.socket = socket;

        try {
            input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            output = new PrintWriter(socket.getOutputStream(), true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        String message;

        try {
            while ((message = input.readLine()) != null) {

                System.out.println("Message Received: " + message);

                for (ClientHandler client : Server.clients) {

                    client.output.println(message);
                }
            }

        } catch (Exception e) {
            System.out.println("Client disconnected");
        }
    }
}