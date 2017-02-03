/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket.tcpip;

import java.lang.*;
import java.io.*;
import java.net.*;

/**
 *
 * @author Cloud
 */
class Server {

    public static void main(String args[]) {
        String data = "Cloud";
        try {
            ServerSocket srvr = new ServerSocket(1234);
            Socket skt = srvr.accept();
            System.out.print("Server has connected!\n");
            PrintWriter out = new PrintWriter(skt.getOutputStream(), true);
            System.out.print("Sending string: '" + data + "'\n");
            out.print(data);
            out.close();
            skt.close();
            srvr.close();
        } catch (Exception e) {
            System.out.print("It didn't work!\n");
        }
    }
}
