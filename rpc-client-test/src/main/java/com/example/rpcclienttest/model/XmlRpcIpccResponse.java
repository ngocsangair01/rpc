package com.example.rpcclienttest.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "xml_rpc_response")
public class XmlRpcIpccResponse {

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

    public XmlRpcIpccResponse(String callStatus, String transactionId, String callOutId, String startTime, String duration, String caller, String callRecipient) {
        this.callStatus = callStatus;
        this.transactionId = transactionId;
        this.callOutId = callOutId;
        this.startTime = startTime;
        this.duration = duration;
        this.caller = caller;
        this.callRecipient = callRecipient;
    }
}
