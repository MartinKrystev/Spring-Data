package com.example.json_processing.constants;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Tests {
    /////////////////////////////////////////////////////////////////////////////////////////////
    Gson gson1 = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(LocalDate.class, new JsonSerializer<LocalDate>() {
                @Override
                public JsonElement serialize(LocalDate date, Type type,
                                             JsonSerializationContext jsonSerializationContext) {
                    return new JsonPrimitive(date.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                }
            }).create();
    /////////////////////////////////////////////////////////////////////////////////////////////
    Gson gson2 = new GsonBuilder()
            .setPrettyPrinting()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            .registerTypeAdapter(LocalDateTime.class, new JsonSerializer<LocalDateTime>() {
                @Override
                public JsonElement serialize(LocalDateTime localDateTime, Type type, JsonSerializationContext jsonSerializationContext) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
                    String dateString = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
                    LocalDateTime ldt = LocalDateTime.parse(dateString, formatter);
                    return new JsonPrimitive(localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")));
                }
            }).create();
    /////////////////////////////////////////////////////////////////////////////////////////////
    static class LocalDateTimeSerializer implements JsonSerializer<LocalDateTime> { // useful
        private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        @Override
        public JsonElement serialize(LocalDateTime localDateTime, Type srcType, JsonSerializationContext context) {
            return new JsonPrimitive(formatter.format(localDateTime));
        }
    }
    Gson gson3 = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer())
            .create();
    /////////////////////////////////////////////////////////////////////////////////////////////
    public Gson gsonFinal() { // useful
        JsonSerializer<String> fromLocalDateTime = (date, type, context) -> new JsonPrimitive(date);
        JsonDeserializer<LocalDateTime> toLocalDateTime = (json, type, context) -> LocalDateTime.parse(json.getAsString());

        return new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDateTime.class, fromLocalDateTime)
                .registerTypeAdapter(LocalDateTime.class, toLocalDateTime)
                .create();
    }
    /////////////////////////////////////////////////////////////////////////////////////////////

    JsonDeserializer<LocalDateTime> toLocalDate = new JsonDeserializer<LocalDateTime>() {
        @Override // useful
        public LocalDateTime deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            return LocalDateTime.parse(json.getAsString());
        }
    };
    /////////////////////////////////////////////////////////////////////////////////////////////
}
