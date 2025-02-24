package br.com.nobre.aula.dao;

import org.hibernate.Session;

import br.com.nobre.aula.model.Aula;
import br.com.nobre.commons.config.HibernateUtil;

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
