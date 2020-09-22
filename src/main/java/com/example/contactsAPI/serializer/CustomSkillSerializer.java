package com.example.contactsAPI.serializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.contactsAPI.model.Skill;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class CustomSkillSerializer extends StdSerializer<List<Skill>> {

	private static final long serialVersionUID = 1L;

	public CustomSkillSerializer() {
        this(null);
    }

    public CustomSkillSerializer(Class<List<Skill>> t) {
        super(t);
    }

    @Override
    public void serialize(
            List<Skill> skills,
            JsonGenerator generator,
            SerializerProvider provider)
            throws IOException, JsonProcessingException {

        List<Skill> sks = new ArrayList<>();
        for (Skill s : skills) {
            s.setContacts(null);
            sks.add(s);
        }
        generator.writeObject(sks);
    }
}