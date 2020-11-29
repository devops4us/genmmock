package com.ibm.amsit.eessi;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;



public class RestClient {
	
	private Client client;
	private WebTarget target;
	
	public RestClient(String restUrl) throws Exception {
		this.client= ClientBuilder.newClient();
		this.target= this.client.target(restUrl);
	}
	
	public void postJson(File jsonFile) throws Exception {		
		byte[] bytes= Files.readAllBytes(Paths.get(jsonFile.getAbsolutePath()));
		String jsonContent= new String(bytes, "utf-8");
		this.target.request(MediaType.APPLICATION_JSON)
		.post(Entity.json(jsonContent));
	}

}
