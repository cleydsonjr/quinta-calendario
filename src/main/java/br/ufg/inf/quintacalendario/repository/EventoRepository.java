package br.ufg.inf.quintacalendario.repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import br.ufg.inf.quintacalendario.model.Evento;
/**
 * Classe que encapsula as regras de pesquisa em objetos da classe Evento da
 * base de dados.
 */
public class EventoRepository extends AbstractRepository<Evento> {

    public EventoRepository(Session session) {
        super(session);
    }

    /**
      * Método que lista todas os eventos que apresentam a descrição da entrada
      * do método nos seus atributos.
     * @param descricao descrição a ser comparada com a dos eventos cadastrados.
     * @return Uma lista de eventos que possuem a descrição recebida.
     */
    @Override
    public List<Evento> listarPorDescricao(String descricao) {
        StringBuilder jpql = new StringBuilder();
        jpql.append("Select t from Evento t where t.nome like :descricao");

        Map<String, Object> parametros = new HashMap<String, Object>();

        parametros.put("descricao", descricao);

        List<Evento> eventos = select(jpql.toString(), parametros);
        return eventos;
    }

    /**
      * Método que lista todas os eventos que apresentam o id de categoria
      * da entrada do método nos seus atributos.
     * @param idCategoria  id de categoria a ser comparada com a dos eventos
     * cadastrados.
     * @return Uma lista de eventos que possuem a categoria recebida.
     */
    public List<Evento> listarPorCategoria(long idCategoria) {
        StringBuilder jpql = new StringBuilder();
        jpql.append("select t from evento t inner join t.categoria c ")
                .append("where c.id = :idCategoria");

        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("idCategoria", idCategoria);

        List<Evento> eventos = select(jpql.toString(), parametros);

        return eventos;
    }

    /**
      * Método que lista todas os eventos que apresentam o id de instituto
      * da entrada do método nos seus atributos.
     * @param idInstituto  id de instituto a ser comparada com a dos eventos
     * cadastrados.
     * @return Uma lista de eventos que possuem o instituto recebida.
     */
    public List<Evento> listarPorInstituto(long idInstituto) {
        StringBuilder jpql = new StringBuilder();
        jpql.append("select t from evento t inner join t.institutos i ")
                .append("where i.id = :idInstituto");

        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("idInstituto", idInstituto);

        List<Evento> eventos = select(jpql.toString(), parametros);

        return eventos;
    }

    /**
      * Método que lista todas os eventos que apresentam o id de regional
      * da entrada do método nos seus atributos.
     * @param idRegional  id de regional a ser comparada com a dos eventos
     * cadastrados.
     * @return Uma lista de eventos que possuem a regional recebida.
     */
    public List<Evento> listarPorRegional(long idRegional) {
        StringBuilder jpql = new StringBuilder();
        jpql.append("select t from evento t inner join t.regionais r ")
                .append("where r.id = :idRegional");

        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("idRegional", idRegional);

        List<Evento> eventos = select(jpql.toString(), parametros);

        return eventos;
    }

    /**
      * Método que lista eventos que começam e terminam dentro do intervalo de
      *  tempo recebido.
     * @param dataInicial primeira data do intervalo de tempo recebido.
     * @param dataFinal última data do intervalo de tempo recebido.
     * @return Uma lista de eventos cadastrados que ocorrem dentro do intervalo
     * de tempo recebido.
     */
    public List<Evento> listarPorPeriodo(Date dataInicial, Date dataFinal) {
        StringBuilder jpql = new StringBuilder();
        jpql.append("select t from evento t ")
                .append("where t.dataInicial >= :dataInicial and t.dataFinal <= :dataFinal");

        Map<String, Object> parametros = new HashMap<String, Object>();

        parametros.put("dataInicial", dataInicial);
        parametros.put("dataFinal", dataFinal);

        List<Evento> eventos = select(jpql.toString(), parametros);

        return eventos;
    }

    /**
      * Método que lista os eventos que se iniciam na data de entrada.
     * @param dataInicial data a ser comparada com a data de início dos eventos
     * cadastrados.
     * @return Uma lista de eventos que se iniciam na data de entrada.
     */
    public List<Evento> listarPorData(Date dataInicial) {
        StringBuilder jpql = new StringBuilder();
        jpql.append("select t from evento t ")
                .append("where t.dataInicial = :dataInicial");

        Map<String, Object> parametros = new HashMap<String, Object>();

        parametros.put("dataInicial", dataInicial);

        List<Evento> eventos = select(jpql.toString(), parametros);

        return eventos;
    }
}
