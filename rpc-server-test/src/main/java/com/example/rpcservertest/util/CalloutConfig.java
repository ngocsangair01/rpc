package com.example.rpcservertest.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CalloutConfig {

    @Value("${call.ipcc.xmlRpcUrl}")
    public  String xmlRpcUrl;

    @Value("${call.ipcc.user}")
    public  String user;

    @Value("${call.ipcc.password}")
    public  String password;

    @Value("${call.ipcc.appCode}")
    public  String appCode;

    @Value("${call.ipcc.methods}")
    public  String methods;

    public CalloutConfig() {
    }

    public CalloutConfig(String xmlRpcUrl, String user, String password, String appCode, String methods) {
        this.xmlRpcUrl = xmlRpcUrl;
        this.user = user;
        this.password = password;
        this.appCode = appCode;
        this.methods = methods;
    }

    public String getXmlRpcUrl() {
        return xmlRpcUrl;
    }

    public void setXmlRpcUrl(String xmlRpcUrl) {
        this.xmlRpcUrl = xmlRpcUrl;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getMethods() {
        return methods;
    }

    public void setMethods(String methods) {
        this.methods = methods;
    }
}
