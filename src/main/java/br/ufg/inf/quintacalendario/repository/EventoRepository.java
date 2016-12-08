package br.ufg.inf.quintacalendario.repository;

import java.util.Date;
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
		jpql.append("Select t from evento t where t.descricao like :descricao");
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("descricao", "%"+descricao+"%");
		
		List<Evento> eventos = select(jpql.toString(), parametros);
		return eventos;
	}

	public List<Evento> listarPorCategoria(long idCategoria) {
		StringBuilder jpql = new StringBuilder();
		jpql.append("select t from evento t inner join t.categoria c ")
			.append("where c.id = :idCategoria");
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("idCategoria", idCategoria);
		
		List<Evento> eventos = select(jpql.toString(), parametros);
		
		return eventos;
	}

	public List<Evento> listarPorInstituto(long idInstituto) {
		StringBuilder jpql = new StringBuilder();
		jpql.append("select t from evento t inner join t.institutos i ")
			.append("where i.id = :idInstituto");
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("idInstituto", idInstituto);
		
		List<Evento> eventos = select(jpql.toString(), parametros);
		
		return eventos;
	}

	public List<Evento> listarPorRegional(long idRegional) {
		StringBuilder jpql = new StringBuilder();
		jpql.append("select t from evento t inner join t.regionais r ")
			.append("where r.id = :idRegional");
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("idRegional", idRegional);
		
		List<Evento> eventos = select(jpql.toString(), parametros);
		
		return eventos;
	}

	public List<Evento> listarPorPeriodo(Date dataInicial, Date dataFinal) {
		StringBuilder jpql = new StringBuilder();
		jpql.append("select t from evento t ")
			.append("where t.dataInicial >= :dataInicial and t.dataFinal <= :dataFinal");
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("dataInicial", dataInicial);
		parametros.put("dataFinal", dataFinal);
		
		List<Evento> eventos = select(jpql.toString(), parametros);
		
		return eventos;
	}

	public List<Evento> listarPorData(Date dataInicial) {
		StringBuilder jpql = new StringBuilder();
		jpql.append("select t from evento t ")
			.append("where t.dataInicial = :dataInicial");
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("dataInicial", dataInicial);
		
		List<Evento> eventos = select(jpql.toString(), parametros);
		
		return eventos;
	}
}
