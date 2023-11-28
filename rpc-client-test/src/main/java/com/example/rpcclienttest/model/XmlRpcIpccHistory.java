package com.viettel.cso.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(name = "ipcc_history")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XmlRpcIpccHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "call_status")
    private String callStatus;

    @Column(name = "transactione_id")
    private String transactionId;

    @Column(name = "call_out_id")
    private String callOutId;

    @Column(name = "start_time")
    private String startTime;

    @Column(name = "duration")
    private String duration;

    @Column(name = "caller")
    private String caller;

    @Column(name = "call_recipient")
    private String callRecipient;

    public XmlRpcIpccHistory() {

    }
    public XmlRpcIpccHistory(String callStatus, String transactionId, String callOutId, String startTime, String duration, String caller, String callRecipient) {
        this.callStatus = callStatus;
        this.transactionId = transactionId;
        this.callOutId = callOutId;
        this.startTime = startTime;
        this.duration = duration;
        this.caller = caller;
        this.callRecipient = callRecipient;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCallStatus() {
        return callStatus;
    }

    public void setCallStatus(String callStatus) {
        this.callStatus = callStatus;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getCallOutId() {
        return callOutId;
    }

    public void setCallOutId(String callOutId) {
        this.callOutId = callOutId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getCaller() {
        return caller;
    }

    public void setCaller(String caller) {
        this.caller = caller;
    }

    public String getCallRecipient() {
        return callRecipient;
    }

    public void setCallRecipient(String callRecipient) {
        this.callRecipient = callRecipient;
    }
}
