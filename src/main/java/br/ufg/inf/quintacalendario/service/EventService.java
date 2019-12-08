package br.ufg.inf.quintacalendario.service;

import br.ufg.inf.quintacalendario.model.Event;
import br.ufg.inf.quintacalendario.repository.EventoRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Date;
import java.util.List;

/**
 * Service responsible for validating and communicating with the EventRepository.
 *
 * @author Joao Pedro Pinheiro
 */
public class EventService {

    private SessionFactory sessionFactory;

    /**
     * Class's default constructor
     * @param sessionFactory entity's SessionFactory
     */
    public EventService(SessionFactory sessionFactory) {
        super();
        this.sessionFactory = sessionFactory;
    }

    /**
     * Persist the object into the Database
     * @param event event to be persisted
     * @return true if the operation was successful or false if it wasn't
     */
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

    /**
     * Edit one instance of event in the database
     * @param event event to be edited
     */
    public void edit(Event event) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        EventoRepository eventRepository = new EventoRepository(session);
        eventRepository.atualizar(event);
        transaction.commit();
    }

    /**
     * List all events
     * @return a list of events
     */
    public List<Event> listRecords() {
        Session session = sessionFactory.openSession();
        return new EventoRepository(session).listar();
    }

    /**
     * List events by description
     * @param description description to be searched by
     * @return a list of Events
     */
    public List<Event> listRecordsByDescription(String description) {
        Session session = sessionFactory.openSession();
        return new EventoRepository(session).listarPorDescricao(description);
    }

    /**
     * Get a single event by id
     * @param id id of the event to be searched
     * @return a Event
     */
    public Event listById(long id) {
        Session session = sessionFactory.openSession();
        return new EventoRepository(session).listarPorId(id);
    }

    /**
     * List events by category
     * @param categoryId id of the category to be searched by
     * @return a list of Events
     */
    public List<Event> listByCategory(long categoryId) {
        Session session = sessionFactory.openSession();
        return new EventoRepository(session).listarPorCategoria(categoryId);
    }

    /**
     * List events by institute
     * @param instituteId id of the institute to be searched by
     * @return a list of Events
     */
    public List<Event> listByInstitute(long instituteId) {
        Session session = sessionFactory.openSession();
        return new EventoRepository(session).listarPorInstituto(instituteId);
    }

    /**
     * List events by regional
     * @param regionalId id of the regional to be searched by
     * @return a list of Events
     */
    public List<Event> listByRegional(long regionalId) {
        Session session = sessionFactory.openSession();
        return new EventoRepository(session).listarPorRegional(regionalId);
    }

    /**
     * List events by a given period
     * @param startDate start date of the period
     * @param endDate end date of the period
     * @return a list of Events
     */
    public List<Event> listByPeriod(Date startDate, Date endDate) {
        Session session = sessionFactory.openSession();
        return new EventoRepository(session).listarPorPeriodo(startDate, endDate);
    }

    /**
     * List events by a single date
     * @param date date to be searched by
     * @return a list of Events
     */
    public List<Event> listByDate(Date date) {
        Session session = sessionFactory.openSession();
        return new EventoRepository(session).listarPorData(date);

    }

    /**
     * Delete all records in the database
     */
    public void truncateTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        new EventoRepository(session).limparTabela();
        transaction.commit();
        session.close();
    }

    /**
     * Clears an event category
     * @param event event to be edited
     */
    private void deleteCategory(Event event) {
        event.setCategory(null);
    }

    /**
     * Clears an event institute
     * @param event event to be edited
     */
    private void deleteInstitute(Event event) {
        event.getInstitutes().clear();
    }

    /**
     * Clears all the event's regionals
     * @param event event to be edited
     */
    private void deleteRegionals(Event event) {
        event.getRegionais().clear();
    }

    /**
     * Clears all attributes of an given event
     * @param event event to be cleared
     */
    public void clearObject(Event event) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        deleteCategory(event);
        deleteInstitute(event);
        deleteRegionals(event);

        new EventoRepository(session).atualizar(event);
        transaction.commit();
    }

    /**
     * Get the session factory
     * @return a session factory
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Set the session factory
     * @param sessionFactory a session factory
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
