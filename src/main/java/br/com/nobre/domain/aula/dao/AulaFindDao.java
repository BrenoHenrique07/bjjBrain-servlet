package br.com.nobre.domain.aula.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.Session;

import br.com.nobre.commons.config.HibernateUtil;
import br.com.nobre.domain.aula.model.Aula;
import br.com.nobre.domain.aula.model.AulaTipo;

public class AulaFindDao {
	
	public AulaTipo findTipoById(int id ) {
		
		Session session = HibernateUtil.openSession();
		
		AulaTipo tipo = session.find(AulaTipo.class, id);
		session.close();

		return tipo;
		
	}
	
	public long countAll(int start, int limit, Map<String, Object> paramsMap) throws IllegalArgumentException {
		
		Session session = HibernateUtil.openSession();

		StringBuilder hqlBuilder = new StringBuilder("SELECT count(a) FROM Aula a WHERE 1=1 ");
		appendFilters(hqlBuilder, paramsMap);

		TypedQuery<Long> query = session.createQuery(hqlBuilder.toString(), Long.class);
		appendParameters(query, paramsMap);

		Long size = query.getSingleResult();	
		session.close();

		return size.longValue();
		
	}
	
	public List<Aula> findAll(int start, int limit, Map<String, Object> paramsMap) {

		Session session = HibernateUtil.openSession();
		
		StringBuilder hqlBuilder = new StringBuilder();
		hqlBuilder.append(" SELECT a FROM Aula a ");
		hqlBuilder.append(" JOIN FETCH a.tipo t ");
		hqlBuilder.append(" WHERE 1=1 ");
		
		appendFilters(hqlBuilder, paramsMap);
		
		TypedQuery<Aula> query = session.createQuery(hqlBuilder.toString(), Aula.class);
		appendParameters(query, paramsMap);

		query.setFirstResult(start); 
		query.setMaxResults(limit); 
		
		List<Aula> aulaList = query.getResultList();
		session.close();
		
		return aulaList;
		
	}
	
	private void appendFilters(StringBuilder hqlBuilder, Map<String, Object> paramsMap) {
		
		if(paramsMap == null || paramsMap.isEmpty()) {
			return;
		}
		
		if (paramsMap.containsKey("id")) {
			hqlBuilder.append(" AND a.id = :id");
		}
		
	}
	
	private void appendParameters(Query query, Map<String, Object> paramsMap) {
		
		try {
			
			if (paramsMap.containsKey("id")) {
				int alunoId = Integer.valueOf((String) paramsMap.get("id"));
				query.setParameter("id", alunoId);
			}
			
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Parâmetros inválidos");
		}
		
	}
	
}
