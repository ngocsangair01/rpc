package com.example.rpcservertest;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.server.XmlRpcServerConfigImpl;
import org.apache.xmlrpc.webserver.WebServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class DemoController {

    @GetMapping("/xmlrpc")
    public String handleXmlRpcRequest() throws XmlRpcException, IOException {
        // Khởi tạo WebServer và cấu hình cổng
        WebServer webServer = new WebServer(8082);

        // Tạo XmlRpcServer và cấu hình
        XmlRpcServer xmlRpcServer = webServer.getXmlRpcServer();
        PropertyHandlerMapping phm = new PropertyHandlerMapping();

        // Thêm các đối tượng xử lý yêu cầu XML-RPC
        phm.addHandler("DemoController", DemoController.class);
        xmlRpcServer.setHandlerMapping(phm);

        // Cấu hình server
        XmlRpcServerConfigImpl serverConfig = (XmlRpcServerConfigImpl) xmlRpcServer.getConfig();
        serverConfig.setEnabledForExtensions(true);
        serverConfig.setContentLengthOptional(false);

        // Khởi động WebServer
        webServer.start();

        return "XML-RPC server is running on port 8080";
    }

    // Đối tượng xử lý yêu cầu XML-RPC
        public int add(int x, int y) {
            return x + y;
        }

        public int subtract(int x, int y) {
            return x - y;
        }

}