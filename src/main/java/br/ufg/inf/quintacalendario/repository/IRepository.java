package br.ufg.inf.quintacalendario.repository;

import java.util.List;

public interface IRepository<T> {
	public void salvar(T t);
	public void atualizar(T t);
	public List<T> listar();
	public List<T> listarPorDescricao(String descricao);
	public T listarPorId(long id);
	public void remover(long id);
	public List<T> select(String jpql);
	public void limparTabela();
	
	
}
