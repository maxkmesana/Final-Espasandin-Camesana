package org.tpfinal.StockFile.Model.Repository;

import com.google.gson.*;
import org.tpfinal.Interfaces.IntStrategy;

import java.lang.reflect.Type;

public class IntStrategyAdapter implements JsonSerializer<IntStrategy>, JsonDeserializer<IntStrategy> {

    public IntStrategyAdapter() {
    }

    @Override
    public JsonElement serialize(IntStrategy src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject obj = new JsonObject();
        obj.addProperty("type", src.getClass().getName());
        obj.add("data", context.serialize(src));
        return obj;
    }

    @Override
    public IntStrategy deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject obj = json.getAsJsonObject();
        String className = obj.get("type").getAsString();
        JsonElement element = obj.get("data");
        try {
            return context.deserialize(element, Class.forName(className));
        } catch (ClassNotFoundException e) {
            throw new JsonParseException(e);
        }
    }

    public static IntStrategyAdapter getInstance() {
        return new IntStrategyAdapter();
    }
}