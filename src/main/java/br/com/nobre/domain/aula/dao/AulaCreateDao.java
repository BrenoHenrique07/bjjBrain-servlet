package br.com.nobre.domain.aula.dao;

import org.hibernate.Session;

import br.com.nobre.commons.config.HibernateUtil;
import br.com.nobre.domain.aula.model.Aula;

public class AulaCreateDao {
	
	public Aula createAula(Aula aula) {
	
		Session session = HibernateUtil.openSession();
		
		try {
			
			session.beginTransaction();
			
			session.save(aula);
			session.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}

		return aula;
		
	}
	
}
