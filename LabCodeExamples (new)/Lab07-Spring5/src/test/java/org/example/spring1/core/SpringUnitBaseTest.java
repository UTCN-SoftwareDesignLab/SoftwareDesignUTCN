package org.example.spring1.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;

public class SpringUnitBaseTest {
  private final ObjectMapper objectMapper;

  public SpringUnitBaseTest() {
    objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
  }

  @BeforeEach
  public void initMocks() {
    MockitoAnnotations.openMocks(this);
  }

  public String toJson(Object obj) throws JsonProcessingException {
    return objectMapper.writeValueAsString(obj);
  }
}
