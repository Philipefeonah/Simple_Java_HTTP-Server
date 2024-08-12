package com.decagon_institute.HttpServer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/*
Reads the file content from the specified path and returns it as a String.
 */
public class ResourceLoader {

    public static String loadResource(String resourcePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get("src/main/resources/" + resourcePath)));
    }
}
