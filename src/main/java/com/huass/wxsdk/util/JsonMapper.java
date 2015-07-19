package com.huass.wxsdk.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JsonMapper
{
  private ObjectMapper mapper;

  public JsonMapper()
  {
    this(null);
  }

  public JsonMapper(JsonInclude.Include include) {
    this.mapper = new ObjectMapper();

    if (include != null) {
      this.mapper.setSerializationInclusion(include);
    }

    this.mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
  }

  public static JsonMapper nonEmptyMapper()
  {
    return new JsonMapper(JsonInclude.Include.NON_EMPTY);
  }

  public static JsonMapper nonDefaultMapper()
  {
    return new JsonMapper(JsonInclude.Include.NON_DEFAULT);
  }

  public static JsonMapper getInstance() {
    return new JsonMapper(JsonInclude.Include.NON_DEFAULT).enableSimple();
  }

  public String toJson(Object object)
  {
    try
    {
      return this.mapper.writeValueAsString(object); } catch (IOException e) {
    }
    return null;
  }

  public JavaType createCollectionType(Class<?> collectionClass, Class<?>[] elementClasses)
  {
    return this.mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
  }

  public <T> T update(String jsonString, T object)
  {
    try
    {
      return this.mapper.readerForUpdating(object).readValue(jsonString);
    } catch (JsonProcessingException e) {
    } catch (IOException e) {
    }
    return null;
  }

  public String toJsonP(String functionName, Object object)
  {
    return toJson(new JSONPObject(functionName, object));
  }

  public JsonMapper enableEnumUseToString()
  {
    this.mapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
    this.mapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
    return this;
  }

  public JsonMapper enableJaxbAnnotation()
  {
    JaxbAnnotationModule module = new JaxbAnnotationModule();
    this.mapper.registerModule(module);
    return this;
  }

  public JsonMapper enableSimple()
  {
    this.mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
    this.mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
    return this;
  }

  public ObjectMapper getMapper()
  {
    return this.mapper;
  }

  public static void main(String[] args)
  {
    Map map = new HashMap();
    map.put("touser", "baixiaohu");
    map.put("msgtype", "text");
    Map subMap = new HashMap();
    subMap.put("content", "helloworld");
    map.put("text", subMap);
    String json = getInstance().toJson(map);
    System.out.println(json);
  }
}