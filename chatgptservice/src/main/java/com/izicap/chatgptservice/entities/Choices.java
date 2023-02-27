package com.izicap.chatgptservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class Choices {

    private String finish_reason;
    private Long index;
    private String text;
    private String logprobs;

}
