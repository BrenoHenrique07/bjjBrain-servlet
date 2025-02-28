package br.com.nobre.domain.aluno.dao;

import org.hibernate.Session;

import br.com.nobre.commons.config.HibernateUtil;
import br.com.nobre.domain.aluno.model.Aluno;

public class AlunoUpdateDao {

	public void update(Aluno aluno) {
		
		Session session = HibernateUtil.openSession();
		
		try {
			
			session.beginTransaction();
			session.update(aluno);
			session.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}

	}	

}
