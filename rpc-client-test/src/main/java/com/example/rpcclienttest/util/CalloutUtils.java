package com.example.rpcclienttest;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@Service
public class CalloutUtils {

    public static Object sendXmlRpc(String url, String method, Object[] params) throws Exception {
        Object res;
        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        config.setReplyTimeout(6000);
        config.setConnectionTimeout(3000);
        config.setServerURL(new URL(url));
        XmlRpcClient client = new XmlRpcClient();
        client.setConfig(config);

        res = client.execute(method, params);

        return res;
    }


}
