package br.com.nobre.domain.aula.service;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.nobre.commons.dto.PageDto;
import br.com.nobre.commons.dto.PageableDto;
import br.com.nobre.commons.exception.NotFoundException;
import br.com.nobre.commons.utils.JsonUtil;
import br.com.nobre.commons.utils.PageableUtil;
import br.com.nobre.domain.aula.dao.AulaFindDao;
import br.com.nobre.domain.aula.dto.AulaResponseDto;
import br.com.nobre.domain.aula.dto.ConverterAulaDto;
import br.com.nobre.domain.aula.model.Aula;

public class AulaFindService {
	
	private AulaFindDao aulaFindDao;
	
	public AulaFindService() {
		this.aulaFindDao = new AulaFindDao();
	}
	
	public String findAll(Map<String, String[]> parameterMap) throws NotFoundException, JsonProcessingException {
		
		Map<String, Object> paramsMap = PageableUtil.createParamnsMap(parameterMap);
		PageableDto pageableDto = PageableUtil.convertParamsToPageable(paramsMap.get("start"), paramsMap.get("limit"));

		long size = this.aulaFindDao.countAll(pageableDto.start, pageableDto.limit, paramsMap);
		List<Aula> aulaList = this.aulaFindDao.findAll(pageableDto.start, pageableDto.limit, paramsMap);

		List<AulaResponseDto> alunoResponseList = ConverterAulaDto.aulaToResponseList(aulaList);
		PageDto<AulaResponseDto> page = PageableUtil.createPage(alunoResponseList, pageableDto.start, pageableDto.limit, size);
		
		return JsonUtil.toJson(page);
		
	}
	
}
