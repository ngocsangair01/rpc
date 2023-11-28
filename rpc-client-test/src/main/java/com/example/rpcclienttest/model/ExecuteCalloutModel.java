package com.example.rpcclienttest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExecuteCalloutModel {
    private String ipccUser;
    private String callingNumber;
    private String transactionId;
}
