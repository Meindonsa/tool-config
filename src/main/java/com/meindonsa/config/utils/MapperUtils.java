package com.meindonsa.config.utils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MapperUtils {
    private static ModelMapper modelMapper = new ModelMapper();
    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Model mapper property setting are specified in the following block. Default property matching
     * strategy is set to Strict see {@link MatchingStrategies} Custom mappings are added using
     * {@link ModelMapper#addMappings(PropertyMap)}
     */
    static {
        modelMapper = new ModelMapper();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.setVisibility(
                VisibilityChecker.Std.defaultInstance()
                        .withFieldVisibility(JsonAutoDetect.Visibility.ANY));
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    /** Hide from public usage. */
    private MapperUtils() {}

    /**
     * Note: outClass object must have default constructor with no arguments
     *
     * @param <D> type of result object.
     * @param <T> type of source object to map from.
     * @param entity entity that needs to be mapped.
     * @param outClass class of result object.
     * @return new object of <code>outClass</code> type.
     */
    public static <D, T> D map(final T entity, Class<D> outClass) {
        return modelMapper.map(entity, outClass);
    }

    /**
     * Note: outClass object must have default constructor with no arguments
     *
     * @param entityList list of entities that needs to be mapped
     * @param outCLass class of result list element
     * @param <D> type of objects in result list
     * @param <T> type of entity in <code>entityList</code>
     * @return list of mapped object with <code><D></code> type.
     */
    public static <D, T> List<D> mapAll(final Collection<T> entityList, Class<D> outCLass) {
        return entityList.stream()
                .map(entity -> map(entity, outCLass))
                .collect(Collectors.toList());
    }

    /**
     * Maps {@code source} to {@code destination}.
     *
     * @param source object to map from
     * @param destination object to map to
     */
    public static <S, D> D map(final S source, D destination) {
        modelMapper.map(source, destination);
        return destination;
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> objectToMap(Object o) {
        return objectMapper.convertValue(o, Map.class);
    }

    public static <C> C stringJsonObject(String data, Class<C> clazz) {
        try {
            return objectMapper.readValue(data, clazz);
        } catch (JsonProcessingException e) {
            e.getStackTrace();
        }
        return null;
    }
}
