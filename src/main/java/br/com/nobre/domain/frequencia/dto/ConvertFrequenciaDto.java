package br.com.nobre.domain.frequencia.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.nobre.domain.aluno.dto.AlunoResponseDto;
import br.com.nobre.domain.aluno.dto.ConverterAlunoDto;
import br.com.nobre.domain.aula.dto.AulaResponseDto;
import br.com.nobre.domain.aula.dto.ConverterAulaDto;
import br.com.nobre.domain.frequencia.model.Frequencia;

public class ConvertFrequenciaDto {
	
	public static List<FrequenciaResponseDto> frequenciaListToResponse (List<Frequencia> frequenciaList) {
		
		List<FrequenciaResponseDto> frequenciaDtoList = new ArrayList<>();
		
		for (Frequencia frequencia : frequenciaList) {
			
			AlunoResponseDto alunoDto = ConverterAlunoDto.alunoToResponse(frequencia.getAluno());
			AulaResponseDto aulaDto = ConverterAulaDto.aulaToResponse(frequencia.getAula());
			FrequenciaResponseDto frequenciaDto = new FrequenciaResponseDto(frequencia.getId(), alunoDto, aulaDto, frequencia.isConfirmado());
			
			frequenciaDtoList.add(frequenciaDto);
			
		}
		
		return frequenciaDtoList;
		
	}
	
}
