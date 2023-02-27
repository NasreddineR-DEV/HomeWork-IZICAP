package com.izicap.gateway.apigateway.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.izicap.gateway.apigateway.entities.ChatGptApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

/**
 * Feign client interface for the chatGPT-service.
 */
@FeignClient(name = "chatgpt-service", path = "api")
interface ChatServerClient {

    /**
     * Sends a GET request to the chatgpt endpoint of the chatGPT-service and returns the response.
     * @param question the question to ask the GPT chat API.
     * @return the response from the GPT chat API.
     */
    @GetMapping(value = "/chatgpt", consumes = "application/json")
    ChatGptApiResponse getResponse(@RequestParam("question") String question);
}



@RestController("gateway-api")
@RequestMapping()
public class ChatGatewayRestController {

    private final ChatServerClient chatServerClient;

    private static final Logger logger = LoggerFactory.getLogger(ChatGatewayRestController.class);

    public ChatGatewayRestController(ChatServerClient chatServerClient) {
        this.chatServerClient = chatServerClient;
    }

    /**
     * Handles GET requests to the /chatgpt/ask/{question} endpoint.
     * @param question the question to ask the GPT chat API.
     * @return the answer from the GPT chat API.
     */
    @GetMapping("/chatgpt/ask/{question}")
    public String getAnswer(@PathVariable("question") String question) {
        logger.info("Received request to ask question: {}", question);
        try {
            ChatGptApiResponse chatGptApiResponse = chatServerClient.getResponse(question);
            String answer = chatGptApiResponse.getChoices().get(0).getText();
            logger.info("Received answer from chatGPT-service: {}", answer);
            return answer;
        } catch (Exception e) {
            logger.error("Error occurred while processing the request: {}", e.getMessage());
            throw new RuntimeException("Error occurred while processing the request", e);
        }
    }




}
