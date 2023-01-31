package com.network.server.controller;

import com.network.server._enum.Status;
import com.network.server.model.Response;
import com.network.server.model.Server;
import com.network.server.service.ServerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.springframework.http.MediaType.*;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/server")
@RequiredArgsConstructor
public class ServerController {
    private final ServerService serverService;
    @GetMapping("/list")
    public ResponseEntity<Response> getAllServers() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("servers" , serverService.list(30)))
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .message("Servers retrieved")
                        .build()
        );
    }

    @GetMapping("/ping/{IP}")
    public ResponseEntity<Response> pingServer(@PathVariable( "IP" ) String IP) throws IOException {
        Server server = serverService.ping(IP);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("server" , server))
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .message( server.getStatus() == Status.SERVER_UP ? "Ping Success" : "Ping Failed" )
                        .build()
        );
    }

    @PostMapping("save")
    public ResponseEntity<Response> saveServer(@RequestBody @Valid Server server) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("server" , serverService.create(server)))
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .message( "Server Created" )
                        .build()
        );
    }

    @GetMapping("/get/{ID}")
    public ResponseEntity<Response> getServer(@PathVariable( "ID" ) String ID) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("server" , serverService.get(ID)))
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .message( "Server Retrieved" )
                        .build()
        );
    }

    @DeleteMapping("/delete/{ID}")
    public ResponseEntity<Response> deleteServer(@PathVariable( "ID" ) String ID) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("server" , serverService.delete(ID)))
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .message( "Server Deleted" )
                        .build()
        );
    }

    @GetMapping( path = { "/image/{fileName}" } , produces = IMAGE_PNG_VALUE)
    public byte[] getServerImage(@PathVariable("fileName") String fileName) throws IOException{
        return Files.readAllBytes(Paths.get("C:/Users/HAMZA/Desktop/FullStackWeb-Manage Servers/images/"+fileName));
    }
}
