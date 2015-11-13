package ch.heigvd.amt.amt_project.rest.config;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.text.SimpleDateFormat;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

/**
 * This class is used to configure the serialization/deserialization of payloads
 * in the REST API. For instance, this is where we can specify how dates should
 * be formatted.
 * 
 * @author mberthouzoz
 */
@Provider
public class JacksonConfigurationProvider implements ContextResolver<ObjectMapper> {

  private ObjectMapper mapper = new ObjectMapper();

  public JacksonConfigurationProvider() {
    SerializationConfig serConfig = mapper.getSerializationConfig();
    DeserializationConfig deserializationConfig = mapper.getDeserializationConfig();
    mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  @Override
  public ObjectMapper getContext(Class<?> type) {
    return mapper;
  }

}