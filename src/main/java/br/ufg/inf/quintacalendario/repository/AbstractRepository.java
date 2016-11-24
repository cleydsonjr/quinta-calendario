package br.ufg.inf.quintacalendario.repository;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;

public abstract class AbstractRepository<T> implements IRepository<T> {
	
	protected Session session;
	private final Class<T> modelClass;
	
	@SuppressWarnings("unchecked")
	public AbstractRepository(Session session) {
		super();
		this.session = session;
		this.modelClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public String getModelAlias() {
		return String.valueOf(this.modelClass.getSimpleName().charAt(0))
				.toLowerCase();
	}

	@Override
	public void salvar(T t) {
        session.save(t);
	}

	@Override
	public void atualizar(T t) {
		session.merge(t);
	}

	@Override
	public List<T> listar() {
		String sql = "SELECT " + getModelAlias() + " FROM "
				    + this.modelClass.getSimpleName() + " " + getModelAlias();
		
		return session.createQuery(sql, modelClass).getResultList();
	}

	@Override
	public T listarPorId(long id) {
		return session.find(modelClass, id);
	}
}
