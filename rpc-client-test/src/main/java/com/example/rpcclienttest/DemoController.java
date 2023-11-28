package com.example.rpcclienttest;

import com.example.rpcclienttest.model.ExecuteCalloutModel;
import com.example.rpcclienttest.service.CalloutHandler;
import org.apache.xmlrpc.XmlRpcException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;

@RestController
public class DemoController {
    private final CalloutHandler calloutHandler;

    public DemoController(CalloutHandler calloutHandler) {
        this.calloutHandler = calloutHandler;
    }

    @GetMapping()
    public ResponseEntity<?> handleXmlRpcRequest(@RequestBody ExecuteCalloutModel model) throws XmlRpcException, MalformedURLException {
        Object res = calloutHandler.executeCallout(model.getIpccUser(), model.getCallingNumber(), model.getTransactionId());
        return ResponseEntity.ok(res);
    }
}
