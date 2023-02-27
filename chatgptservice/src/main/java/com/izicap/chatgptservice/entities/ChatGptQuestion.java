package com.izicap.chatgptservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data @AllArgsConstructor
public class ChatGptQuestion {
    private String prompt;
    private final String model = "text-davinci-003";
    private final int max_tokens = 250;
    private final double temperature = 0.6;

}
