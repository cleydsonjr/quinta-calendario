package br.ufg.inf.quintacalendario.repository;

import java.util.List;

import org.hibernate.Session;

import br.ufg.inf.quintacalendario.model.Regional;

public class RegionalRepository extends AbstractRepository<Regional>{

	public RegionalRepository(Session session) {
		super(session);
	}

	@Override
	public List<Regional> listarPorDescricao(String descricao) {
		return null;
	}

}
