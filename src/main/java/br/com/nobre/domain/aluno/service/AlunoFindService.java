package br.com.nobre.domain.aluno.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import br.com.nobre.commons.dto.PageDto;
import br.com.nobre.commons.dto.PageableDto;
import br.com.nobre.commons.exception.NotFoundException;
import br.com.nobre.commons.utils.JsonUtil;
import br.com.nobre.commons.utils.PageableUtil;
import br.com.nobre.domain.aluno.dao.AlunoFindDao;
import br.com.nobre.domain.aluno.dto.AlunoResponseDto;
import br.com.nobre.domain.aluno.dto.ConverterAlunoDto;
import br.com.nobre.domain.aluno.model.Aluno;

public class AlunoFindService {
	
	private AlunoFindDao alunoDao;
	
	public AlunoFindService() {
		this.alunoDao = new AlunoFindDao();
	}
	
	public String findAll(Map<String, String[]> parameterMap) throws NotFoundException, IOException {
		
		Map<String, Object> paramsMap = PageableUtil.createParamnsMap(parameterMap);
		PageableDto pageableDto = PageableUtil.convertParamsToPageable(paramsMap.get("start"), paramsMap.get("limit"));

		long size = this.alunoDao.countAll(pageableDto.start, pageableDto.limit, paramsMap);
		List<Aluno> alunoList = this.alunoDao.findAll(pageableDto.start, pageableDto.limit, paramsMap);

		List<AlunoResponseDto> alunoResponseList = ConverterAlunoDto.alunoToResponseList(alunoList);
		PageDto<AlunoResponseDto> page = PageableUtil.createPage(alunoResponseList, pageableDto.start, pageableDto.limit, size);
		
		return JsonUtil.toJson(page);
		
	}
	
}
