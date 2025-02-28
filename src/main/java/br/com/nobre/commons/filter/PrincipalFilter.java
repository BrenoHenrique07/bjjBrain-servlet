package br.com.nobre.commons.filter;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

import br.com.nobre.commons.exception.InvalidParamsException;
import br.com.nobre.commons.exception.NotFoundException;
import br.com.nobre.commons.utils.JsonUtil;

@WebFilter(urlPatterns = "/*")
public class PrincipalFilter implements Filter {

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

		try {
			
			req.setCharacterEncoding("UTF-8");
			
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			
			chain.doFilter(req, resp);
			
		} catch (Exception e) {

			String message = "Erro interno no servidor";
			Integer statusCode = HttpURLConnection.HTTP_INTERNAL_ERROR;
			
			Map<String, String> errorMap = new HashMap<>();
			String error = message;
						
			try {
				
				e.printStackTrace();
				
				if(e instanceof InvalidParamsException) {
					
					statusCode = HttpURLConnection.HTTP_BAD_REQUEST;
					message = e.getMessage();
					
				} else if (e instanceof NotFoundException) {

					statusCode = HttpURLConnection.HTTP_NOT_FOUND;
					message = e.getMessage();

				} else if (e instanceof IllegalArgumentException) {
					statusCode = HttpURLConnection.HTTP_BAD_REQUEST;
					message = "Argumento inválido, por favor verifique a requisição";

				} else if (e instanceof UnrecognizedPropertyException) {
					statusCode = HttpURLConnection.HTTP_BAD_REQUEST;
					message = "Parâmetro do corpo da requisição inválido, por favor verifique a requisição";
				}

				errorMap.put("error", message);
				error = JsonUtil.toJson(errorMap);

			} catch (JsonProcessingException jsonEx) {
				jsonEx.printStackTrace();
			}

			HttpServletResponse httpResponse = (HttpServletResponse) resp;

			httpResponse.setStatus(statusCode);
			httpResponse.getWriter().write(error);
		}
	}

}
