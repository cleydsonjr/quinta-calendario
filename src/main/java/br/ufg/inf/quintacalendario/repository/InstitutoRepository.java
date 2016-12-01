package br.ufg.inf.quintacalendario.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import br.ufg.inf.quintacalendario.model.Instituto;

public class InstitutoRepository extends AbstractRepository<Instituto>{

	public InstitutoRepository(Session session) {
		super(session);
	}

	@Override
	public List<Instituto> listarPorDescricao(String descricao) {
		StringBuilder jpql = new StringBuilder();
		jpql.append("Select t from instituto t where t.nome like :descricao");
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("descricao", "%"+descricao+"%");
		
		List<Instituto> institutos = select(jpql.toString(), parametros);
		return institutos;
	}

}
