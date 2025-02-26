package br.com.nobre.domain.aula.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.nobre.commons.utils.DateUtil;
import br.com.nobre.domain.aula.model.Aula;
import br.com.nobre.domain.aula.model.AulaTipo;

public class ConverterAulaDto {
	
	public static Aula requestToAula(AulaRequestDto aulaRequestDto, AulaTipo tipo) {
		
		Aula aula = new Aula();
		aula.setHorario(DateUtil.ISOUtcToGMTMinus3(aulaRequestDto.horario));
		aula.setDescricao(aulaRequestDto.descricao);
		aula.setTipo(tipo);
		
		return aula;
		
	}
	
	public static AulaResponseDto aulaToResponse(Aula aula) {
		AulaResponseDto aulaResponseDto = new AulaResponseDto(aula.getId(), DateUtil.GMTMinus3ToISOUtc(aula.getHorario()), aula.getDescricao(), aula.getTipo());
		return aulaResponseDto;
	}
	
	public static List<AulaResponseDto> aulaToResponseList(List<Aula> aulaList) {
		
		List<AulaResponseDto> aulaResponseList = new ArrayList<>();
		
		for (Aula aula : aulaList) {
			AulaResponseDto aulaResponseDto = new AulaResponseDto(aula.getId(), DateUtil.GMTMinus3ToISOUtc(aula.getHorario()), aula.getDescricao(), aula.getTipo());
			aulaResponseList.add(aulaResponseDto);	
		}
		
		return aulaResponseList;
		
	}
	
}
