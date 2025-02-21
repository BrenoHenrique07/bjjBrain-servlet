package br.com.nobre.aula.model;

import java.time.LocalDateTime;

public class Aula {

	private int id;
	private LocalDateTime horario;
	private AulaTipo aulaTipo;
	private String descricao;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getHorario() {
		return horario;
	}

	public void setHorario(LocalDateTime horario) {
		this.horario = horario;
	}

	public AulaTipo getAulaTipo() {
		return aulaTipo;
	}

	public void setAulaTipo(AulaTipo aulaTipo) {
		this.aulaTipo = aulaTipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
