package br.com.nobre.domain.aluno.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import br.com.nobre.commons.utils.FormattedToJsonUtil;
import br.com.nobre.domain.aluno.dao.AlunoCreateDao;
import br.com.nobre.domain.aluno.model.Aluno;
import br.com.nobre.domain.aluno.model.Faixa;

public class AlunoCreateService {
	
	private AlunoCreateDao alunoCreateDao;
	
	public AlunoCreateService() {
		this.alunoCreateDao = new AlunoCreateDao();
	}
	
	public String createAluno(HttpServletRequest req) throws JSONException, IOException {
		
		JSONObject jsonObject = FormattedToJsonUtil.requestBodyToJson(req);

		Aluno aluno = requestToAluno(jsonObject);
		Aluno alunoResponse = this.alunoCreateDao.createAluno(aluno);

		return createResponse(alunoResponse);
		
	}

	private Aluno requestToAluno(JSONObject jsonObject) throws JSONException {
		
		String nome = jsonObject.getString("nome");
		String sobrenome = jsonObject.getString("sobrenome");
		int faixaId = jsonObject.getInt("faixaId");
		
		Aluno aluno = new Aluno();
		aluno.setNome(nome);
		aluno.setSobrenome(sobrenome);
		aluno.setFaixaId(faixaId);
		
		return aluno;
		
	}
	
	private String createResponse(Aluno alunoResponse) throws JSONException {
		
		JSONObject jsonResponse = new JSONObject();
		jsonResponse.put("id", alunoResponse.getId());
		jsonResponse.put("nome", alunoResponse.getNome());
		jsonResponse.put("sobrenome", alunoResponse.getSobrenome());	
		jsonResponse.put("faixa", Faixa.fromId(alunoResponse.getFaixaId()).getNome());
		
		return jsonResponse.toString();
		
	}
	
}
