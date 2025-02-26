package br.com.nobre.domain.aluno.dto;

import br.com.nobre.domain.aluno.model.Faixa;

public class AlunoResponseDto {
	
	public final int id;
	public final String nome;
	public final String sobrenome;
	public final Faixa faixa;
	
	public AlunoResponseDto(int id, String nome, String sobrenome, Faixa faixa) {
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.faixa = faixa;
	}
	
}
