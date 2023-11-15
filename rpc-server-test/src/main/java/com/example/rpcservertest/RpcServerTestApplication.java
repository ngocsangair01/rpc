package com.example.rpcservertest;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.server.XmlRpcServerConfigImpl;
import org.apache.xmlrpc.webserver.WebServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class RpcServerTestApplication {
	public Integer add(int x, int y){
		return x + y;
	}

	public String echo(String input){
		return input;
	}
	public static void main(String[] args) throws XmlRpcException, IOException {
		SpringApplication.run(RpcServerTestApplication.class, args);
		WebServer webServer = new WebServer(8088);
		PropertyHandlerMapping phm = new PropertyHandlerMapping();
		// Thêm các đối tượng xử lý yêu cầu XML-RPC
		phm.addHandler("SERVER", RpcServerTestApplication.class);
		webServer.getXmlRpcServer().setHandlerMapping(phm);
		// Khởi động WebServer
		webServer.start();
	}

}
