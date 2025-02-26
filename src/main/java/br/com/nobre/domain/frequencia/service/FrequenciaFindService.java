package br.com.nobre.domain.frequencia.service;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.nobre.commons.dto.PageDto;
import br.com.nobre.commons.dto.PageableDto;
import br.com.nobre.commons.exception.InvalidParamsException;
import br.com.nobre.commons.utils.JsonUtil;
import br.com.nobre.commons.utils.PageableUtil;
import br.com.nobre.domain.frequencia.dao.FrequenciaFindDao;
import br.com.nobre.domain.frequencia.dto.ConvertFrequenciaDto;
import br.com.nobre.domain.frequencia.dto.FrequenciaResponseDto;
import br.com.nobre.domain.frequencia.model.Frequencia;

public class FrequenciaFindService {

	private FrequenciaFindDao frequenciaFindDao;

	public FrequenciaFindService() {
		this.frequenciaFindDao = new FrequenciaFindDao();
	}

	public String findAll(Map<String, String[]> parameterMap) throws InvalidParamsException, IllegalArgumentException, JsonProcessingException {

		Map<String, Object> paramsMap = PageableUtil.createParamnsMap(parameterMap);
		PageableDto pageableDto = PageableUtil.convertParamsToPageable(paramsMap.get("start"), paramsMap.get("limit"));

		long size = this.frequenciaFindDao.countAll(pageableDto.start, pageableDto.limit, paramsMap);
		List<Frequencia> frequenciaList = this.frequenciaFindDao.findAll(pageableDto.start, pageableDto.limit, paramsMap);
		
		List<FrequenciaResponseDto> frequenciaResponseDto = ConvertFrequenciaDto.frequenciaListToResponse(frequenciaList);
		PageDto<FrequenciaResponseDto> page = PageableUtil.createPage(frequenciaResponseDto, pageableDto.start, pageableDto.limit, size);
		
		return JsonUtil.toJson(page);

	}
	
}
