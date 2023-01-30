package com.network.server.repository;

import com.network.server.model.Server;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ServerRepo extends MongoRepository<Server , String> {
    Server findByIpAddress(String ipAddress);
}
