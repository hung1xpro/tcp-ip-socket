/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

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

        try {
            sock = new Socket(address, 8888);
            br = new BufferedReader(new InputStreamReader(System.in));
            is = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            os = new PrintWriter(sock.getOutputStream());
        } catch (IOException e) {
          //  e.printStackTrace();
            System.err.print("IO EXception");
        }

        System.out.println("Client Address : " + address);
        System.out.println("Enter message to server (type Close to end):");

        String response = null;
        try {
            line = br.readLine();
            while (line.compareTo("Close") != 0) {
                os.println(line);
                os.flush();
                response = is.readLine();
                System.out.println("Server said : " + response);
                line = br.readLine();
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
