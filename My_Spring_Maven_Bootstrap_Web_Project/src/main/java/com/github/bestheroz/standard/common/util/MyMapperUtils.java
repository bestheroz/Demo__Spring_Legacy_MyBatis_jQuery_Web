package com.github.bestheroz.standard.common.util;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.bestheroz.standard.common.exception.CommonException;
import com.github.bestheroz.standard.common.exception.CommonExceptionCode;
import com.github.bestheroz.standard.common.util.mapper.MapperDateDeserializer;
import com.github.bestheroz.standard.common.util.mapper.MapperDateSerializer;
import com.github.bestheroz.standard.common.util.mapper.MapperDateTimeDeserializer;
import com.github.bestheroz.standard.common.util.mapper.MapperDateTimeSerializer;
import com.github.bestheroz.standard.common.util.mapper.MapperLocalDateTimeDeserializer;
import com.github.bestheroz.standard.common.util.mapper.MapperLocalDateTimeSerializer;
import com.github.bestheroz.standard.common.util.mapper.MapperMapDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

public class MyMapperUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyMapperUtils.class);

    protected MyMapperUtils() {
        throw new UnsupportedOperationException();
    }

    private static final Gson GSON_INSTANCE = new GsonBuilder().registerTypeAdapter(Date.class, new MapperDateDeserializer()).registerTypeAdapter(Date.class, new MapperDateSerializer())
            .registerTypeAdapter(DateTime.class, new MapperDateTimeDeserializer()).registerTypeAdapter(DateTime.class, new MapperDateTimeSerializer())
            .registerTypeAdapter(LocalDateTime.class, new MapperLocalDateTimeDeserializer()).registerTypeAdapter(LocalDateTime.class, new MapperLocalDateTimeSerializer())
            .registerTypeAdapter(Map.class, new MapperMapDeserializer()).registerTypeAdapter(HashMap.class, new MapperMapDeserializer())
            .registerTypeAdapter(LinkedTreeMap.class, new MapperMapDeserializer()).disableHtmlEscaping().create();

    public static final <T> T writeObjectAsObject(final Object content, final Class<T> returnType) throws CommonException {
        return GSON_INSTANCE.fromJson(writeObjectAsJsonElement(content), returnType);
    }

    public static final <T> T writeObjectAsObject(final Object content, final Type typeOfT) throws CommonException {
        return GSON_INSTANCE.fromJson(writeObjectAsJsonElement(content), typeOfT);
    }

    public static final JsonArray writeObjectAsJsonArray(final Object content) throws CommonException {
        return getCollectionTypeCatchException(content, JsonArray.class);
    }

    public static final JsonObject writeObjectAsJsonObject(final Object content) throws CommonException {
        return getCollectionTypeCatchException(content, JsonObject.class);
    }

    public static final Map<String, Object> writeObjectAsHashMap(final Object content) throws CommonException {
        return writeObjectAsObject(getCollectionTypeCatchException(content, JsonObject.class), new TypeToken<HashMap<String, Object>>() {
        }.getType());
    }

    public static final JsonPrimitive writeObjectAsJsonPrimitive(final Object content) throws CommonException {
        return writeObjectAsJsonElement(content).getAsJsonPrimitive();
    }

    public static final JsonElement writeObjectAsJsonElement(final Object content) throws CommonException {
        if (content != null && content instanceof String) {
            try {
                return new JsonParser().parse((String) content);
            } catch (final Throwable e) {
                // ignored
                return GSON_INSTANCE.toJsonTree(content);
            }
        } else {
            return GSON_INSTANCE.toJsonTree(content);
        }
    }

    public static final String writeObjectAsString(final Object content) throws CommonException {
        return GSON_INSTANCE.toJson(content);
    }

    public static final <T> List<T> writeObjectAsArrayList(final Object content, final Class<T> returnType) throws CommonException {
        final JsonArray array = MyMapperUtils.writeObjectAsObject(content, JsonArray.class);
        final List<T> lst = new ArrayList<>();
        for (final JsonElement json : array) {
            lst.add(MyMapperUtils.writeObjectAsObject(json, returnType));
        }
        return lst;
        // return GSON_INSTANCE.fromJson(getJsonElementWithExceptionString(content), new TypeToken<ArrayList<T>>() {
        // }.getType());
    }

    public static final Gson getGsonObject() {
        return GSON_INSTANCE;
    }

    @SuppressWarnings("unchecked")
    private static <T> T getCollectionTypeCatchException(final Object content, final Class<T> returnType) {
        final JsonElement jsonElement = writeObjectAsJsonElement(content);
        if (jsonElement.isJsonPrimitive()) {
            LOGGER.warn(CommonExceptionCode.ERROR_FAIL_TRANSFORM_DATA.toString());
            throw new CommonException(CommonExceptionCode.ERROR_FAIL_TRANSFORM_DATA, jsonElement);
        } else if (jsonElement.isJsonNull()) {
            if (returnType == JsonObject.class) {
                return (T) new JsonObject();
            } else if (returnType == JsonArray.class) {
                return (T) new JsonArray();
            } else if (returnType == Map.class) {
                return (T) new HashMap<String, Object>();
            }
        }

        return writeObjectAsObject(jsonElement, returnType);
    }
}
