package com.example.rpcclienttest;

import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;
import java.net.URL;

//@Configuration
//public class XmlRpcConfiguration {
//    @Bean
//    public XmlRpcClientConfigImpl xmlRpcClientConfigImpl() {
//        XmlRpcClientConfigImpl xmlRpcClientConfig = new XmlRpcClientConfigImpl();
//        try {
//            xmlRpcClientConfig.setServerURL(new URL("http://localhost:8081/xmlrpc"));
//            return new XmlRpcClientConfigImpl();
//        } catch (MalformedURLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}