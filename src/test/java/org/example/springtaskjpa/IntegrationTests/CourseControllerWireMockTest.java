package org.example.springtaskjpa.IntegrationTests;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.springframework.test.web.servlet.RequestBuilder;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@SpringBootTest()
@AutoConfigureMockMvc
@ActiveProfiles("test")

public class CourseControllerWireMockTest {

    @Autowired
    private MockMvc mockMvc;

    private static WireMockServer wireMockServer;


    @BeforeAll
    static void setup() {
        wireMockServer = new WireMockServer(8099);
        wireMockServer.start();

        configureFor("localhost", 8099);
    }

    @AfterAll
    static void teardown() {
        wireMockServer.stop();
    }

    @Test
    void getRating_shouldReturnCustomJsonResponse() throws Exception {
        //Stub external API to return custom JSON
        stubFor(WireMock.get(urlEqualTo("/service"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                        [
                             {
                                  "courseId": 1,
                                  "rating": 4.5,
                                  "reviews": 150
                             },
                             {
                                  "courseId": 2,
                                  "rating": 4.7,
                                  "reviews": 120
                             },
                             {
                                  "courseId": 3,
                                  "rating": 4.8,
                                  "reviews": 90
                             },
                             {
                                  "courseId": 4,
                                  "rating": 4.6,
                                  "reviews": 100
                             }
                        ]
                                
                    """)));



        mockMvc.perform(get("/courses/rating").with(httpBasic("admin", "admin123")))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(4)) // 4 courses
                .andExpect(jsonPath("$[0].courseId").value(1))
                .andExpect(jsonPath("$[0].rating").value(4.5))
                .andExpect(jsonPath("$[0].reviews").value(150))
                .andExpect(jsonPath("$[1].courseId").value(2))
                .andExpect(jsonPath("$[1].rating").value(4.7))
                .andExpect(jsonPath("$[1].reviews").value(120));
    }
}
