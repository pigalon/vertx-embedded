package com.alstom.vertx.embedded.server;

import java.util.Map;
import java.util.UUID;

import org.vertx.java.core.AsyncResult;
import org.vertx.java.core.AsyncResultHandler;
import org.vertx.java.core.Handler;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.VertxFactory;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.file.AsyncFile;
import org.vertx.java.core.file.FileProps;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.streams.Pump;
import org.vertx.java.core.streams.ReadStream;
import org.vertx.java.core.streams.WriteStream;
import org.vertx.java.core.http.HttpServerFileUpload;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

//import org.vertx.java.core.VertxFactory;
public class HTTPUploadEmbedded {
	  public static void main(String[] args) throws Exception {

	    final Vertx vertx = VertxFactory.newVertx();
	    HttpServer server = vertx.createHttpServer();

	    server.requestHandler(new Handler<HttpServerRequest>() {
	    	public void handle(final HttpServerRequest request) {
	    		request.expectMultiPart(true);
	    		System.out.println("test upload");
	    		request.uploadHandler(new Handler<HttpServerFileUpload>() {
	    			public void handle(final HttpServerFileUpload upload) {
						final String contentType = upload.contentType();
						final String filename = upload.filename();
						System.out.println("filename : "+ upload.filename());
						/*String fileExtension = upload.filename()
								.substring(
										upload.filename().lastIndexOf(
												"."));
						final String uniqueId = UUID.randomUUID()
								.toString();
						final String uniqueFilename = uniqueId
								+ fileExtension;
						final String uniquePreviewImageUrl = "/img/"
								+ uniqueId + ".png";*/

						final JsonObject result = new JsonObject();
						JsonArray files = new JsonArray();
						result.putArray("files", files);
						final JsonObject file = new JsonObject();
						file.putString("type", contentType);
						file.putString("name", filename);
						//file.putString("uniqueFilename", uniqueFilename);

						files.add(file);

						request.response().putHeader("Content-Type",
								"application/json");

						upload.streamToFileSystem("upload/" + filename);
	    			}

	    		});

	    		/*final String filename = "upload/file-" + UUID.randomUUID().toString() + ".upload";

	    		vertx.fileSystem().open(filename, new AsyncResultHandler<AsyncFile>() {
	    			public void handle(AsyncResult<AsyncFile> ar) {
	    				if (ar.failed()) {
	    	              ar.exception.printStackTrace();
	    	              return;
	    	            }
	    	            final AsyncFile file = ar.result;
	    	            final Pump pump = Pump.createPump((ReadStream)request, (WriteStream)file);
	    	            final long start = System.currentTimeMillis();
	    	            request.endHandler(new Handler<Void>() {
	    	            	public void handle(Void arg) {
	    	                file.close(new AsyncResultHandler<Void>() {
	    	                  public void handle(AsyncResult<Void> ar) {
	    	                    if (ar.succeeded()) {
	    	                      request.response.end();
	    	                      long end = System.currentTimeMillis();
	    	                      System.out.println("Uploaded " + pump.getBytesPumped() + " bytes to " + filename + " in " + (end - start) + " ms");
	    	                    } else {
	    	                      ar.exception.printStackTrace(System.err);
	    	                    }
	    	                  }
	    	                });
	    	              }
	    	            });
	    	            pump.start();
	    	            request.resume();
	          }
	       });*/
	     }
	    }).listen(8080, "localhost");

    // Prevent the JVM from exiting
    System.in.read();
  }
}
