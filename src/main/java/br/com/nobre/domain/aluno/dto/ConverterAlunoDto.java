package br.com.nobre.domain.aluno.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.nobre.domain.aluno.model.Aluno;

public class ConverterAlunoDto {
	
	public static Aluno requestToAluno(AlunoRequestDto alunoRequestDto) {
		
		Aluno aluno = new Aluno();
		aluno.setNome(alunoRequestDto.nome);
		aluno.setSobrenome(alunoRequestDto.sobrenome);
		aluno.setFaixaId(alunoRequestDto.faixaId);
		
		return aluno;
		
	}
	
	public static List<AlunoResponseDto> alunoToResponseList (List<Aluno> alunoList) {
		
		List<AlunoResponseDto> alunoResponseList = new ArrayList<>();
		
		for (Aluno aluno : alunoList) {
			
			AlunoResponseDto alunoResponseDto = alunoToResponse(aluno);		
			alunoResponseList.add(alunoResponseDto);
			
		}

		return alunoResponseList;
		
	}
	
	public static AlunoResponseDto alunoToResponse(Aluno aluno) {
		
		AlunoResponseDto alunoResponseDto = new AlunoResponseDto(aluno);			
		return alunoResponseDto;
		
	}
	
	
}
