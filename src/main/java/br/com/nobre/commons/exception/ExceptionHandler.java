package br.com.nobre.commons.exception;

import java.io.IOException;
import java.net.HttpURLConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import br.com.nobre.commons.exception.dto.NotFoundException;
import br.com.nobre.commons.utils.HttpServletResponseUtil;

@WebServlet(urlPatterns = "/error-handler")
public class ExceptionHandler extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    	Integer statusCode = HttpURLConnection.HTTP_INTERNAL_ERROR;
    	
        String message = (String) req.getAttribute("errorMessage");
        Class<?> exceptionClass = (Class<?>) req.getAttribute("exceptionClass");

        JSONObject error = new JSONObject();

        try {
   
            if (exceptionClass != null) {
            
                if (NotFoundException.class.isAssignableFrom(exceptionClass)) {
                    statusCode = HttpURLConnection.HTTP_NOT_FOUND;
                } 
                
                if (IllegalArgumentException.class.isAssignableFrom(exceptionClass)) {
                	statusCode = HttpURLConnection.HTTP_BAD_REQUEST;
                	message = "Argumento inválido, por favor verifique a requisição";
                } 
   
                error.put("error", message);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        HttpServletResponseUtil.getResponseHeaders(resp);
        resp.setStatus(statusCode);
        resp.getWriter().write(error.toString());
    }
}
