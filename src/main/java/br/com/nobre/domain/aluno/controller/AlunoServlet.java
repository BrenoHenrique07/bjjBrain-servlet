package br.com.nobre.domain.aluno.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.nobre.commons.exception.InvalidParamsException;
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
		
		if(containsPathParameter(req.getPathInfo())) {
			throw new InvalidParamsException("Parâmetro inválido, por favor verficar");
		}

		Map<String, String[]> parameterMap = req.getParameterMap();

		String response = this.alunoFindService.findAll(parameterMap);
		resp.getWriter().write(response);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		if(containsPathParameter(req.getPathInfo())) {
			throw new InvalidParamsException("Parâmetro inválido, por favor verficar");
		}

		String response = this.alunoCreateService.createAluno(req);

		resp.setStatus(HttpURLConnection.HTTP_CREATED);
		resp.getWriter().write(response);
		
	}
	
	private boolean containsPathParameter(String pathParameter) {
		return pathParameter != null ? true : false;
	}
	
}
