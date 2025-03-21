package br.com.nobre.domain.aluno.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import br.com.nobre.commons.exception.NotFoundException;
import br.com.nobre.commons.utils.JsonUtil;
import br.com.nobre.domain.aluno.dao.AlunoCreateDao;
import br.com.nobre.domain.aluno.dto.AlunoRequestDto;
import br.com.nobre.domain.aluno.dto.AlunoResponseDto;
import br.com.nobre.domain.aluno.dto.ConverterAlunoDto;
import br.com.nobre.domain.aluno.model.Aluno;
import br.com.nobre.domain.aluno.model.Faixa;

public class AlunoCreateService {
	
	private AlunoCreateDao alunoCreateDao;
	
	public AlunoCreateService() {
		this.alunoCreateDao = new AlunoCreateDao();
	}
	
	public String createAluno(HttpServletRequest req) throws IOException {
		
		AlunoRequestDto alunoRequestDto = JsonUtil.requestBodyToJson(req, AlunoRequestDto.class);
		
		if(!Faixa.faixaExists(alunoRequestDto.faixaId)) {
			throw new NotFoundException("Faixa com id " + alunoRequestDto.faixaId + " não encontrada.");
		}
		
		Aluno aluno = ConverterAlunoDto.requestToAluno(alunoRequestDto);
		aluno.setAtivo(true);
		
		aluno = this.alunoCreateDao.createAluno(aluno);
		AlunoResponseDto alunoResponseDto = ConverterAlunoDto.alunoToResponse(aluno);
		
		return JsonUtil.toJson(alunoResponseDto);
		
	}
	
}
