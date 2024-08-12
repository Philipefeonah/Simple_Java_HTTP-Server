package com.decagon_institute.Service;

import com.decagon_institute.HttpServer.ResourceLoader;

import java.io.*;
import java.net.Socket;

public class RequestHandlerImpl implements RequestHandler {
    private final Socket clientSocket;        // hold the client socket connection


    public RequestHandlerImpl(Socket clientSocket) {    //initializes the RequestHandlerImpl with the client's socket
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));  // creates a bufferedReader from the input stream of client socket
             BufferedWriter output = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {  // creates a bufferedWriter from the output stream of the client socket

            String requestLine = input.readLine();   // reads the request line from the client

            if (requestLine != null && !requestLine.isEmpty()) {
                String[] requestParts = requestLine.split(" ");
                String method = requestParts[0];
                String path = requestParts[1];
                if ("GET".equals(method)) {
                    handleRequest(path, output);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void handleRequest(String path, BufferedWriter output) throws IOException {  // determines the response based on the request path and then calls the createResponse to generate the response

        String response;
        if ("/".equals(path)) {
            response = createResponse("index.html", "text/html", 200);
        } else if ("/json".equals(path)) {
            response = createResponse("student.json", "application/json", 200);
        } else {
            response = createErrorResponse(404, "Not Found");
        }
        output.write(response);
        output.flush();


    }


    @Override
    public String createResponse(String resourcePath, String contentType, int statusCode) throws IOException { // reads the file content and creates an HTTP response.

        String content = ResourceLoader.loadResource(resourcePath);
        return "HTTP/1.1 " + statusCode + " OK\r\n" + "Content-Type: " + contentType + "\r\n" +
                "Content-Length: " + content.length() + "\r\n" + "\r\n" + content;

    }


    @Override
    public String createErrorResponse(int statusCode, String statusMessage) {
        String errorMessage = "<html><body><h1>" + statusCode + " " + statusMessage + "</h1></body></html>";
        return "HTTP/1.1 " + statusCode + " " + statusMessage + "\r\n" +
                "Content-Type: text/html\r\n "  +
                "Content-Length: " + errorMessage.length() + "\r\n" +
                "\r\n" + errorMessage;
    }

}

