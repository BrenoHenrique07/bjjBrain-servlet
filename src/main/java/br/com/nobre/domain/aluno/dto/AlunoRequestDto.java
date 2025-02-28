package br.com.nobre.domain.aluno.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AlunoRequestDto {

	public final String nome;
	public final String sobrenome;
	public final Integer faixaId;
	public final Boolean ativo;

	@JsonCreator
	public AlunoRequestDto(@JsonProperty("nome") String nome, @JsonProperty("sobrenome") String sobrenome,
			@JsonProperty("faixaId") Integer faixaId, @JsonProperty("ativo") Boolean ativo) {

		this.nome = nome;
		this.sobrenome = sobrenome;
		this.faixaId = faixaId;
		this.ativo = ativo;

	}

	@Override
	public String toString() {
		return "AlunoRequestDto [nome=" + nome + ", sobrenome=" + sobrenome + ", faixaId=" + faixaId + ", ativo="
				+ ativo + "]";
	}

}
