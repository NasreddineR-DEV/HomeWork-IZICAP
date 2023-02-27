package com.izicap.gateway.apigateway.entities;

import lombok.Data;

@Data
public class Choices {

    private String finish_reason;
    private Long index;
    private String text;
    private String logprobs;

}
