package org.example.spring1.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.data.util.Pair;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

public class SpringControllerBaseTest extends SpringUnitBaseTest {
  protected MockMvc mvc;

  @BeforeEach
  public void setUp() {
    // any setup
  }

  protected MockMvc buildForController(Object controller) {
    return MockMvcBuilders.standaloneSetup(controller)
        .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
        .build();
  }


  // following methods are used to perform different types of requests

  protected ResultActions performPost(String path) throws Exception {
    return mvc.perform(
        post(path).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
  }

  protected ResultActions performPostWithRequestBody(String path, Object body) throws Exception {
    return mvc.perform(
        post(path)
            .content(asJsonString(body))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON));
  }

  protected ResultActions performPutWithRequestBody(String path, Object body) throws Exception {
    return mvc.perform(
        put(path)
            .content(asJsonString(body))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON));
  }

  protected ResultActions performMultipartFilePost(String path, MockMultipartFile file)
      throws Exception {
    return mvc.perform(multipart(path).file(file));
  }

  protected ResultActions performMultipartFilePostWithPathVariables(
      String path, MockMultipartFile file, Object... pathVariables) throws Exception {
    return mvc.perform(multipart(path, pathVariables).file(file));
  }

  protected ResultActions performMultipartFilePostWithParams(
      String path, MockMultipartFile file, List<Pair<String, String>> params) throws Exception {
    final MockMultipartHttpServletRequestBuilder multipart = multipart(path);
    params.forEach(param -> multipart.param(param.getFirst(), param.getSecond()));
    return mvc.perform(multipart.file(file));
  }

  protected ResultActions performPostWithRequestBodyAndPathVariable(
      String path, Object body, Object pathVariable) throws Exception {
    return mvc.perform(
        post(path, pathVariable)
            .content(asJsonString(body))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON));
  }

  protected ResultActions performPostWithPathVariableAndParams(
      String path, String pathVariable, List<Pair<String, String>> params) throws Exception {
    MockHttpServletRequestBuilder requestBuilder = post(path, pathVariable);
    params.forEach(param -> requestBuilder.param(param.getFirst(), param.getSecond()));
    return mvc.perform(requestBuilder);
  }

  protected ResultActions performPostWithPathVariableAndContent(
      String path, Object pathVariable, Object content) throws Exception {
    return mvc.perform(
        post(path, pathVariable)
            .content(toJson(content))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON));
  }

  protected ResultActions performGet(String path) throws Exception {
    return mvc.perform(get(path));
  }

  protected ResultActions performGet(String path, Object... variables) throws Exception {
    return mvc.perform(get(path, variables));
  }

  protected ResultActions performGetWithPathVariables(String path, Object... pathVariables)
      throws Exception {
    return mvc.perform(get(path, pathVariables));
  }

  protected ResultActions performGetWithParamsAndPathVariables(
      String path, List<Pair<String, String>> params, Object... pathVariables) throws Exception {
    MockHttpServletRequestBuilder requestBuilder = get(path, pathVariables);
    params.forEach(param -> requestBuilder.param(param.getFirst(), param.getSecond()));
    return mvc.perform(requestBuilder);
  }

  protected ResultActions performGetWithModelAttributeAndParams(
      String path, Pair<String, Object> modelAttribute, List<Pair<String, String>> params)
      throws Exception {
    MockHttpServletRequestBuilder requestBuilder = get(path);
    params.forEach(param -> requestBuilder.param(param.getFirst(), param.getSecond()));
    requestBuilder.flashAttr(modelAttribute.getFirst(), modelAttribute.getSecond());
    return mvc.perform(requestBuilder);
  }

  protected ResultActions performGetWithModelAttributeAndParamsAndPathVariables(
      String path,
      Pair<String, Object> modelAttribute,
      List<Pair<String, String>> params,
      Object... pathVariables)
      throws Exception {
    MockHttpServletRequestBuilder requestBuilder = get(path, pathVariables);
    params.forEach(param -> requestBuilder.param(param.getFirst(), param.getSecond()));
    requestBuilder.flashAttr(modelAttribute.getFirst(), modelAttribute.getSecond());
    return mvc.perform(requestBuilder);
  }

  protected ResultActions performGetWithPathVariableModelAttributeAndParams(
      String path,
      Object pathVariable,
      Pair<String, Object> modelAttribute,
      List<Pair<String, String>> params)
      throws Exception {
    MockHttpServletRequestBuilder requestBuilder = get(path, pathVariable);
    params.forEach(param -> requestBuilder.param(param.getFirst(), param.getSecond()));
    requestBuilder.flashAttr(modelAttribute.getFirst(), modelAttribute.getSecond());
    return mvc.perform(requestBuilder);
  }

  protected ResultActions performGetWithParams(
      String path, List<Pair<String, String>> pathVariables) throws Exception {
    MockHttpServletRequestBuilder requestBuilder = get(path);
    pathVariables.forEach(
        pathVariable -> requestBuilder.param(pathVariable.getFirst(), pathVariable.getSecond()));
    return mvc.perform(requestBuilder);
  }

  protected ResultActions performGetWithPathVariableAndParams(
      String path, Object pathVariable, List<Pair<String, String>> params) throws Exception {
    MockHttpServletRequestBuilder requestBuilder = get(path, pathVariable);
    params.forEach(param -> requestBuilder.param(param.getFirst(), param.getSecond()));
    return mvc.perform(requestBuilder);
  }

  protected ResultActions performPutWithPathVariables(String path, Object... pathVariables)
      throws Exception {
    return mvc.perform(put(path, pathVariables));
  }

  protected ResultActions performPutWithPathVariableAndRequestBody(
      String path, Object pathVariable, Object body) throws Exception {
    return mvc.perform(
        put(path, pathVariable)
            .content(asJsonString(body))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON));
  }

  protected ResultActions performPatchWithPathVariableAndRequestBody(
      String path, Object pathVariable, Object body) throws Exception {
    return mvc.perform(
        patch(path, pathVariable)
            .content(asJsonString(body))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON));
  }

  protected ResultActions performPutWithPathVariableAndParams(
      String path, Object pathVariable, List<Pair<String, String>> params) throws Exception {
    final MockHttpServletRequestBuilder requestBuilder = put(path, pathVariable);
    params.forEach(param -> requestBuilder.param(param.getFirst(), param.getSecond()));
    return mvc.perform(
        requestBuilder.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
  }

  protected ResultActions performDelete(String path) throws Exception {
    return mvc.perform(delete(path));
  }

  protected ResultActions performDeleteWithPathVariables(String path, Object... pathVariable)
      throws Exception {
    return mvc.perform(delete(path, pathVariable));
  }

  protected ResultActions performDeleteWithParams(String path, List<Pair<String, String>> params)
      throws Exception {
    MockHttpServletRequestBuilder requestBuilder = delete(path);
    params.forEach(param -> requestBuilder.param(param.getFirst(), param.getSecond()));
    return mvc.perform(requestBuilder);
  }

  protected ResultActions performDeleteWithModelAttribute(
      String path, Pair<String, Object> modelAttribute) throws Exception {
    MockHttpServletRequestBuilder requestBuilder = delete(path);
    requestBuilder.flashAttr(modelAttribute.getFirst(), modelAttribute.getSecond());
    return mvc.perform(requestBuilder);
  }

  protected ResultActions performDeleteWithPathVariablesAndParams(
      String path, List<Pair<String, String>> params, Object... pathVariable) throws Exception {
    MockHttpServletRequestBuilder requestBuilder = delete(path, pathVariable);
    params.forEach(param -> requestBuilder.param(param.getFirst(), param.getSecond()));
    return mvc.perform(requestBuilder);
  }

  protected ResultActions performPostWithParams(String path, List<Pair<String, String>> params)
      throws Exception {
    MockHttpServletRequestBuilder requestBuilder = post(path);
    params.forEach(param -> requestBuilder.param(param.getFirst(), param.getSecond()));
    return mvc.perform(requestBuilder);
  }

  protected ResultMatcher contentToBe(Object content) throws JsonProcessingException {
    return content().json(toJson(content), true);
  }

  protected String asJsonString(final Object obj) {
    try {
      ObjectMapper mapper = new ObjectMapper();
      mapper.registerModule(new JavaTimeModule());
      return mapper.writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
