package br.com.nobre.aluno.dto;

public class AlunoRequestDto {
	
	public final String nome;
	public final String sobrenome;
	public final int faixaId;
	
	public AlunoRequestDto(String nome, String sobrenome, int faixaId) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.faixaId = faixaId;
	}
	
}
