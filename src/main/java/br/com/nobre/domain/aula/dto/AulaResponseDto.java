package br.com.nobre.domain.aula.dto;

public class AulaResponseDto {
	
	public final int id;
	public final String horario;
	public final String descricao;
	public final AulaTipoDto tipo; 
	
	public AulaResponseDto(int id, String horario, String descricao, AulaTipoDto tipo) {
		this.id = id;
		this.horario = horario;
		this.descricao = descricao;
		this.tipo = tipo;
	}
	
}
