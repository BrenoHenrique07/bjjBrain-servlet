package br.com.nobre.domain.aluno.dao;

import org.hibernate.Session;

import br.com.nobre.commons.config.HibernateUtil;
import br.com.nobre.domain.aluno.model.Aluno;

public class AlunoFindDao {

	public Aluno findAlunoById(int id) {

		Session session = HibernateUtil.openSession();

		Aluno aluno = session.find(Aluno.class, id);
		session.close();
			
		return aluno;
		
	}

}
