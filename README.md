# Simple Java HTTP Server

## This project implements a simple HTTP server using raw Java sockets. The server can handle multiple requests concurrently using threads. It has two main endpoints:

/ : Returns an HTML page.
/json : Returns a JSON object.

Testing the Endpoints
Open a web browser and navigate to http://localhost:9080/ to see the HTML page.
Navigate to http://localhost:9080/json to see the JSON response.

## Code Explanation

HttpServer: This class sets up the server, binds it to a port, and listens for client connections. When a client connects, a new RequestHandler thread is started to handle the request.


RequestHandler: This class implements the Runnable interface. It reads the client's request, determines the requested path, and sends the appropriate response back to the client. It handles the root (/) and JSON (/json) endpoints.


ResourceLoader: This class provides a method to load resource files (HTML and JSON) from the classpath and return their content as a string.


HttpServerTest: This class contains JUnit tests for the server. It tests the root and JSON endpoints by sending GET requests and verifying the responses.


### Conclusion
This project demonstrates a simple multithreaded HTTP server implemented using raw Java sockets. It handles GET requests to the root (/) and JSON (/json) endpoints, serving static HTML and JSON content respectively. The server correctly responds with HTTP status codes and content types, and can handle multiple simultaneous requests using threads.

Feel free to clone the repository, explore the code, and run the tests to see the server in action! If you have any questions or suggestions, please open an issue or submit a pull request.
