package br.com.nobre.domain.crogronoma.controller;

import java.io.IOException;
import java.net.HttpURLConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.nobre.domain.crogronoma.service.CronogramaService;

@WebServlet(urlPatterns = "/cronograma")
public class CronogramaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private CronogramaService cronogramaService;
	
	public CronogramaServlet() {
		this.cronogramaService = new CronogramaService();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String response = this.cronogramaService.createAulas(req);
		
		resp.setStatus(HttpURLConnection.HTTP_CREATED);
		resp.getWriter().write(response);
		
	}
	
}
