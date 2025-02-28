package br.com.nobre.domain.aluno.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import br.com.nobre.commons.exception.NotFoundException;
import br.com.nobre.commons.utils.JsonUtil;
import br.com.nobre.domain.aluno.dao.AlunoFindDao;
import br.com.nobre.domain.aluno.dao.AlunoUpdateDao;
import br.com.nobre.domain.aluno.dto.AlunoRequestDto;
import br.com.nobre.domain.aluno.dto.AlunoResponseDto;
import br.com.nobre.domain.aluno.dto.ConverterAlunoDto;
import br.com.nobre.domain.aluno.model.Aluno;

public class AlunoUpdateService {
	
	private AlunoFindDao alunoFindDao;
	private AlunoUpdateDao alunoUpdateDao;
	
	public AlunoUpdateService() {
		this.alunoFindDao = new AlunoFindDao();
		this.alunoUpdateDao = new AlunoUpdateDao();
	}
	
	public String updateAluno(HttpServletRequest req, int id) throws IOException {

		Aluno aluno = this.alunoFindDao.findById(id);
		
		if(aluno == null) {
			throw new NotFoundException(String.format("Aluno com id %d não existe ou está inativado", id));
		}
		
		AlunoRequestDto alunoRequestDto = JsonUtil.requestBodyToJson(req, AlunoRequestDto.class);
		
		if(alunoRequestDto.nome != null) {
			aluno.setNome(alunoRequestDto.nome);
		}
		
		if(alunoRequestDto.sobrenome != null) {
			aluno.setSobrenome(alunoRequestDto.sobrenome);
		}
		
		if(alunoRequestDto.ativo != null) {
			aluno.setAtivo(alunoRequestDto.ativo);
		}
		
		if(alunoRequestDto.faixaId != null) {
			aluno.setFaixaId(alunoRequestDto.faixaId);
		}
		
		this.alunoUpdateDao.update(aluno);
		AlunoResponseDto alunoResponseDto = ConverterAlunoDto.alunoToResponse(aluno);
		
		return JsonUtil.toJson(alunoResponseDto);
		
	}
 	
	public void disableAluno(int id) {
		
		Aluno aluno = this.alunoFindDao.findById(id);
		
		if(aluno == null) {
			throw new NotFoundException(String.format("Aluno com id %d não existe ou está inativado", id));
		}
		
		aluno.setAtivo(false);
		this.alunoUpdateDao.update(aluno);
		
	}
	
}
