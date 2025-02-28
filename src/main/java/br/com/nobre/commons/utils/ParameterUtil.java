package br.com.nobre.commons.utils;

import javax.servlet.http.HttpServletRequest;

public class ParameterUtil {
	
	public static boolean containsPathParameter(String pathParameter) {
		return pathParameter != null ? true : false;
	}
	
	public static int getIdInPathParameter(HttpServletRequest req, int index) {
		String path = req.getPathInfo();
		return Integer.parseInt(path.substring(1));
	}
	
}
