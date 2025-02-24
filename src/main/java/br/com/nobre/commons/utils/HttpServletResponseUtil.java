package br.com.nobre.commons.utils;

import javax.servlet.http.HttpServletResponse;

public class HttpServletResponseUtil {

	public static void getResponseHeaders(HttpServletResponse resp) {
		
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		
	}
	
}
