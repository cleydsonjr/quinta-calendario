package br.ufg.inf.quintacalendario.repository;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

public abstract class AbstractRepository<T> implements IRepository<T> {

    private final Class<T> modelClass;
    protected Session session;

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
                + this.modelClass.getSimpleName().toLowerCase() + " " + getModelAlias();

        return session.createQuery(sql, modelClass).getResultList();
    }

    @Override
    public T listarPorId(long id) {
        return session.find(modelClass, id);
    }

    @Override
    public List<T> select(String jpql) {
        List<T> list = session.createQuery(jpql, modelClass).getResultList();
        return list;
    }

    public List<T> select(String jpql, Map<String, Object> parametros) {
        Query<T> query = session.createQuery(jpql, modelClass);

        for (Map.Entry<String, Object> parameter : parametros.entrySet()) {
            query.setParameter(parameter.getKey(), parameter.getValue());
        }

        List<T> list = query.getResultList();

        return list;
    }

    @Override
    public void limparTabela() {
        StringBuilder jpql = new StringBuilder();
        jpql.append("Delete from " + this.modelClass.getSimpleName().toLowerCase());

        Query<?> query = session.createQuery(jpql.toString());
        query.executeUpdate();

    }

    @Override
    public void remover(long id) {
        T t = listarPorId(id);
        session.remove(t);
    }
}
