package br.ufg.inf.quintacalendario.repository;

import java.util.List;

import org.hibernate.Session;

import br.ufg.inf.quintacalendario.model.Instituto;

public class InstitutoRepository extends AbstractRepository<Instituto>{

	public InstitutoRepository(Session session) {
		super(session);
	}

	@Override
	public List<Instituto> listarPorDescricao(String descricao) {
		return null;
	}

}
