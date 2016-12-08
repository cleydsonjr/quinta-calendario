package br.ufg.inf.quintacalendario.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import br.ufg.inf.quintacalendario.model.Regional;
/**
 * Classe que encapsula as regras de pesquisa em objetos da classe Regional da
 * base de dados.
 */
public class RegionalRepository extends AbstractRepository<Regional> {

    public RegionalRepository(Session session) {
        super(session);
    }

    /**
      * Método que lista todas as regionais que remetem a um evento que
      * apresentam a descrição recebida pelo método.
     * @param descricao descrição a ser comparada com a dos eventos cadastrados.
     * @return Uma lista regionais que remetem a eventos com a descrição
     * recebida.
     */
    @Override
    public List<Regional> listarPorDescricao(String descricao) {
        StringBuilder jpql = new StringBuilder();
        jpql.append("Select t from regional t where t.nome like :descricao");

        Map<String, Object> parametros = new HashMap<String, Object>();

        parametros.put("descricao", "%" + descricao + "%");

        List<Regional> regionais = select(jpql.toString(), parametros);
        return regionais;
    }
}
