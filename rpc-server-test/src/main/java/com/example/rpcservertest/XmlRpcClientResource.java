package com.example.rpcservertest;

import com.example.rpcservertest.util.CalloutConfig;
import com.example.rpcservertest.util.CalloutUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/xml-rpc-to-client")
public class XmlRpcClientResource {


    private final CalloutUtils calloutUtils;

    public XmlRpcClientResource(CalloutUtils calloutUtils) {

        this.calloutUtils = calloutUtils;
    }

    @GetMapping
    public ResponseEntity<?> handleXmlRpcRequest(String ipccUser, String callingNumber, String transactionId) {
        Object res1 = calloutUtils.executeCallout(ipccUser, callingNumber, transactionId);
        return ResponseEntity.ok(res1);
    }
}
