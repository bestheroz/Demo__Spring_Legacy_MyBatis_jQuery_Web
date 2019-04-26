package com.github.bestheroz.standard.common.util.mapper;

import java.lang.reflect.Type;

import org.joda.time.DateTime;

import com.github.bestheroz.standard.common.util.MyDateUtils;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;

public class MapperDateTimeDeserializer implements JsonDeserializer<DateTime> {
    @Override
    public DateTime deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) {
        return MyDateUtils.getDateTime(json.getAsLong());
    }
}
