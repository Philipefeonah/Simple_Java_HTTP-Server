package com.decagon_institute.Service;

import java.io.BufferedWriter;
import java.io.IOException;

public interface RequestHandler extends Runnable{

    void run();

    void handleRequest(String path, BufferedWriter output) throws IOException;

    String createResponse(String filePath, String contentType, int statusCode) throws IOException;

    String createErrorResponse(int statusCode, String statusMessage);

}
