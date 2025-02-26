package br.com.nobre.domain.frequencia.dto;

import br.com.nobre.domain.aluno.dto.AlunoResponseDto;
import br.com.nobre.domain.aula.dto.AulaResponseDto;

public class FrequenciaResponseDto {
	
	public final int id;
	public final AlunoResponseDto aluno;
	public final AulaResponseDto aula ;
	public boolean confirmado;
	
	public FrequenciaResponseDto(int id, AlunoResponseDto aluno, AulaResponseDto aula, boolean confirmado) {
		this.id = id;
		this.aluno = aluno;
		this.aula = aula;
		this.confirmado = confirmado;
	}
	
}
