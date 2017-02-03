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
class Client {

    public static void main(String args[]) {
        try {
            Socket skt = new Socket("localhost", 11111);
            BufferedReader in = new BufferedReader(new InputStreamReader(skt.getInputStream()));
            System.out.print("Received string: '");

            while (!in.ready()) {}

                System.out.print(in.readLine());

                System.out.print("'\n");
                in.close();
            }catch (Exception e) {
            System.out.print(" It didn't work!\n");
        }
        }
    }
