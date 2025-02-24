package br.com.nobre.aluno.dao;

import org.hibernate.Session;

import br.com.nobre.aluno.model.Aluno;
import br.com.nobre.commons.config.HibernateUtil;

public class AlunoCreateDao {
	
	public Aluno createAluno(Aluno aluno) {
		
		Session session = HibernateUtil.openSession();
		
		try {
			
			session.beginTransaction();
			
			session.save(aluno);
			session.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}

		return aluno;
		
	}	
	
}
