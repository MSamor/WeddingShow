package vip.maosi.weddingserver.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jackson.JacksonProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableConfigurationProperties(JacksonProperties.class)
public class JacksonConfig implements WebMvcConfigurer {

    @Autowired
    JacksonProperties jacksonProperties;

    @Bean
    @Primary
    @ConditionalOnMissingBean(ObjectMapper.class)
    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).failOnUnknownProperties(false).build();
        objectMapper.setSerializerProvider(new CustomSerializerProvider());
        return objectMapper;
    }

    @Override
    public void configureMessageConverters(@NonNull List<HttpMessageConverter<?>> converters) {
        converters.add(new StringHttpMessageConverter(StandardCharsets.UTF_8));
        converters.add(new ByteArrayHttpMessageConverter());
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setTimeZone(jacksonProperties.getTimeZone());
        objectMapper.setDateFormat(new SimpleDateFormat(jacksonProperties.getDateFormat()));
        objectMapper.setSerializerProvider(new CustomSerializerProvider());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(objectMapper);
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
        converters.add(converter);
    }
}
