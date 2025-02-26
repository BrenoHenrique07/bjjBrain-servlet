package br.com.nobre.domain.aluno.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.hibernate.Session;

import br.com.nobre.commons.config.HibernateUtil;
import br.com.nobre.domain.aluno.model.Aluno;

public class AlunoFindDao {

	public long countAll(int start, int limit, Map<String, Object> paramsMap) throws IllegalArgumentException {
		
		Session session = HibernateUtil.openSession();

		StringBuilder hqlBuilder = new StringBuilder("SELECT count(a) FROM Aluno a WHERE 1=1");
		appendFilters(hqlBuilder, paramsMap);

		Query query = session.createQuery(hqlBuilder.toString(), Long.class);
		appendParameters(query, paramsMap);

		Long size = (Long) query.getSingleResult();	
		session.close();

		return size.longValue();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Aluno> findAll(int start, int limit, Map<String, Object> paramsMap) {

		Session session = HibernateUtil.openSession();
		
		StringBuilder hqlBuilder = new StringBuilder();
		hqlBuilder.append(" SELECT a FROM Aluno a WHERE 1=1 ");
		appendFilters(hqlBuilder, paramsMap);
		
		Query query = session.createQuery(hqlBuilder.toString(), Aluno.class);
		appendParameters(query, paramsMap);

		query.setFirstResult(start); 
		query.setMaxResults(limit); 
		
		List<Aluno> alunoList = query.getResultList();
		session.close();
		
		return alunoList;
		
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
