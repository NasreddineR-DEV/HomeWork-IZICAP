package com.izicap.gateway.apigateway;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ChatGatewayRestControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;


    // test method omitted for brevity

    @Test
    public void getAnswerTest() throws IOException {
        // Define the question to ask the API
        String question = "1+1 = ?";

        // Send a GET request to the API and capture the response
        ResponseEntity<String> response = restTemplate.getForEntity(
                "http://localhost:" + port + "/chatgpt/ask/" + question,
                String.class);

        // Assert that the response status code is HTTP 200 OK
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

        // Assert that the response body matches the expected answer (in this case, "\n2")
        assertThat(response.getBody(), equalTo("\n2"));
    }

}
