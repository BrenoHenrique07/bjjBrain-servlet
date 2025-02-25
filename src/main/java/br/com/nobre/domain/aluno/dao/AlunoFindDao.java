package br.com.nobre.domain.aluno.dao;

import org.hibernate.Session;

import br.com.nobre.commons.config.HibernateUtil;
import br.com.nobre.domain.aluno.model.Aluno;

public class AlunoFindDao {

	public Aluno findAlunoById(int id) {

		Session session = HibernateUtil.openSession();
		
		Aluno aluno = null;
		
		try {	
			aluno = session.get(Aluno.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return aluno;
		
	}

}
