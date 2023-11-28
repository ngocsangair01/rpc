package com.example.rpcclienttest.service;

import com.example.rpcclienttest.model.XmlRpcIpccResponse;
import com.example.rpcclienttest.repos.XmlRpcResponseRepo;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.webserver.WebServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class IpccResponseHandler {

    private static final Logger logger = LoggerFactory.getLogger(IpccResponseHandler.class);
    protected static Map<String, XmlRpcIpccResponse> mapResponse = new HashMap<>();


    private final XmlRpcResponseRepo repo;

    public IpccResponseHandler(XmlRpcResponseRepo repo) {
        this.repo = repo;
    }
    @Bean
    public void handleXmlRpcRequest() throws XmlRpcException, IOException {

        WebServer webServer = new WebServer(8089);
        PropertyHandlerMapping phm = new PropertyHandlerMapping();
        // Thêm các đối tượng xử lý yêu cầu XML-RPC
        phm.addHandler("CLIENT", IpccResponseHandler.class);
        webServer.getXmlRpcServer().setHandlerMapping(phm);
        // Khởi động WebServer
        webServer.start();
    }

    public String getResponseIpcc(String callStatus, String transactionId, String callOutId, String startTime, String duration, String deviceId, String caller, String callRecipient) {
        logger.info("Response from Ipcc: callStatus=" + callStatus + "|transactionId=" + transactionId + "|callOutId=" + callOutId + "|startTime=" + startTime + "|duration=" + duration + "|deviceId=" + deviceId);
        repo.save(new XmlRpcIpccResponse(callStatus,transactionId,callOutId,startTime,duration,caller,callRecipient));
        String reCode = "";
        try {
            if (callStatus != null && transactionId != null && callOutId != null && startTime != null && duration != null) {
                String[] status = callStatus.split("\\|");
                XmlRpcIpccResponse objXmlRpcIpcc = new XmlRpcIpccResponse(callStatus, transactionId, callOutId, startTime, duration, caller, callRecipient);
                System.out.println("status.length" + status.length);
                if (status.length != 2) {
                    reCode = "4|CALL_STATUS_NOT_STANDARDIZE";

                    logger.error("Error callStatus not standardize: " + callStatus);
                    return reCode;
                }
                Date startCallDate, endCallDate;
                try {
                    if (Long.parseLong(startTime) > 0) {
                        Timestamp stamp = new Timestamp(Long.parseLong(startTime));
                        startCallDate = new Date(stamp.getTime());
                        endCallDate = new Date(new Timestamp(Long.parseLong(startTime) + Long.parseLong(duration)).getTime());
                    } else {
                        startCallDate = null;
                        endCallDate = null;
                    }
                } catch (NumberFormatException ex) {
                    reCode = "5|DATE_TIME_WRONG_FORMAT";
                    logger.error("Error callStatus not standardize: " + callStatus, ex);
                    return reCode;
                }

                try {
                    Long.valueOf(status[1]);
                } catch (NumberFormatException ex) {
                    reCode = "6|CALL_STATUS_WRONG_FORMAT";
                    logger.error("Error callStatus wrong format: " + status[1], ex);
                    return reCode;
                }

                try {
                    Long.valueOf(duration);
                } catch (NumberFormatException ex) {
                    reCode = "7|DURATION_NOT_NUMBER";
                    logger.error("Error duration wrong format: " + duration, ex);
                    return reCode;
                }

                if (!mapResponse.containsKey(transactionId)) {
                    logger.info("TransactionId: " + transactionId);
                    logger.info("callOutId: " + callOutId);
                    logger.info("startTime: " + startTime);
                    logger.info("callStatus: " + status[1]);

                    mapResponse.put(transactionId, objXmlRpcIpcc);

                    //Insert log:
                    //                    CalloutIpccHistory callout = new CalloutIpccHistory();
                    //                    callout.setTransactionId(transactionId);
                    //                    callout.setCallId(callOutId);
                    //                    callout.setDuration(String.valueOf(Long.valueOf(duration) / 1000));
                    //                    callout.setCallStatus(status[1]);
                    //                    callout.setStartTime(startCallDate);
                    //                    callout.setEndTime(endCallDate);
                    //                    calloutIpccService.updateHistory(callout);
                    logger.info("Cap nhat thong tin cuoc goi thanh cong");
                    if ("0".equals(status[1])) {
                        //                        alarmKpiService.updateCallOutStatus(transactionId);
                        System.out.println("Callout thanh cong. Cap nhat thong tin bang alarm current thanh cong");
                    }
                    mapResponse.remove(transactionId);
                    reCode = "0|SUCCESS";
                    System.out.println("Remove transactionId after insert successfully!");
                } else {
                    reCode = "2|DUPLICATE_TRANSACTIONID";
                    logger.error(reCode);
                }
            } else {
                reCode = "1|MISSING_PARAMETER";
                logger.error(reCode);
            }
        } catch (Exception ex) {
            logger.error("Error response from Ipcc: " + transactionId + ex);
            throw ex;
        } finally {
            mapResponse.remove(transactionId);
        }

        return reCode;
    }
}
