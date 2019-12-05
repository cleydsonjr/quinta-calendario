package br.ufg.inf.quintacalendario.repository;

import br.ufg.inf.quintacalendario.model.Institute;
import org.hibernate.Session;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InstitutoRepository extends AbstractRepository<Institute> {

    public InstitutoRepository(Session session) {
        super(session);
    }

    @Override
    public List<Institute> listarPorDescricao(String descricao) {
        StringBuilder jpql = new StringBuilder();
        jpql.append("Select t from instituto t where t.nome like :descricao");

        Map<String, Object> parametros = new HashMap<String, Object>();

        parametros.put("descricao", "%" + descricao + "%");

        List<Institute> institutes = select(jpql.toString(), parametros);
        return institutes;
    }

}
