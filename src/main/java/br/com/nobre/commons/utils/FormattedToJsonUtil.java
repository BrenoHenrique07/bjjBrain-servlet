package br.com.nobre.commons.utils;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class FormattedToJsonUtil {
	
	public static JSONObject requestBodyToJson(HttpServletRequest req) throws JSONException, IOException {
		
        StringBuilder jsonRequest = new StringBuilder();
        String line;
        try (BufferedReader reader = req.getReader()) {
            while ((line = reader.readLine()) != null) {
                jsonRequest.append(line);
            }
        }
		
        JSONObject jsonObject = new JSONObject(jsonRequest.toString());
		return jsonObject;
        
	}
	
}
