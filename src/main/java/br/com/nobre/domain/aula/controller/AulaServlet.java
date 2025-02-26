package br.com.nobre.domain.aula.controller;

import java.io.IOException;
import java.net.HttpURLConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.nobre.commons.utils.HttpServletResponseUtil;
import br.com.nobre.domain.aula.service.AulaCreateService;
import br.com.nobre.domain.aula.service.AulaFindService;

@WebServlet(urlPatterns = "/aula/*")
public class AulaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private AulaFindService aulaFindService;
	private AulaCreateService aulaCreateService;

	public AulaServlet() {
		this.aulaFindService = new AulaFindService();
		this.aulaCreateService = new AulaCreateService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String pathInfo = req.getPathInfo();

		if (pathInfo == null || pathInfo.isEmpty()) {
			throw new IllegalArgumentException();
		}

		String idPath = pathInfo.substring(1);
		int alunoId = Integer.valueOf(idPath);

		HttpServletResponseUtil.getResponseHeaders(resp);
		String response = this.aulaFindService.findAulaById(alunoId);

		resp.getWriter().write(response);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String pathInfo = req.getPathInfo();

		if (pathInfo != null && !pathInfo.isEmpty()) {
			throw new IllegalArgumentException();
		}

		HttpServletResponseUtil.getResponseHeaders(resp);
		String response = this.aulaCreateService.createAula(req);

		resp.setStatus(HttpURLConnection.HTTP_CREATED);
		resp.getWriter().write(response);

	}

}
