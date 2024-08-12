package com.decagon_institute.HttpServer;


import com.decagon_institute.Service.RequestHandlerImpl;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

//Responsible for starting the server and listening to incoming connections
public class HttpServer {

    private static final int PORT = 9080;

    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(PORT)) { // initialize the server to listen on the specified port

            System.out.println("HTTP Server started on port " + PORT);

            while (true){
                Socket clientSocket = serverSocket.accept();   // waits for an incoming connection and returns a new socket object for the client connection
                new Thread(new RequestHandlerImpl(clientSocket)).start(); // creates and start a new thread to handle the client's request using RequestHandlerImpl
                System.out.println("new thread just started from a client");
            }
        }catch (IOException exception){
            throw new RuntimeException();
        }
    }

}
