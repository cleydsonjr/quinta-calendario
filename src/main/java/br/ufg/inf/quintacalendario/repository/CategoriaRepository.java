package br.ufg.inf.quintacalendario.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import br.ufg.inf.quintacalendario.model.Categoria;
/**
 * Classe que encapsula as regras de pesquisa em objetos da classe Categoria da
 * base de dados.
 */
public class CategoriaRepository extends AbstractRepository<Categoria> {

    public CategoriaRepository(Session session) {
        super(session);
    }

    /**
      * Método que lista todas as categorias que remetem a um evento que
      * apresentam a descrição recebida pelo método.
     * @param descricao descrição a ser comparada com a dos eventos cadastrados.
     * @return Uma lista de categorias de evento que remetem a eventos com a
     * descrição recebida.
     */
    @Override
    public List<Categoria> listarPorDescricao(String descricao) {
        StringBuilder jpql = new StringBuilder();
        jpql.append("Select t from categoria t where t.nome like :descricao");

        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("descricao", "%" + descricao + "%");

        List<Categoria> categorias = select(jpql.toString(), parametros);
        return categorias;
    }
}
