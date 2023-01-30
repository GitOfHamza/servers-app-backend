package com.network.server.service;

import com.network.server._enum.OS;
import com.network.server.model.GeneratorID;
import com.network.server.model.Server;
import com.network.server.repository.ServerRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Random;

import static com.network.server._enum.OS.*;
import static com.network.server._enum.Status.SERVER_UP;
import static com.network.server._enum.Status.SERVER_DOWN;
import static java.lang.Boolean.TRUE;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ServerService implements ServerServiceCore{
    private final ServerRepo serverRepo;

    @Override
    public Server create(Server server) {
        log.info("Saving new server : {}", server.getName());
        server.setId(GeneratorID.getID());
        server.setImageUrl(setImage(server.getType().toUpperCase()));
        return serverRepo.save(server);
    }

    @Override
    public Server ping(String ipAddress) throws IOException {
        log.info("Pinging server IP : {}", ipAddress);
        Server server = serverRepo.findByIpAddress(ipAddress);
        InetAddress address = InetAddress.getByName(ipAddress);
        server.setStatus( address.isReachable(10000) ? SERVER_UP : SERVER_DOWN);
        serverRepo.save(server);
        return server;
    }
    @Override
    public Collection<Server> list(int limit) {
        log.info("Fetching All Servers");
        return serverRepo.findAll( PageRequest.of(0, limit) ).toList();
    }
    @Override
    public Server get(String id) {
        log.info("Fetching Server By ID : {} ", id);
        return serverRepo.findById(id).get();
    }
    @Override
    public Server update(Server server) {
        log.info("Updating server : {}", server.getName());
        server.setImageUrl(setImage(server.getType().toUpperCase()));
        return serverRepo.save(server);
    }
    @Override
    public Boolean delete(String id) {
        log.info("Deleting server Where ID : {}", id );
        serverRepo.deleteById(id);
        return  TRUE;
    }

    private String setImage(String serverType) {
        String imageName = "default_server.png";
        if( serverType.equals(LINUX) ) imageName = "linux.png";
        else if(serverType.equals(MacOS)) imageName = "macOS.png";
        else if(serverType.equals(WINDOWS))  imageName = "windows.png";
        return ServletUriComponentsBuilder.fromCurrentContextPath().path( "/server/image" + imageName ).toUriString();
    }
}