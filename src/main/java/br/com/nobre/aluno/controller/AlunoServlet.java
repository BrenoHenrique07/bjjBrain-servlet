package br.com.nobre.aluno.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import br.com.nobre.aluno.dao.AlunoFindDao;
import br.com.nobre.aluno.model.Aluno;
import br.com.nobre.aluno.model.Faixa;

@WebServlet(urlPatterns = "/aluno/*")
public class AlunoServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private AlunoFindDao alunoDao;
	
	public AlunoServlet() {
		alunoDao = new AlunoFindDao();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			
			String pathInfo = req.getPathInfo(); 
			
			if (pathInfo == null || pathInfo.isEmpty()) {
				//exception path parameter obrigatorio
			} 

			String idPath = pathInfo.substring(1);
			int alunoId = Integer.valueOf(idPath);
			
			Aluno aluno = alunoDao.findAlunoById(alunoId);
			
			if(aluno == null) {
				//not found 404
			}
			
			JSONObject json = new JSONObject();
			
			json.put("nome", aluno.getNome());
			json.put("sobrenome", aluno.getSobrenome());
			json.put("faixa", Faixa.fromId(aluno.getFaixaId()).getNome());
			
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			
			resp.getWriter().write(json.toString());
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	}
	
}
