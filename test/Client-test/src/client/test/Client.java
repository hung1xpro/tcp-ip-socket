/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Cloud
 */
public class Client {

    public static void main(String args[]) throws IOException {

        InetAddress address = InetAddress.getLocalHost();
        Socket sock = null;
        String line = null;
        BufferedReader br = null;
        BufferedReader is = null;
        PrintWriter os = null;
        Scanner sc = new Scanner(System.in);
        try {
            while (true) {
                try {
                    System.out.print("Please Input IP : ");
                    String ip = sc.next();
                    int port;
                    do {
                        System.out.print("Port: ");

                        while (!sc.hasNextInt()) {
                            System.out.print("    Port( Please enter number): ");
                            sc.next();
                        }
                        port = sc.nextInt();

                    } while (port <= 0);
                    sock = new Socket(ip, port);
                    sc.nextLine();
                    System.out.println("done");
                    break;
                } catch (Exception e) {
                    System.out.println("Please Input again");
                }

            }
            //  sock = new Socket("127.0.0.1", 8888);
            br = new BufferedReader(new InputStreamReader(System.in));
            is = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            os = new PrintWriter(sock.getOutputStream());
        } catch (IOException e) {
            //  e.printStackTrace();
            System.err.print("Please Run Server First");
        }

        try {
            if (br != null) {
                String response = null;
                System.out.println("Client Address : " + address);

                do {
                    System.out.print("Enter message to server (type Close to end):");
                    do {
                        line = br.readLine();
                        os.println(line);
                    } while (line.compareTo(".") != 0);

                    os.flush();
                    do {
                        response = is.readLine();
                        if (response.compareTo(".") != 0) {
                            System.out.println("Server said : " + response);
                        }

                    } while (response.compareTo(".") != 0);
                } while (line.compareTo("Close") != 0);
            }
        } catch (IOException e) {
            // e.printStackTrace();
            System.out.println("Socket Error");
        } finally {
            if (is != null) {
                is.close();
            }
            if (os != null) {
                os.close();
            }
            if (br != null) {
                br.close();
            }
            if (sock != null) {
                sock.close();
            }

            System.out.println("Connection Closed");

        }

    }
}
