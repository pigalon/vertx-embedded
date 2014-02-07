package com.alstom.vertx.embedded.server;

import java.util.Map;

import org.vertx.java.core.Handler;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.VertxFactory;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.http.HttpServerRequest;
//import org.vertx.java.core.VertxFactory;
public class HTTPEmbedded {
	  public static void main(String[] args) throws Exception {

	    Vertx vertx = VertxFactory.newVertx();
	    HttpServer server = vertx.createHttpServer();

	    server.requestHandler(new Handler<HttpServerRequest>() {
	        public void handle(HttpServerRequest request) {
	        	System.out.println("Got request: " + request.uri());
	            System.out.println("Headers are: ");
	        	StringBuilder sb = new StringBuilder();
	            for (Map.Entry<String, String> header: request.headers().entries()) {
	                sb.append(header.getKey()).append(": ").append(header.getValue()).append("\n");
	            }
	            request.response().headers().set("Content-Type", "application/json; charset=UTF-8");
	            request.response().end("{'id':'1'}");
	        }
	    }).listen(8080, "localhost");

	    // Prevent the JVM from exiting
	    System.in.read();

	  }
	}
