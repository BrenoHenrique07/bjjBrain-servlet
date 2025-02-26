package br.com.nobre.commons.utils;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

    private static ObjectMapper mapper = new ObjectMapper();

    public static <T> T requestBodyToJson(HttpServletRequest req, Class<T> valueType) throws IOException {

        StringBuilder jsonRequest = new StringBuilder();
        String line;
        try (BufferedReader reader = req.getReader()) {
            while ((line = reader.readLine()) != null) {
                jsonRequest.append(line);
            }
        }

        return mapper.readValue(jsonRequest.toString(), valueType);
    }

    public static String toJson(Object object) throws JsonProcessingException  {
        return mapper.writeValueAsString(object);
    }
}
