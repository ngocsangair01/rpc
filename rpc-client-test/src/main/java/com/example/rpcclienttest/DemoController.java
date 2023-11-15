package com.example.rpcclienttest;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfig;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@RestController
public class DemoController {

    @GetMapping
    public ResponseEntity<?> handleXmlRpcRequest() throws XmlRpcException, MalformedURLException {
        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        config.setServerURL(new URL("http://localhost:8088"));
        XmlRpcClient rc = new XmlRpcClient();
        rc.setConfig(config);
        Integer res = (Integer)rc.execute("SERVER.add", List.of(2,3));
        System.out.println(res);
        return ResponseEntity.ok(res);
    }
}
