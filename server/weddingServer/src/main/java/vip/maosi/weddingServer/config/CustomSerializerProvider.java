package vip.maosi.weddingServer.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import com.fasterxml.jackson.databind.ser.SerializerFactory;

import java.io.IOException;
import java.util.List;

public class CustomSerializerProvider extends DefaultSerializerProvider {

    private static final JsonSerializer<Object> nullToEmptyStringSerializer = new JsonSerializer<>() {
        @Override
        public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeString("");
        }
    };

    private static final JsonSerializer<Object> nullToEmptyArraySerializer = new JsonSerializer<>() {
        @Override
        public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeStartArray();
            gen.writeEndArray();
        }
    };

    @Override
    public JsonSerializer<Object> findNullValueSerializer(BeanProperty property) throws JsonMappingException {
        if (property.getType().getRawClass().equals(String.class)) {
            return nullToEmptyStringSerializer;
        }
        if (property.getType().getRawClass().equals(List.class)) {
            return nullToEmptyArraySerializer;
        }
        return super.findNullValueSerializer(property);
    }

    public CustomSerializerProvider() {
        super();
    }

    public CustomSerializerProvider(SerializerProvider provider, SerializationConfig config, SerializerFactory jsf) {
        super(provider, config, jsf);
    }

    @Override
    public DefaultSerializerProvider createInstance(SerializationConfig config, SerializerFactory jsf) {
        return new CustomSerializerProvider(this, config, jsf);
    }
}
