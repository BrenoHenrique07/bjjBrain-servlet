package br.com.nobre.domain.aluno.service;

import java.io.IOException;

import br.com.nobre.commons.exception.NotFoundException;
import br.com.nobre.commons.utils.JsonUtil;
import br.com.nobre.domain.aluno.dao.AlunoFindDao;
import br.com.nobre.domain.aluno.dto.AlunoResponseDto;
import br.com.nobre.domain.aluno.dto.ConverterAlunoDto;
import br.com.nobre.domain.aluno.model.Aluno;

public class AlunoFindService {
	
	private AlunoFindDao alunoDao;
	
	public AlunoFindService() {
		this.alunoDao = new AlunoFindDao();
	}
	
	public String findAlunoById(int alunoId) throws NotFoundException, IOException {
		
		Aluno aluno = this.alunoDao.findAlunoById(alunoId);

		if(aluno == null) {
			throw new NotFoundException(String.format("Aluno com id %d não existe ou está inativado", alunoId));
		}
		
		AlunoResponseDto alunoResponseDto = ConverterAlunoDto.alunoToResponse(aluno);
		return JsonUtil.toJson(alunoResponseDto);
		
	}
	
}
