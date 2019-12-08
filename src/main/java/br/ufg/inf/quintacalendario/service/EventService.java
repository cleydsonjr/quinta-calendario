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
        this.sessionFactory = sessionFactory;
    }

    public boolean save(Event event) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
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

    public void edit(Event event) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        EventoRepository eventRepository = new EventoRepository(session);
        eventRepository.atualizar(event);
        transaction.commit();
    }

    public List<Event> listRecords() {
        Session session = sessionFactory.openSession();
        return new EventoRepository(session).listar();
    }

    public List<Event> listRecordsByDescription(String description) {
        Session session = sessionFactory.openSession();
        return new EventoRepository(session).listarPorDescricao(description);
    }

    public Event listById(long id) {
        Session session = sessionFactory.openSession();
        return new EventoRepository(session).listarPorId(id);
    }

    public List<Event> listByCategory(long categoryId) {
        Session session = sessionFactory.openSession();
        return new EventoRepository(session).listarPorCategoria(categoryId);
    }

    public List<Event> listByInstitute(long instituteId) {
        Session session = sessionFactory.openSession();
        return new EventoRepository(session).listarPorInstituto(instituteId);
    }

    public List<Event> listByRegional(long regionalId) {
        Session session = sessionFactory.openSession();
        return new EventoRepository(session).listarPorRegional(regionalId);
    }

    public List<Event> listByPeriod(Date startDate, Date endDate) {
        Session session = sessionFactory.openSession();
        return new EventoRepository(session).listarPorPeriodo(startDate, endDate);
    }

    public List<Event> listByDate(Date date) {
        Session session = sessionFactory.openSession();
        return new EventoRepository(session).listarPorData(date);

    }

    public void truncateTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        new EventoRepository(session).limparTabela();
        transaction.commit();
        session.close();
    }

    private void deleteCategory(Event event) {
        event.setCategory(null);
    }

    private void deleteInstitute(Event event) {
        event.getInstitutes().clear();
    }

    private void deleteRegionals(Event event) {
        event.getRegionais().clear();
    }

    public void clearObject(Event event) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        deleteCategory(event);
        deleteInstitute(event);
        deleteRegionals(event);

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
