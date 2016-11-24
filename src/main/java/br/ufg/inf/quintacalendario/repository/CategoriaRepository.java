package br.ufg.inf.quintacalendario.repository;

import java.util.List;

import org.hibernate.Session;

import br.ufg.inf.quintacalendario.model.Categoria;

public class CategoriaRepository extends AbstractRepository<Categoria>{

	public CategoriaRepository(Session session) {
		super(session);
	}

	@Override
	public List<Categoria> listarPorDescricao(String descricao) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
