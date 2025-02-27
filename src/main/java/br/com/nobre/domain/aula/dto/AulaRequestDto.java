package br.com.nobre.domain.aula.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AulaRequestDto {
	
	public final String horario;
	public final String descricao;
	public final int tipo; 
	
	@JsonCreator
	public AulaRequestDto(@JsonProperty("horario") String horario, @JsonProperty("descricao") String descricao, @JsonProperty("tipo") int tipo) {
		this.horario = horario;
		this.descricao = descricao;
		this.tipo = tipo;
	}
	
}
