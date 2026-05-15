package chatapp;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        try {

            Socket socket = new Socket("localhost", 5000);

            BufferedReader input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            PrintWriter output = new PrintWriter(
                    socket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);

            Thread readThread = new Thread(() -> {
                try {
                    String msg;

                    while ((msg = input.readLine()) != null) {
                        System.out.println(msg);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            readThread.start();

            System.out.println("Connected to chat server");
            System.out.println("Type messages:");

            while (true) {

                String message = scanner.nextLine();
                output.println(message);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}