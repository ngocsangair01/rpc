package com.example.rpcservertest.util;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.List;

@Service
public class CalloutUtils {

    private static final Logger logger = LoggerFactory.getLogger(CalloutUtils.class);

    private final CalloutConfig calloutConfig;

    public CalloutUtils(CalloutConfig calloutConfig) {
        this.calloutConfig = calloutConfig;
    }


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
        String callStatus = "Example call status";
        String callOutId = "Example call out id";
        String startTime = "Example start time";
        String duration = "Example duration";
        String deviceId = "Example device id";
        Object[] params = new Object[] {
                callStatus,
                transactionId,
                callOutId,
                startTime,
                duration,
                deviceId
        };
        Object result = null;
        List<String> listUrl = List.of(this.calloutConfig.getXmlRpcUrl().split(","));
        if (!listUrl.isEmpty()) {
            for (String url : listUrl) {
                try {
                    if (url != null && !url.isEmpty()) {
                        logger.info("Ket noi den agent server lan " + listUrl.indexOf(url) + ", url = " + url + "\n");
                        result = sendXmlRpc(url, methodReq, params);
                        logger.info("Ket qua " + result);
                        if (result != null && !result.toString().equals("4")) {
                            break;
                        }
                    }
                } catch (Exception e) {
                    logger.info("Ket noi den agentserver that bai");
                    logger.error(e.getMessage(), e);
                }
            }
        }
        return result;
    }

}
