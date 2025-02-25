package br.com.nobre.domain.aula.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import br.com.nobre.commons.exception.dto.NotFoundException;
import br.com.nobre.commons.utils.DateUtils;
import br.com.nobre.commons.utils.FormattedToJsonUtil;
import br.com.nobre.domain.aula.dao.AulaCreateDao;
import br.com.nobre.domain.aula.dao.AulaFindDao;
import br.com.nobre.domain.aula.model.Aula;
import br.com.nobre.domain.aula.model.AulaTipo;

public class AulaCreateService {
	
	private AulaCreateDao aulaCreateDao;
	private AulaFindDao aulaFindDao;
	
	public AulaCreateService() {
		this.aulaCreateDao = new AulaCreateDao();
		this.aulaFindDao = new AulaFindDao();
	}
	
	public String createAula(HttpServletRequest req) throws JSONException, IOException, NotFoundException {
		
		JSONObject jsonObject = FormattedToJsonUtil.requestBodyToJson(req);

		Aula aula = requestToAula(jsonObject);
		aula = this.aulaCreateDao.createAula(aula);
		
		return createResponse(aula);
		
	}
	
	private Aula requestToAula(JSONObject jsonObject) throws JSONException, NotFoundException {
		
		String horario = jsonObject.getString("horario");
		String descricao = jsonObject.getString("descricao");
		int tipoId = jsonObject.getInt("tipo");
		
		AulaTipo tipo = this.aulaFindDao.findTipoById(tipoId);
		
		if(tipo == null) {
			throw new NotFoundException(String.format("Tipo com id %d não existe ou foi inativado", tipoId));
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
