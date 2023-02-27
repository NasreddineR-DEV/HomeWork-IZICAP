package com.izicap.chatgptservice.web;

import com.izicap.chatgptservice.entities.ChatGptQuestion;

import com.izicap.chatgptservice.entities.ChatGptApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@FeignClient(name="chat-client", url="https://api.openai.com/v1/completions")
interface ChatGptClient {
    @PostMapping(consumes = "application/json",headers="Authorization=Bearer <!--PUT YOU API KEY->")
    ChatGptApiResponse getResponse(@RequestBody ChatGptQuestion chatGptQuestion);
}


@RestController()
@RequestMapping("api")
public class ChatgptRestController {

    private final ChatGptClient chatGptClient;

    public ChatgptRestController(ChatGptClient chatGptClient) {
        this.chatGptClient = chatGptClient;
    }



    /**
     * Handles requests to chat with the GPT API and stores the conversation in a CSV file.
     *
     * @param question The user's question to ask the GPT API
     * @return The GPT API's response to the user's question
     */
    @GetMapping("/chatgpt")
    public ResponseEntity<ChatGptApiResponse> askChatApi(@RequestParam("question") String question) {
        try {
            // Create the data directory if it doesn't exist
            File dataDir = new File("./data");
            if (!dataDir.exists()) {
                dataDir.mkdir();
            }

            // Create the conversation CSV file if it doesn't exist
            File csvFile = new File("./data/conversation.csv");
            if (!csvFile.exists()) {
                csvFile.createNewFile();
                String header = String.format("%s;%s\n", "Question", "Answer");
                Files.write(csvFile.toPath(), header.getBytes(), StandardOpenOption.APPEND);
            }

            ChatGptQuestion chatGptQuestion = new ChatGptQuestion(question);
            ChatGptApiResponse chatGptApiResponse = chatGptClient.getResponse(chatGptQuestion);

            // Format the conversation as a CSV string, removing any whitespace and line breaks
            String formattedQuestion = question.replaceAll("\\s+"," ");
            String formattedAnswer = chatGptApiResponse.getChoices().get(0).getText().replaceAll("\\s+"," ");
            String csv = String.format("%s;%s\n", formattedQuestion, formattedAnswer);

            // Append the conversation to the CSV file
            Files.write(csvFile.toPath(), csv.getBytes(), StandardOpenOption.APPEND);

            // Return the GPT API's response
            return ResponseEntity.ok(chatGptApiResponse);
        } catch (IOException e) {
            // Handle any I/O exceptions
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (Exception e) {
            // Handle any other exceptions
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }



}
