package br.com.nobre.domain.frequencia.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.hibernate.Session;

import br.com.nobre.commons.config.HibernateUtil;
import br.com.nobre.domain.frequencia.model.Frequencia;

public class FrequenciaFindDao {
	
	public long countAll(int start, int limit, Map<String, Object> paramsMap) throws IllegalArgumentException {
		
		Session session = HibernateUtil.openSession();

		StringBuilder hqlBuilder = new StringBuilder("SELECT count(f) FROM Frequencia f WHERE 1=1");
		appendFilters(hqlBuilder, paramsMap);

		Query query = session.createQuery(hqlBuilder.toString(), Long.class);
		appendParameters(query, paramsMap);

		Long size = (Long) query.getSingleResult();	
		session.close();

		return size.longValue();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Frequencia> findAll(int start, int limit, Map<String, Object> paramsMap) throws IllegalArgumentException {

		Session session = HibernateUtil.openSession();

		StringBuilder hqlBuilder = new StringBuilder("SELECT f FROM Frequencia f WHERE 1=1");
		appendFilters(hqlBuilder, paramsMap);

		Query query = session.createQuery(hqlBuilder.toString(), Frequencia.class);
		appendParameters(query, paramsMap);

		query.setFirstResult(start); 
		query.setMaxResults(limit); 

		List<Frequencia> frequenciaList = query.getResultList();
		session.close();

		return frequenciaList;
		
	}
	
	private void appendFilters(StringBuilder hqlBuilder, Map<String, Object> paramsMap) {
		
		if(paramsMap == null || paramsMap.isEmpty()) {
			return;
		}
		
		if (paramsMap.containsKey("alunoId")) {
			hqlBuilder.append(" AND f.aluno.id = :alunoId");
		}

		if (paramsMap.containsKey("aulaId")) {
			hqlBuilder.append(" AND f.aula.id = :aulaId");
		}
		
	}
	
	private void appendParameters(Query query, Map<String, Object> paramsMap) throws IllegalArgumentException {
		
		try {
			
			if (paramsMap.containsKey("alunoId")) {
				int alunoId = Integer.valueOf((String) paramsMap.get("alunoId"));
				query.setParameter("alunoId", alunoId);
			}

			if (paramsMap.containsKey("aulaId")) {
				int aulaId = Integer.valueOf((String) paramsMap.get("aulaId"));
				query.setParameter("aulaId", aulaId);
			}
			
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Parâmetros inválidos");
		}
		
	}
	
}
