package com.example.oopsassgn;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.core.JsonGenerator;

import java.io.IOException;

public class UserSetupSerializer extends JsonSerializer<UserSetup> {
    @Override
    public void serialize(UserSetup user, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField("UID", user.getUID());
        gen.writeStringField("username", user.getUsername());
        gen.writeStringField("mailID", user.getMailID());
        gen.writeEndObject();
    }
}
