package br.com.nobre.domain.frequencia.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.nobre.commons.utils.HttpServletResponseUtil;
import br.com.nobre.domain.frequencia.service.FrequenciaFindService;

@WebServlet(urlPatterns = "/frequencia")
public class FrequenciaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private FrequenciaFindService frequenciaFindService;

	public FrequenciaServlet() {
		this.frequenciaFindService = new FrequenciaFindService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Map<String, String[]> parameterMap = req.getParameterMap();

		HttpServletResponseUtil.getResponseHeaders(resp);
		String response = this.frequenciaFindService.findAll(parameterMap);

		resp.getWriter().write(response);

	}
}
