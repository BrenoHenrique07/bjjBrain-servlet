package br.com.nobre.domain.aula.dto;

public class AulaRequestDto {
	
	public final String horario;
	public final String descricao;
	public final int tipo; 
	
	public AulaRequestDto(String horario, String descricao, int tipo) {
		this.horario = horario;
		this.descricao = descricao;
		this.tipo = tipo;
	}
	
}
