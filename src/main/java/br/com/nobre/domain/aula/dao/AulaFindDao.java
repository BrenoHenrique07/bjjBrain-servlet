package br.com.nobre.domain.aula.dao;

import org.hibernate.Session;

import br.com.nobre.commons.config.HibernateUtil;
import br.com.nobre.domain.aula.model.Aula;
import br.com.nobre.domain.aula.model.AulaTipo;

public class AulaFindDao {
	
	public Aula findAulaById(int id) {

		Session session = HibernateUtil.openSession();

		Aula aula = session.find(Aula.class, id);
		session.close();

		return aula;
		
	}
	
	public AulaTipo findTipoById(int id ) {
		
		Session session = HibernateUtil.openSession();
		
		AulaTipo tipo = session.find(AulaTipo.class, id);
		session.close();

		return tipo;
		
	}
	
}
