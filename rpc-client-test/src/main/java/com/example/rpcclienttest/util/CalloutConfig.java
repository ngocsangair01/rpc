package com.example.rpcclienttest.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class CalloutConfig {

    @Value("${call.ipcc.xmlRpcUrl}")
    private String xmlRpcUrl;

    @Value("${call.ipcc.user}")
    private String user;

    @Value("${call.ipcc.password}")
    private String password;

    @Value("${call.ipcc.appCode}")
    private String appCode;

    @Value("${call.ipcc.methods}")
    private String methods;
}
