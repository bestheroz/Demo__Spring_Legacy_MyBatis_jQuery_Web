package com.github.bestheroz.standard.common.util.mapper;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;

import java.lang.reflect.Type;
import java.util.Date;

public class MapperDateDeserializer implements JsonDeserializer<Date> {
    @Override
    public Date deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) {
        return new Date(json.getAsLong());
    }
}
