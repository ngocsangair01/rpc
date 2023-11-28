package com.example.rpcservertest;

import com.example.rpcservertest.util.CalloutUtils;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.webserver.WebServer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RpcController {

    public Object dialCallOut(String urlRes, String methodRes,String transactionId,String callingNumber,String ipccUser,String user,String password,String appCode) {
        System.out.println(urlRes);
        System.out.println(methodRes);
        System.out.println(transactionId);
        System.out.println(callingNumber);
        System.out.println(ipccUser);
        System.out.println(user);
        System.out.println(password);
        System.out.println(appCode);
        return "Success";
    }



    @Bean
    public void handleXmlRpcRequest() throws XmlRpcException, IOException {

        WebServer webServer = new WebServer(8088);
        PropertyHandlerMapping phm = new PropertyHandlerMapping();
        // Thêm các đối tượng xử lý yêu cầu XML-RPC
        phm.addHandler("IPCCCalloutGW", RpcController.class);
        webServer.getXmlRpcServer().setHandlerMapping(phm);
        // Khởi động WebServer
        webServer.start();
    }

}