package br.ufg.inf.quintacalendario.service;

import br.ufg.inf.quintacalendario.model.Event;
import br.ufg.inf.quintacalendario.repository.EventoRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Date;
import java.util.List;

public class EventService {

    private SessionFactory sessionFactory;

    public EventService(SessionFactory sessionFactory) {
        super();
        this.setSessionFactory(sessionFactory);
    }

    public boolean save(Event event) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            validarEvento(event);

            new EventoRepository(session).salvar(event);
            transaction.commit();
            session.close();

            return true;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void atualizar(Event event) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        EventoRepository eventoRepository = new EventoRepository(session);
        eventoRepository.atualizar(event);
        transaction.commit();
    }

    public List<Event> listRecords() {
        Session session = sessionFactory.openSession();
        return new EventoRepository(session).listar();
    }

    public List<Event> listRecordsByDescription(String descricao) {
        Session session = sessionFactory.openSession();
        List<Event> events = new EventoRepository(session).listarPorDescricao(descricao);
        return events;
    }

    public Event listById(long id) {
        Session session = sessionFactory.openSession();
        return new EventoRepository(session).listarPorId(id);
    }

    public List<Event> listarPorCategoria(long idCategoria) {
        Session session = sessionFactory.openSession();
        return new EventoRepository(session).listarPorCategoria(idCategoria);
    }

    public List<Event> listarPorInstituto(long idInstituto) {
        Session session = sessionFactory.openSession();
        return new EventoRepository(session).listarPorInstituto(idInstituto);
    }

    public List<Event> listarPorRegional(long idRegional) {
        Session session = sessionFactory.openSession();
        return new EventoRepository(session).listarPorRegional(idRegional);
    }

    public List<Event> listarEventosPorPeriodo(Date dataInicial, Date dataFinal) {
        Session session = sessionFactory.openSession();
        return new EventoRepository(session).listarPorPeriodo(dataInicial, dataFinal);
    }

    public List<Event> listarEventosPorData(Date dataInicial) {
        Session session = sessionFactory.openSession();
        return new EventoRepository(session).listarPorData(dataInicial);

    }

    public void limparTabela() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        new EventoRepository(session).limparTabela();
        transaction.commit();
        session.close();
    }

    public void removerCategoriasEvento(Event event) {
        event.setCategory(null);
    }

    public void removerInstitutoEvento(Event event) {
        event.getInstitutes().clear();
    }

    public void removerRegionalEvento(Event event) {
        event.getRegionais().clear();
    }

    private void validarEvento(Event event) {
        // TODO Criar validacoes de evento
    }

    public void limparObjeto(Event event) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        removerCategoriasEvento(event);
        removerInstitutoEvento(event);
        removerRegionalEvento(event);

        new EventoRepository(session).atualizar(event);
        transaction.commit();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
