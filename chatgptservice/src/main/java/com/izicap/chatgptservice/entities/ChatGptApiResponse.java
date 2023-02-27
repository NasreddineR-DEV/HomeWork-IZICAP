package com.izicap.chatgptservice.entities;

import lombok.Data;

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


