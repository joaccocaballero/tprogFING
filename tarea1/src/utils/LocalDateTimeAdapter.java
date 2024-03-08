package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class LocalDateTimeAdapter implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {
	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	public JsonElement serialize(LocalDateTime dateTime, Type typeOfSrc, JsonSerializationContext context) {
		return new JsonPrimitive(dateTime.format(DATE_TIME_FORMATTER));
	}

	public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		return LocalDateTime.parse(json.getAsString(), DATE_TIME_FORMATTER);
	}

	@Override
	public LocalDateTime deserialize(JsonElement json, java.lang.reflect.Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JsonElement serialize(LocalDateTime src, java.lang.reflect.Type typeOfSrc,
			JsonSerializationContext context) {
		// TODO Auto-generated method stub
		return null;
	}
}