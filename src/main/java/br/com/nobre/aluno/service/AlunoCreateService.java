package br.com.nobre.aluno.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import br.com.nobre.aluno.dao.AlunoCreateDao;
import br.com.nobre.aluno.dto.AlunoRequestDto;
import br.com.nobre.aluno.dto.AlunoResponseDto;
import br.com.nobre.commons.utils.FormattedToJsonUtil;

public class AlunoCreateService {
	
	private AlunoCreateDao alunoCreateDao;
	
	public AlunoCreateService() {
		alunoCreateDao = new AlunoCreateDao();
	}
	
	public String createAluno(HttpServletRequest req) throws JSONException, IOException {
		
		JSONObject jsonObject = FormattedToJsonUtil.requestBodyToJson(req);
        
		String nome = jsonObject.getString("nome");
		String sobrenome = jsonObject.getString("sobrenome");
		int faixaId = jsonObject.getInt("faixaId");

		AlunoRequestDto alunoRequest = new AlunoRequestDto(nome, sobrenome, faixaId);
		AlunoResponseDto alunoResponse = alunoCreateDao.createAluno(alunoRequest);

		JSONObject jsonResponse = new JSONObject();
		jsonResponse.put("id", alunoResponse.id);
		jsonResponse.put("nome", alunoResponse.nome);
		jsonResponse.put("sobrenome", alunoResponse.sobrenome);
		jsonResponse.put("faixa", alunoResponse.faixa);
		
		return jsonResponse.toString();
		
	}
	
}
