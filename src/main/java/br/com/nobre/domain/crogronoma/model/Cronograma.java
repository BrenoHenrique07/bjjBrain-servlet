package br.com.nobre.domain.crogronoma.model;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.nobre.domain.aula.model.AulaTipo;

@Entity
@Table(name = "cronograma")
public class Cronograma implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "DIA_SEMANA_ID")
	private int diaSemanaId;

	@Column(name = "HORA")
	private LocalTime hora;

	@ManyToOne
	@JoinColumn(name = "TIPO_ID", referencedColumnName = "ID")
	private AulaTipo aulaTipo;

	@Column(name = "INICIO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date inicio;

	@Column(name = "fim")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fim;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDiaSemanaId() {
		return diaSemanaId;
	}

	public void setDiaSemanaId(int diaSemanaId) {
		this.diaSemanaId = diaSemanaId;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public AulaTipo getAulaTipo() {
		return aulaTipo;
	}

	public void setAulaTipo(AulaTipo aulaTipo) {
		this.aulaTipo = aulaTipo;
	}

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getFim() {
		return fim;
	}

	public void setFim(Date fim) {
		this.fim = fim;
	}

}
