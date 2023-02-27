package com.izicap.chatgptservice;


import com.izicap.chatgptservice.entities.ChatGptApiResponse;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ChatgptRestControllerTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;


    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    private File conversationFile;

    @Before
    public void setUp() throws Exception {
        conversationFile = tempFolder.newFile("conversation.csv");
    }

    // test method omitted for brevity

    @Test
    public void testAskChatApi() throws IOException {
        String question = "1+1 = ?";
        String expectedQuestion = "1 1 = ?";
        String expectedAnswer = " 1";
        String expectedCsvLine = String.format("%s;%s", expectedQuestion, expectedAnswer);

        ResponseEntity<ChatGptApiResponse> response = restTemplate.getForEntity(
                "http://localhost:" + port + "/api/chatgpt?question=" + question,
                ChatGptApiResponse.class);

        // assert that the response is successful and has the expected body
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(response.getBody().getChoices().get(0).getText(), equalTo("\n\n1"));


        // Read the last line of the CSV file
        // Assert that the last line contains the conversation
        // assert that the CSV file contains the expected line
        List<String> lines = Files.readAllLines(Paths.get("conversation.csv"));
        String lastLine = lines.get(lines.size() - 1);
        assertThat(lastLine, equalTo(expectedCsvLine));

    }

}
