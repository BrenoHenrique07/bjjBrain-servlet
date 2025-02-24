package br.com.nobre.aluno.service;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import br.com.nobre.aluno.dao.AlunoFindDao;
import br.com.nobre.aluno.model.Aluno;
import br.com.nobre.aluno.model.Faixa;

public class AlunoFindService {
	
	private AlunoFindDao alunoDao;
	
	public AlunoFindService() {
		alunoDao = new AlunoFindDao();
	}
	
	public String findAlunoById(int alunoId) throws JSONException {
		
		Aluno aluno = alunoDao.findAlunoById(alunoId);
		
		if(aluno == null) {
			//not found 404
		}
		
		return createResponse(aluno);
		
	}
	
	private String createResponse(Aluno aluno) throws JSONException {
		
		JSONObject jsonResponse = new JSONObject();

		jsonResponse.put("nome", aluno.getNome());
		jsonResponse.put("sobrenome", aluno.getSobrenome());
		jsonResponse.put("faixa", Faixa.fromId(aluno.getFaixaId()));
		
		return jsonResponse.toString();
		
	}
	
}
