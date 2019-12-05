package br.ufg.inf.quintacalendario.repository;

import br.ufg.inf.quintacalendario.model.Category;
import org.hibernate.Session;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoriaRepository extends AbstractRepository<Category> {

    public CategoriaRepository(Session session) {
        super(session);
    }

    @Override
    public List<Category> listarPorDescricao(String descricao) {
        StringBuilder jpql = new StringBuilder();
        jpql.append("Select t from categoria t where t.nome like :descricao");

        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("descricao", "%" + descricao + "%");

        List<Category> categories = select(jpql.toString(), parametros);
        return categories;
    }
}
