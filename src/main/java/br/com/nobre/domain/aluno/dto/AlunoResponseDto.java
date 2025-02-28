package br.com.nobre.domain.aluno.dto;

import br.com.nobre.domain.aluno.model.Aluno;
import br.com.nobre.domain.aluno.model.Faixa;

public class AlunoResponseDto {
	
	public final int id;
	public final String nome;
	public final String sobrenome;
	public final Faixa faixa;
	public final boolean ativo;
	
	public AlunoResponseDto(Aluno aluno) {
		this.id = aluno.getId();
		this.nome = aluno.getNome();
		this.sobrenome = aluno.getSobrenome();
		this.faixa = Faixa.fromId(aluno.getFaixaId());
		this.ativo = aluno.isAtivo();
	}

}
