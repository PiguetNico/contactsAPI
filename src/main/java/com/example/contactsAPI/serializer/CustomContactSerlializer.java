package com.example.contactsAPI.serializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.contactsAPI.model.Contact;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class CustomContactSerlializer extends StdSerializer<List<Contact>> {

	private static final long serialVersionUID = 1L;

	public CustomContactSerlializer() {
        this(null);
    }

    public CustomContactSerlializer(Class<List<Contact>> t) {
        super(t);
    }

    @Override
    public void serialize(
            List<Contact> contacts,
            JsonGenerator generator,
            SerializerProvider provider)
            throws IOException, JsonProcessingException {

        List<Contact> conts = new ArrayList<Contact>();
        for (Contact c : contacts) 
        {
            c.setSkills(null);
            conts.add(c);
        }
        generator.writeObject(conts);
    }
}