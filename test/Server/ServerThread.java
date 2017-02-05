/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Cloud
 */
public class ServerThread extends Thread {

    String line = null;
    BufferedReader is = null;
    PrintWriter os = null;
    Socket sock = null;

    public ServerThread(Socket s) {
        this.sock = s;

    }

    public void run() {
        try {
            is = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            os = new PrintWriter(sock.getOutputStream());

        } catch (IOException e) {
            System.out.println("IO EXception in server");
        }

        try {
            line = is.readLine();
            while (line.compareTo("Close") != 0) {
                os.println(line);
                os.flush();
                System.out.println("Rep to Client :  " + line);
                line = is.readLine();
            }
        } catch (IOException e) {
            System.out.println("IO EXception in server");
            // line = this.getName();
            //  System.out.println("IO Error/ Client " + line + " terminated abruptly");
        } catch (NullPointerException e) {
            System.out.println("NullPointerException in server");
            // line = this.getName();
            // System.out.println("Client " + line + " Closed");
        } finally {
            try {
                System.out.println("Connection Closing..");
                if (is != null) {
                    is.close();
                    //  System.out.println(" Socket Input Stream Closed");
                }

                if (os != null) {
                    os.close();
                    // System.out.println("Socket Out Closed");
                }
                if (sock != null) {
                    sock.close();
                    // System.out.println("Socket Closed");
                }

            } catch (IOException ie) {
                System.out.println("Socket Close Error");
            }
        }
    }
}
