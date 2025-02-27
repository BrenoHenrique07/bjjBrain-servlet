package br.com.nobre.domain.aluno.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AlunoRequestDto {

    public final String nome;
    public final String sobrenome;
    public final int faixaId;

    @JsonCreator
    public AlunoRequestDto(@JsonProperty("nome") String nome, @JsonProperty("sobrenome") String sobrenome, @JsonProperty("faixaId") int faixaId) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.faixaId = faixaId;
    }
    
}
