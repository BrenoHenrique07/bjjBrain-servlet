package br.com.nobre.domain.aula.service;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import br.com.nobre.commons.exception.dto.NotFoundException;
import br.com.nobre.commons.utils.DateUtils;
import br.com.nobre.domain.aula.dao.AulaFindDao;
import br.com.nobre.domain.aula.model.Aula;

public class AulaFindService {
	
	private AulaFindDao aulaFindDao;
	
	public AulaFindService() {
		this.aulaFindDao = new AulaFindDao();
	}
	
	public String findAulaById(int id) throws JSONException, NotFoundException {
		
		Aula aula = this.aulaFindDao.findAulaById(id);
		
		if(aula == null) {
			throw new NotFoundException(String.format("Aula com id %d não existe ou foi inativada", id));
		}

		return createResponse(aula);
		
	}
	
	private String createResponse(Aula aula) throws JSONException {
		
		JSONObject jsonResponse = new JSONObject();

		jsonResponse.put("horario", DateUtils.GMTMinus3ToISOUtc(aula.getHorario()));
		jsonResponse.put("descricao", aula.getDescricao());
		
		JSONObject tipo = new JSONObject();
		tipo.put("id", aula.getTipo().getId());
		tipo.put("nome", aula.getTipo().getTipo());
		
		jsonResponse.put("tipo", tipo);
		
		return jsonResponse.toString();
		
	}
	
}
