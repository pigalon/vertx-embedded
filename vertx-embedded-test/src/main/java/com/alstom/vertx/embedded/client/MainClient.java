package com.alstom.vertx.embedded.client;

import org.vertx.java.core.Handler;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.http.HttpClient;
import org.vertx.java.core.http.HttpClientRequest;
import org.vertx.java.core.http.HttpClientResponse;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class MainClient {

	public static void main(String[] args) throws Exception {
		//Vertx vertx  = Vertx.newVertx(8080, "localhost");
		/*HttpClient client = vertx.createHttpClient().setPort(8080).setHost("localhost");

		HttpClientRequest request = client.get("/",  new Handler<HttpClientResponse>(){
		    public void handle(HttpClientResponse resp) {
		    	System.out.println("Got a response: " + resp.statusCode);
		    }

		});
		request.end();*/

		HttpResponse<JsonNode> jsonResponse = Unirest.post("http://localhost:8080")
				  .header("accept", "application/json")
				  .field("parameter", "value")
				  .field("foo", "bar")
				  .asJson();

		System.out.println(jsonResponse.getBody().toString());

	}

}
