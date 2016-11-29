package br.ufg.inf.quintacalendario.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import br.ufg.inf.quintacalendario.model.Evento;

public class EventoRepository extends AbstractRepository<Evento>{

	public EventoRepository(Session session) {
		super(session);
	}

	@Override
	public List<Evento> listarPorDescricao(String descricao) {
		StringBuilder jpql = new StringBuilder();
		jpql.append("Select t from Evento t where t.nome like :descricao");
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("descricao", descricao);
		
		List<Evento> eventos = select(jpql.toString(), parametros);
		return eventos;
	}
}
