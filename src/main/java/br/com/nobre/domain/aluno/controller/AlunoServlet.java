package br.com.nobre.domain.aluno.controller;

import java.io.IOException;
import java.net.HttpURLConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.nobre.commons.utils.HttpServletResponseUtil;
import br.com.nobre.domain.aluno.service.AlunoCreateService;
import br.com.nobre.domain.aluno.service.AlunoFindService;

@WebServlet(urlPatterns = "/aluno/*")
public class AlunoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private AlunoFindService alunoFindService;
	private AlunoCreateService alunoCreateService;

	public AlunoServlet() {
		this.alunoFindService = new AlunoFindService();
		this.alunoCreateService = new AlunoCreateService();
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
		String response = this.alunoFindService.findAlunoById(alunoId);

		resp.getWriter().write(response);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {

			String pathInfo = req.getPathInfo();

			if (pathInfo != null) {
				throw new IllegalArgumentException();
			}

			HttpServletResponseUtil.getResponseHeaders(resp);
			String response = this.alunoCreateService.createAluno(req);

			resp.setStatus(HttpURLConnection.HTTP_CREATED);
			resp.getWriter().write(response);

		} catch (Exception e) {

		}

	}

}
