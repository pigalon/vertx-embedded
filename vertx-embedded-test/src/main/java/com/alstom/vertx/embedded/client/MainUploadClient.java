package com.alstom.vertx.embedded.client;

import java.io.File;

import org.vertx.java.core.Handler;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.http.HttpClient;
import org.vertx.java.core.http.HttpClientRequest;
import org.vertx.java.core.http.HttpClientResponse;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class MainUploadClient {

	public static void main(String[] args) throws Exception {

		File file = new File("./tmp/file.txt");
		System.out.println(file.getAbsolutePath());

		HttpResponse<JsonNode> jsonResponse = Unirest.post("http://localhost:8080")
				  .header("accept", "application/json")
				  //.field("parameter", "value")
				  .field("file", file)
				  .asJson();

		System.out.println(jsonResponse.getBody().toString());

	}

}
