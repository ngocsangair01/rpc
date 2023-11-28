package com.example.rpcclienttest.repos;

import com.example.rpcclienttest.model.XmlRpcIpccResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface XmlRpcResponseRepo extends JpaRepository<XmlRpcIpccResponse, Long> {
}
