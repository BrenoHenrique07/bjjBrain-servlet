package br.com.nobre.aluno.dao;

import org.hibernate.Session;

import br.com.nobre.aluno.dto.AlunoRequestDto;
import br.com.nobre.aluno.dto.AlunoResponseDto;
import br.com.nobre.aluno.model.Aluno;
import br.com.nobre.aluno.model.Faixa;
import br.com.nobre.commons.config.HibernateUtil;

public class AlunoCreateDao {
	
	public AlunoResponseDto createAluno(AlunoRequestDto requestDto) {
		
		Session session = HibernateUtil.openSession();
		
		Aluno aluno = alunoDtoToAluno(requestDto);
		AlunoResponseDto alunoResponse = null;
		
		try {
			
			session.beginTransaction();
			
			Aluno alunoCreated = (Aluno) session.save(aluno);
			alunoResponse = alunoToAlunoDto(alunoCreated);
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}

		return alunoResponse;
		
	}
	
	public Aluno alunoDtoToAluno(AlunoRequestDto requestDto) {
		
		Aluno aluno = new Aluno();
		aluno.setNome(requestDto.nome);
		aluno.setSobrenome(requestDto.sobrenome);
		aluno.setFaixaId(requestDto.faixaId);
		
		return aluno;
		
	}
	
	public AlunoResponseDto alunoToAlunoDto(Aluno aluno) {
		
		AlunoResponseDto responseDto = new AlunoResponseDto(aluno.getId(), aluno.getNome(), aluno.getSobrenome(), Faixa.fromId(aluno.getFaixaId()).getNome());
		return responseDto;
		
	}
	
	
}
