package com.izicap.gateway.apigateway.entities;

import lombok.Data;

@Data
public class Usage {

    private Long completion_tokens;
    private Long prompt_tokens;
    private Long total_tokens;

}
