/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Cloud
 */
public class Server {

    public static void main(String args[]) {
        Socket s = null;
        ServerSocket connect = null;
        System.out.println("Server Listening......");
        try {
            connect = new ServerSocket(8888);
        } catch (IOException e) {
            //  e.printStackTrace();
            System.out.println("Server error");
        }
        int count = 0;
        while (true) {
            try {
                s = connect.accept();
                System.out.println(s.getLocalSocketAddress());
                count++;
                System.out.println("Find connection finish ! Connection to Client " + count);
                ServerThread threa = new ServerThread(s);
                threa.start();

            } catch (Exception e) {
                //  e.printStackTrace();
                System.out.println("Connection Error");
                break;
            }
        }
    }
}
