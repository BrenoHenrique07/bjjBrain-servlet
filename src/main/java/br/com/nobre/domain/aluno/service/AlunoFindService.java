package br.com.nobre.domain.aluno.service;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import br.com.nobre.commons.exception.dto.NotFoundException;
import br.com.nobre.domain.aluno.dao.AlunoFindDao;
import br.com.nobre.domain.aluno.model.Aluno;
import br.com.nobre.domain.aluno.model.Faixa;

public class AlunoFindService {
	
	private AlunoFindDao alunoDao;
	
	public AlunoFindService() {
		this.alunoDao = new AlunoFindDao();
	}
	
	public String findAlunoById(int alunoId) throws JSONException, NotFoundException {
		
		Aluno aluno = this.alunoDao.findAlunoById(alunoId);
		
		if(aluno == null) {
			throw new NotFoundException(String.format("Aluno com id %d não existe ou está inativado", alunoId));
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
