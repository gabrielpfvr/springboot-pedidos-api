package br.com.gabrielmotta.modules.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;

public class TestHelper {

    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        var mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper.writeValueAsBytes(object);
    }
}
