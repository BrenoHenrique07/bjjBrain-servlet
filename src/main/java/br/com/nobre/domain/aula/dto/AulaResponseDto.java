package br.com.nobre.domain.aula.dto;

import br.com.nobre.domain.aula.model.AulaTipo;

public class AulaResponseDto {
	
	public final int id;
	public final String horario;
	public final String descricao;
	public final AulaTipo tipo; 
	
	public AulaResponseDto(int id, String horario, String descricao, AulaTipo tipo) {
		this.id = id;
		this.horario = horario;
		this.descricao = descricao;
		this.tipo = tipo;
	}
	
}
