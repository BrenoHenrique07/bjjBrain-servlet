package br.com.nobre.domain.aula.service;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.nobre.commons.exception.NotFoundException;
import br.com.nobre.commons.utils.JsonUtil;
import br.com.nobre.domain.aula.dao.AulaFindDao;
import br.com.nobre.domain.aula.dto.ConverterAulaDto;
import br.com.nobre.domain.aula.dto.AulaResponseDto;
import br.com.nobre.domain.aula.model.Aula;

public class AulaFindService {
	
	private AulaFindDao aulaFindDao;
	
	public AulaFindService() {
		this.aulaFindDao = new AulaFindDao();
	}
	
	public String findAulaById(int id) throws NotFoundException, JsonProcessingException {
		
		Aula aula = this.aulaFindDao.findAulaById(id);
		
		if(aula == null) {
			throw new NotFoundException(String.format("Aula com id %d não existe ou foi inativada", id));
		}

		AulaResponseDto aulaResponseDto = ConverterAulaDto.aulaToResponse(aula);
		return JsonUtil.toJson(aulaResponseDto);
		
	}
	
}
