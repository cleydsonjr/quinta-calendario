package br.ufg.inf.quintacalendario.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import br.ufg.inf.quintacalendario.model.Categoria;

public class CategoriaRepository extends AbstractRepository<Categoria>{

	public CategoriaRepository(Session session) {
		super(session);
	}

	@Override
	public List<Categoria> listarPorDescricao(String descricao) {
		StringBuilder jpql = new StringBuilder();
		jpql.append("Select t from categoria t where t.nome like :descricao");
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("descricao", "%"+descricao+"%");
		
		List<Categoria> categorias = select(jpql.toString(), parametros);
		return categorias;
	}
}
