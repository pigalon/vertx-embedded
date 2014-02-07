package com.alstom.vertx.embedded.server;

import org.vertx.java.core.Handler;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.VertxFactory;
//import org.vertx.java.core.VertxFactory;
import org.vertx.java.core.net.NetSocket;
import org.vertx.java.core.streams.Pump;

public class Embedded {
	  public static void main(String[] args) throws Exception {

	    Vertx vertx = VertxFactory.newVertx();

	    // Create an echo server
	    vertx.createNetServer().connectHandler(new Handler<NetSocket>() {
	      public void handle(final NetSocket socket) {
	        Pump.createPump(socket, socket).start();
	      }
	    }).listen(1234);

	    // Prevent the JVM from exiting
	    System.in.read();

	  }
	}
