package com.ibm.amsit.eessi;


import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

import com.github.tomakehurst.wiremock.WireMockServer;


public class GenMock
{
	
	private static WireMockServer wireMockServer;
	
	public static void main(String [] args) 
	{
		if(args.length<3)
			exitError("too few parameters given");
		
		String port=args[0];
		String sourcePattern= args[1];
		String targetPattern= args[2];
		
		wireMockServer = new WireMockServer(options().port(Integer.parseInt(port))); 
		wireMockServer.stubFor(post(urlMatching(sourcePattern)).willReturn(aResponse().proxiedFrom(targetPattern)));
		wireMockServer.start();
		System.out.format("GenMock started on port %s: proxying %s to %s \n", port, sourcePattern, targetPattern);
	}
	
	public static void exitError(String errMsg) {
		System.err.format("GenMock ERROR: %s. usage: GenMock <port> <source pattern> <target pattern> \n", errMsg);
		wireMockServer.stop();
		System.exit(-1);
	}
	
}