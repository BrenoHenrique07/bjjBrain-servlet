package br.com.nobre.aluno.dao;

import org.hibernate.Session;

import br.com.nobre.aluno.model.Aluno;
import br.com.nobre.commons.config.HibernateUtil;

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
