package com.colab.editor.global;

import java.io.IOException;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class ObjectDeserializer extends JsonDeserializer<ObjectId>{

    @Override
    public ObjectId deserialize(JsonParser arg0, DeserializationContext arg1) throws IOException, JacksonException {
        String id = arg0.getText();
        System.out.println(id);
        if (ObjectId.isValid(id)) {
            return new ObjectId(id);
        }
        throw new IOException("Invalid ObjectId: " + id);
    }

}
