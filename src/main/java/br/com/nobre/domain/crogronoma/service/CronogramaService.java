package br.com.nobre.domain.crogronoma.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import br.com.nobre.commons.utils.JsonUtil;
import br.com.nobre.domain.crogronoma.dto.CronogramaRequestDto;

public class CronogramaService {
	
	public String createAulas(HttpServletRequest req) throws IOException {
		
		CronogramaRequestDto cronogramaRequestDto = JsonUtil.requestBodyToJson(req, CronogramaRequestDto.class);
		System.out.println(cronogramaRequestDto.diaSemana);
		
		//TODO verificar tipo e dia da semana
		//TODO fazer a mecanica de criacao de aulas
		
		return null;
	}
	
}
