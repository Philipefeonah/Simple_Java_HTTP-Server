package com.decagon_institute.HttpServer;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;


class HttpServerTest {

    @Test
    void testIndex_HTML_EndPoint() throws IOException{        // test the '/' endpoint

        URL url = new URL("http://localhost:9080/");
        HttpURLConnection connection =(HttpURLConnection) url.openConnection(); //opens connection to http://localhost:4090/
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        assertEquals(200, responseCode);            // checks that the response code is 200 and the content type is a text/html
        assertEquals("text/html", connection.getContentType());

        String responseBody = new String(connection.getInputStream().readAllBytes());
        String expectedHtml = Files.readString(Paths.get("src/main/resources/index.html"));
        assertEquals(expectedHtml, responseBody);  // compares the response body with the expected HTML content read from the index.html

    }

    @Test
    void testJSON_Endpoint() throws IOException{
        String url = "http://localhost:9080/json";
        HttpURLConnection connection =(HttpURLConnection) new URL(url).openConnection(); //opens connection to http://localhost:4090/json
        connection.setRequestMethod("GET");


        int responseCode = connection.getResponseCode();
        assertEquals(200, responseCode);            // checks that the response code is 200 and the content type is application/json
        assertEquals("application/json", connection.getContentType());

        String responseBody = new String(connection.getInputStream().readAllBytes());
        String expectedJson =  Files.readString(Paths.get("src/main/resources/student.json"));
        assertEquals(expectedJson, responseBody);  // compares the response body with the expected JSON content read from the student.json

    }

}