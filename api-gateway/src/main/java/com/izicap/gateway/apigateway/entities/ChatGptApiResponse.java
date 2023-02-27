package com.izicap.gateway.apigateway.entities;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

import java.net.http.HttpResponse;
import java.util.List;


@Data
public class ChatGptApiResponse {

    private Long created;
    private Usage usage;
    private String model;
    private String id;
    private List<Choices> choices;
    private String object;

}




