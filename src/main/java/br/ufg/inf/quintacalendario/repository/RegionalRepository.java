package br.ufg.inf.quintacalendario.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import br.ufg.inf.quintacalendario.model.Regional;

public class RegionalRepository extends AbstractRepository<Regional>{

	public RegionalRepository(Session session) {
		super(session);
	}

	@Override
	public List<Regional> listarPorDescricao(String descricao) {
		StringBuilder jpql = new StringBuilder();
		jpql.append("Select t from regional t where t.nome like :descricao");
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("descricao", "%"+descricao+"%");
		
		List<Regional> regionais = select(jpql.toString(), parametros);
		return regionais;
	}
}
