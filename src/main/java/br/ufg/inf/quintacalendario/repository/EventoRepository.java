package br.ufg.inf.quintacalendario.repository;

import br.ufg.inf.quintacalendario.model.Event;
import org.hibernate.Session;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventoRepository extends AbstractRepository<Event> {

    public EventoRepository(Session session) {
        super(session);
    }

    @Override
    public List<Event> listarPorDescricao(String descricao) {
        StringBuilder jpql = new StringBuilder();
        jpql.append("Select t from evento t where t.descricao like :descricao");

        Map<String, Object> parametros = new HashMap<String, Object>();

        parametros.put("descricao", "%" + descricao + "%");

        List<Event> events = select(jpql.toString(), parametros);
        return events;
    }

    public List<Event> listarPorCategoria(long idCategoria) {
        StringBuilder jpql = new StringBuilder();
        jpql.append("select t from evento t inner join t.categoria c ")
                .append("where c.id = :idCategoria");

        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("idCategoria", idCategoria);

        List<Event> events = select(jpql.toString(), parametros);

        return events;
    }

    public List<Event> listarPorInstituto(long idInstituto) {
        StringBuilder jpql = new StringBuilder();
        jpql.append("select t from evento t inner join t.institutos i ")
                .append("where i.id = :idInstituto");

        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("idInstituto", idInstituto);

        List<Event> events = select(jpql.toString(), parametros);

        return events;
    }

    public List<Event> listarPorRegional(long idRegional) {
        StringBuilder jpql = new StringBuilder();
        jpql.append("select t from evento t inner join t.regionais r ")
                .append("where r.id = :idRegional");

        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("idRegional", idRegional);

        List<Event> events = select(jpql.toString(), parametros);

        return events;
    }

    public List<Event> listarPorPeriodo(Date dataInicial, Date dataFinal) {
        StringBuilder jpql = new StringBuilder();
        jpql.append("select t from evento t ")
                .append("where t.dataInicial >= :dataInicial and t.dataFinal <= :dataFinal");

        Map<String, Object> parametros = new HashMap<String, Object>();

        parametros.put("dataInicial", dataInicial);
        parametros.put("dataFinal", dataFinal);

        List<Event> events = select(jpql.toString(), parametros);

        return events;
    }

    public List<Event> listarPorData(Date dataInicial) {
        StringBuilder jpql = new StringBuilder();
        jpql.append("select t from evento t ")
                .append("where t.dataInicial = :dataInicial");

        Map<String, Object> parametros = new HashMap<String, Object>();

        parametros.put("dataInicial", dataInicial);

        List<Event> events = select(jpql.toString(), parametros);

        return events;
    }
}
