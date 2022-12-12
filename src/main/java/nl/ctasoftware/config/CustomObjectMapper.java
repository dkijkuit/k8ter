package nl.ctasoftware.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.jackson.ObjectMapperCustomizer;

import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

public class CustomObjectMapper {

    // Replaces the CDI producer for ObjectMapper built into Quarkus
    @Singleton
    @Produces
    ObjectMapper objectMapper(Instance<ObjectMapperCustomizer> customizers) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        // Apply all ObjectMapperCustomizer beans (incl. Quarkus)
        for (ObjectMapperCustomizer customizer : customizers) {
            customizer.customize(mapper);
        }

        return mapper;
    }
}