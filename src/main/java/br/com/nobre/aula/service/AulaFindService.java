package br.com.nobre.aula.service;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import br.com.nobre.aula.dao.AulaFindDao;
import br.com.nobre.aula.model.Aula;
import br.com.nobre.commons.utils.DateUtils;

public class AulaFindService {
	
	private AulaFindDao aulaFindDao;
	
	public AulaFindService() {
		this.aulaFindDao = new AulaFindDao();
	}
	
	public String findAulaById(int id) throws JSONException {
		
		Aula aula = aulaFindDao.findAulaById(id);
		
		if(aula == null) {
			//not found 404
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
