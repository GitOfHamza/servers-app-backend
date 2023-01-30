package com.network.server.service;

import com.network.server.model.Server;

import java.io.IOException;
import java.util.Collection;

public interface ServerServiceCore {
    Server create(Server server);
    Server ping(String ipAddress) throws IOException;
    Collection<Server> list(int limit);
    Server get(String id);
    Server update(Server server);
    Boolean delete(String id);
}
