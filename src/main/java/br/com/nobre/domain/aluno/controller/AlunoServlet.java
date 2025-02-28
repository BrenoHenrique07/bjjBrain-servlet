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
import br.com.nobre.commons.utils.ParameterUtil;
import br.com.nobre.domain.aluno.service.AlunoCreateService;
import br.com.nobre.domain.aluno.service.AlunoFindService;
import br.com.nobre.domain.aluno.service.AlunoUpdateService;

@WebServlet(urlPatterns = "/aluno/*")
public class AlunoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final int ID_PARAM_INDEX = 1;
	
	private AlunoFindService alunoFindService;
	private AlunoCreateService alunoCreateService;
	private AlunoUpdateService alunoUpdateService;
	
	public AlunoServlet() {
		this.alunoFindService = new AlunoFindService();
		this.alunoCreateService = new AlunoCreateService();
		this.alunoUpdateService = new AlunoUpdateService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		if(ParameterUtil.containsPathParameter(req.getPathInfo())) {
			throw new InvalidParamsException("Parâmetro inválido, por favor verficar");
		}

		Map<String, String[]> parameterMap = req.getParameterMap();

		String response = this.alunoFindService.findAll(parameterMap);
		resp.getWriter().write(response);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		if(ParameterUtil.containsPathParameter(req.getPathInfo())) {
			throw new InvalidParamsException("Parâmetro inválido, por favor verficar");
		}

		String response = this.alunoCreateService.createAluno(req);

		resp.setStatus(HttpURLConnection.HTTP_CREATED);
		resp.getWriter().write(response);
		
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		if(!ParameterUtil.containsPathParameter(req.getPathInfo())) {
			throw new InvalidParamsException("Necessário informar o id do aluno");
		}
		
		int alunoId = ParameterUtil.getIdInPathParameter(req, ID_PARAM_INDEX);
		String response = this.alunoUpdateService.updateAluno(req, alunoId);
		
		resp.getWriter().write(response);
		
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		if(!ParameterUtil.containsPathParameter(req.getPathInfo())) {
			throw new InvalidParamsException("Necessário informar o id do aluno");
		}
		
		int alunoId = ParameterUtil.getIdInPathParameter(req, ID_PARAM_INDEX);
		this.alunoUpdateService.disableAluno(alunoId);
		
	}
	
}
