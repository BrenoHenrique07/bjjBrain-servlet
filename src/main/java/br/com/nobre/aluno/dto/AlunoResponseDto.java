package br.com.nobre.aluno.dto;

public class AlunoResponseDto {
	
	public final int id;
	public final String nome;
	public final String sobrenome;
	public final String faixa; 
	
	public AlunoResponseDto(int id, String nome, String sobrenome, String faixa) {
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.faixa = faixa;
	}
	
}
