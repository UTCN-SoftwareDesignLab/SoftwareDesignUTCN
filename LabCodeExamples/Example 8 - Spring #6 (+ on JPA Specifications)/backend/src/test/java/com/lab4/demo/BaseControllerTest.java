package com.lab4.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

public abstract class BaseControllerTest {
    protected MockMvc mockMvc;

    @BeforeEach
    protected void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    protected ResultMatcher jsonContentToBe(Object expectedJsonContent) throws JsonProcessingException {
        return content().json(new ObjectMapper().writeValueAsString(expectedJsonContent), true);
    }

    protected ResultActions performPostWithRequestBody(String path, Object body) throws Exception {
        return mockMvc.perform(jsonType(post(path)
                .content(asJsonString(body)))
        );
    }

    protected ResultActions performPatchWithRequestBodyAndPathVariable(String path, Object body, Object pathVariable) throws Exception {
        return mockMvc.perform(jsonType(patch(path, pathVariable)
                .content(asJsonString(body)))
        );
    }

    protected ResultActions performPutWithRequestBodyAndPathVariable(String path, Object body, Object pathVariable) throws Exception {
        return mockMvc.perform(jsonType(put(path, pathVariable)
                .content(asJsonString(body)))
        );
    }

    protected ResultActions performDeleteWIthPathVariable(String path, Object pathVariable) throws Exception {
        return mockMvc.perform(jsonType(delete(path, pathVariable)));
    }

    protected ResultActions performGetWithPathVariable(String path, Object pathVariable) throws Exception {
        return mockMvc.perform(jsonType(get(path, pathVariable)));
    }


    protected String asJsonString(final Object obj) {
        if (obj instanceof String) return String.valueOf(obj);
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private RequestBuilder jsonType(MockHttpServletRequestBuilder content) {
        return content.contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON);
    }
}
