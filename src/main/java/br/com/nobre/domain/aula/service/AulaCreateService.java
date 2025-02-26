package br.com.nobre.domain.aula.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import br.com.nobre.commons.exception.NotFoundException;
import br.com.nobre.commons.utils.JsonUtil;
import br.com.nobre.domain.aula.dao.AulaCreateDao;
import br.com.nobre.domain.aula.dao.AulaFindDao;
import br.com.nobre.domain.aula.dto.ConverterAulaDto;
import br.com.nobre.domain.aula.dto.AulaRequestDto;
import br.com.nobre.domain.aula.dto.AulaResponseDto;
import br.com.nobre.domain.aula.model.Aula;
import br.com.nobre.domain.aula.model.AulaTipo;

public class AulaCreateService {
	
	private AulaCreateDao aulaCreateDao;
	private AulaFindDao aulaFindDao;
	
	public AulaCreateService() {
		this.aulaCreateDao = new AulaCreateDao();
		this.aulaFindDao = new AulaFindDao();
	}
	
	public String createAula(HttpServletRequest req) throws IOException, NotFoundException {
		
		AulaRequestDto aulaRequestDto = JsonUtil.requestBodyToJson(req, AulaRequestDto.class);		
		AulaTipo tipo = this.aulaFindDao.findTipoById(aulaRequestDto.tipo);
		
		if(tipo == null) {
			throw new NotFoundException(String.format("Tipo com id %d não existe ou foi inativado", aulaRequestDto.tipo));
		}
		
		Aula aula = ConverterAulaDto.requestToAula(aulaRequestDto, tipo);
		aula = this.aulaCreateDao.createAula(aula);
		
		AulaResponseDto aulaResponseDto = ConverterAulaDto.aulaToResponse(aula);
		return JsonUtil.toJson(aulaResponseDto);
		
	}
	
}
