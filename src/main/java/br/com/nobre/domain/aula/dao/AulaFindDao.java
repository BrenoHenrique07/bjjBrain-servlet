package br.com.nobre.domain.aula.dao;

import org.hibernate.Session;

import br.com.nobre.commons.config.HibernateUtil;
import br.com.nobre.domain.aula.model.Aula;
import br.com.nobre.domain.aula.model.AulaTipo;

public class AulaFindDao {
	
	public Aula findAulaById(int id) {

		Session session = HibernateUtil.openSession();
		
		Aula aula = null;
		
		try {	
			aula = session.get(Aula.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return aula;
		
	}
	
	public AulaTipo findTipoById(int id ) {
		
		Session session = HibernateUtil.openSession();
		
		AulaTipo tipo = null;
		
		try {	
			tipo = session.get(AulaTipo.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return tipo;
		
	}
	
}
