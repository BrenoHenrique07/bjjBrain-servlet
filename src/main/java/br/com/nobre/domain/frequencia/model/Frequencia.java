package br.com.nobre.domain.frequencia.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.nobre.domain.aluno.model.Aluno;
import br.com.nobre.domain.aula.model.Aula;

@Entity
@Table(name = "frequencia")
public class Frequencia implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "ALUNO_ID", referencedColumnName = "ID")
	private Aluno aluno;

	@ManyToOne
	@JoinColumn(name = "AULA_ID", referencedColumnName = "ID")
	private Aula aula;

	@Column(name = "CONFIRMADO")
	private boolean confirmado;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Aula getAula() {
		return aula;
	}

	public void setAula(Aula aula) {
		this.aula = aula;
	}

	public boolean isConfirmado() {
		return confirmado;
	}

	public void setConfirmado(boolean confirmado) {
		this.confirmado = confirmado;
	}

	@Override
	public String toString() {
		return "Frequencia [id=" + id + ", aluno=" + aluno + ", aula=" + aula + ", confirmado=" + confirmado + "]";
	}

}
