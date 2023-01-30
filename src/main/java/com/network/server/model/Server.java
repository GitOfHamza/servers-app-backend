package com.network.server.model;

import com.network.server._enum.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;

@Document(collection ="Server")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Server {
    @Id
    private String id;
    @NotEmpty( message = "The @IP cannot be empty or null" )
    private String ipAddress;
    private String name;
    private String memory;
    private String type;
    private String imageUrl;
    private Status status;
}
