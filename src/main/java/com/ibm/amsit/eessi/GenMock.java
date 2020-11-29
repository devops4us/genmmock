package com.ibm.amsit.eessi;


import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

import java.io.File;

import com.github.tomakehurst.wiremock.WireMockServer;

/***
 * 
 * @author GottfriedLuef
 *
 * in "PROXY" mode, the WireMock server forwards all requests to a target server and returns the
 * target response as it is to the caller.
 * 
 * in "MOCK" mode, the WireMock server loads the available request mappings and starts WireMock server.
 * The WireMock server will respond to all requests using the loaded mappings.
 *  
 *
 */

public class GenMock
{
	
	private static WireMockServer wireMockServer;
	
	public static void main(String [] args) 
	{		
		if(args.length<1)
			exitError("no parameters given");
		
		String command=args[0];
		if("PROXY".equals(command))
		{
			if(args.length<4)
				exitError("too few parameters given");
			
			String port=args[1];
			String sourcePattern= args[2];
			String targetPattern= args[3];
			
			// start proxy server
			wireMockServer = new WireMockServer(options().port(Integer.parseInt(port))); 
			wireMockServer.stubFor(post(urlMatching(sourcePattern)).willReturn(aResponse().proxiedFrom(targetPattern)));
			wireMockServer.start();
			System.out.format("GenMock PROXY started on port %s, proxying %s to %s \n", port, sourcePattern, targetPattern);			
		}
		else if("MOCK".equals(command)) {
			
			if(args.length < 2)
				exitError("too few parameters given");
			String port= args[1];
			
			// start mock server
			wireMockServer = new WireMockServer(options().port(Integer.parseInt(port))); 
			wireMockServer.start();
			
		    // load request mappings from file "./requestmappings.json and send to server"
			try {
				new RestClient(String.format("http://localhost:%s/__admin/mappings/import", port))
				.postJson(new File("./config/requestmappings.json"));
				System.out.format("GenMock MOCK started on port %s \n", port);
			} catch (Exception e) {
				e.printStackTrace();
				exitError(String.format("unable to load file ./config/requestmappings.json: %s",e.getMessage()));
			}			
		}
		else exitError(String.format("not a valid command: %s",command));
		
	}
	
	public static void exitError(String errMsg) {
		System.err.format("GenMock ERROR: %s. usage: GenMock PROXY|MOCK <port> <source pattern> <target pattern> \n", errMsg);
		wireMockServer.stop();
		System.exit(-1);
	}
	
}