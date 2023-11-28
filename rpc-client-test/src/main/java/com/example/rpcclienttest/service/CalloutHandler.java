package com.example.rpcclienttest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalloutHandler {


    private static final Logger logger = LoggerFactory.getLogger(CalloutUtils.class);

    private final CalloutConfig calloutConfig;

    public CalloutHandler(CalloutConfig calloutConfig) {
        this.calloutConfig = calloutConfig;
    }


    public Object executeCallout(String ipccUser, String callingNumber, String transactionId) {
        String methodRes = "GET";
        String urlRes = "http://localhost:8080/api/ipcc-callback";
        //luu transactionId vao DB de sau nay map voi cuoc goi IPCC tra ve
        String methodReq = calloutConfig.getMethods();
        logger.info("urlRes :" + urlRes);
        logger.info("methodRes :" + methodRes);
        logger.info("transactionId :" + transactionId);
        logger.info("callingNumber :" + callingNumber);
        logger.info("ipccUser.toLowerCase() :" + ipccUser.toLowerCase());
        logger.info("calloutConfig.getUser() :" + calloutConfig.getUser());
        logger.info("calloutConfig.getPassword() :" + calloutConfig.getUser());
        logger.info("calloutConfig.getAppCode() :" + calloutConfig.getAppCode());
        Object[] params = new Object[]{urlRes, methodRes, transactionId, callingNumber, ipccUser.toLowerCase(), calloutConfig.getUser(), calloutConfig.getPassword(), calloutConfig.getAppCode(),};
        Object result = null;
        List<String> listUrl = List.of(this.calloutConfig.getXmlRpcUrl().split(","));
        if (!listUrl.isEmpty()) {
            for (String url : listUrl) {
                try {
                    if (url != null && !url.isEmpty()) {
                        logger.info("Ket noi den agent server lan " + listUrl.indexOf(url) + ", url = " + url + "\n");
                        result = CalloutUtils.sendXmlRpc(url, methodReq, params);
                        logger.info("Ket qua " + result);
                        if (result != null && !result.toString().equals("4")) {
                            break;
                        }
                    }
                } catch (Exception e) {
                    logger.info("Ket noi den agent server that bai");
                    logger.error(e.getMessage(), e);
                }
            }
        }
        return result;
    }
}
