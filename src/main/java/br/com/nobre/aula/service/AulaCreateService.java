package br.com.nobre.aula.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import br.com.nobre.aula.dao.AulaCreateDao;
import br.com.nobre.aula.dao.AulaFindDao;
import br.com.nobre.aula.model.Aula;
import br.com.nobre.aula.model.AulaTipo;
import br.com.nobre.commons.utils.DateUtils;
import br.com.nobre.commons.utils.FormattedToJsonUtil;

public class AulaCreateService {
	
	private AulaCreateDao aulaCreateDao;
	private AulaFindDao aulaFindDao;
	
	public AulaCreateService() {
		this.aulaCreateDao = new AulaCreateDao();
		this.aulaFindDao = new AulaFindDao();
	}
	
	public String createAula(HttpServletRequest req) throws JSONException, IOException {
		
		JSONObject jsonObject = FormattedToJsonUtil.requestBodyToJson(req);

		Aula aula = requestToAula(jsonObject);
		aula = aulaCreateDao.createAula(aula);
		
		return createResponse(aula);
		
	}
	
	private Aula requestToAula(JSONObject jsonObject) throws JSONException {
		
		String horario = jsonObject.getString("horario");
		String descricao = jsonObject.getString("descricao");
		int tipoId = jsonObject.getInt("tipo");
		
		AulaTipo tipo = aulaFindDao.findTipoById(tipoId);
		
		if(tipo == null) {
			//exception
		}
		
		Aula aula = new Aula();
		aula.setHorario(DateUtils.ISOUtcToGMTMinus3(horario));
		aula.setDescricao(descricao);
		aula.setTipo(tipo);
		
		return aula;
		
		
	}
	
	private String createResponse(Aula aula) throws JSONException {
		
		JSONObject jsonResponse = new JSONObject();
		jsonResponse.put("id", aula.getId());
		jsonResponse.put("horario", DateUtils.GMTMinus3ToISOUtc(aula.getHorario()));
		jsonResponse.put("descricao", aula.getDescricao());
		
		JSONObject tipo = new JSONObject();
		tipo.put("id", aula.getTipo().getId());
		tipo.put("nome", aula.getTipo().getTipo());
		
		jsonResponse.put("tipo", tipo);
		
		return jsonResponse.toString();
		
		
	}
	
}
