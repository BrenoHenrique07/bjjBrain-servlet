package br.com.nobre.domain.frequencia.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import br.com.nobre.commons.dto.Page;
import br.com.nobre.commons.utils.DateUtils;
import br.com.nobre.domain.aluno.model.Faixa;
import br.com.nobre.domain.frequencia.dao.FrequenciaFindDao;
import br.com.nobre.domain.frequencia.model.Frequencia;

public class FrequenciaFindService {
	
	private FrequenciaFindDao frequenciaFindDao;
	
	public FrequenciaFindService() {
		this.frequenciaFindDao = new FrequenciaFindDao();
	}
	
	public String findAll(Map<String, String[]> parameterMap) throws IllegalArgumentException, JSONException {
		
		Map<String, Object> paramsMap = createParamnsMap(parameterMap);
		Page<Frequencia> page = new Page<Frequencia>(new ArrayList<Frequencia>(), 0, 0, 0, 0);
		
		try {
			
			
			int start = paramsMap.get("start") != null ? Integer.valueOf(paramsMap.get("start").toString()) : 0;
			int limit = paramsMap.get("limit") != null ? Integer.valueOf(paramsMap.get("limit").toString()) : 100;
			
			if(limit > 100) {
				//exception
			}
			
			int size = this.frequenciaFindDao.countAll(start, limit, paramsMap);
			List<Frequencia> frequenciaList = this.frequenciaFindDao.findAll(start, limit, paramsMap);
			
			page = createPage(frequenciaList, start, limit, size);
			
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Parâmetros inválidos");
		}

		return pageToJson(page);
		
	}
	
    private Map<String, Object> createParamnsMap(Map<String, String[]> parameterMap) {
        
    	Map<String, Object> paramsMap = new HashMap<>();
    	
    	for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
			String key = entry.getKey();
			String[] val = entry.getValue();
			
			paramsMap.put(key, val[0]);
			
		}
    	
    	return paramsMap;
    	
    }
	
	private Page<Frequencia> createPage(List<Frequencia> frequenciaList, int start, int limit, int size) {
		
		//validar divisão por zero
		int totalPage = size / limit;
		
		if((size % limit) != 0) {
			totalPage ++;
		}
		
		return new Page<Frequencia>(frequenciaList, start, limit, size, totalPage <= 0 ? 1 : totalPage);
	}
    
	private String pageToJson(Page<Frequencia> page) throws JSONException {
		
		JSONObject response = new JSONObject();
		
		JSONArray data = new JSONArray();
		for (Frequencia frequencia : page.data) {
			
			JSONObject frequenciaJson = new JSONObject();
			
			JSONObject aluno = new JSONObject();
			aluno.put("id", frequencia.getAluno().getId());
			aluno.put("nome", frequencia.getAluno().getNome());
			aluno.put("sobrenome", frequencia.getAluno().getSobrenome());
			aluno.put("faixa", Faixa.fromId(frequencia.getAluno().getFaixaId()));
			
			JSONObject aula = new JSONObject();
			aula.put("id", frequencia.getAula().getId());
			aula.put("tipo", frequencia.getAula().getTipo().getTipo());
			aula.put("horario", DateUtils.GMTMinus3ToISOUtc(frequencia.getAula().getHorario()));
		
			frequenciaJson.put("aluno", aluno);
			frequenciaJson.put("aula", aula);
			frequenciaJson.put("confirmado", frequencia.isConfirmado());
	
			data.put(frequenciaJson);
			
		}
		
		response.put("data", data);
		response.put("start", page.start);
		response.put("limit", page.limit);
		response.put("size", page.size);
		response.put("totalPage", page.totalPage);
		
		return response.toString();
		
	}
	
}
