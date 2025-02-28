package br.com.nobre.domain.crogronoma.dto;

import java.time.LocalTime;
import java.util.Date;

import br.com.nobre.commons.utils.DateUtil;
import br.com.nobre.domain.aula.dto.AulaTipoDto;
import br.com.nobre.domain.crogronoma.model.DiaSemana;

public class CronogramaRequestDto {
	
	public final DiaSemana diaSemana;
	public final LocalTime hora;
	public final AulaTipoDto tipo;
	public final Date inicio;
	public final Date fim;
	
	public CronogramaRequestDto(int diaSemanaId, String hora, AulaTipoDto tipo, String inicio, String fim) {
		
		this.diaSemana = DiaSemana.fromId(diaSemanaId);
		this.hora = DateUtil.HourUTCToHourGMTMinus3(hora);
		this.tipo = tipo;
		this.inicio = DateUtil.ISOUtcToGMTMinus3(inicio);
		this.fim = DateUtil.ISOUtcToGMTMinus3(fim);
	}
	
}
