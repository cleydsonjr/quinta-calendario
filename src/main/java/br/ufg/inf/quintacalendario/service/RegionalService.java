package br.ufg.inf.quintacalendario.service;

import br.ufg.inf.quintacalendario.model.Regional;
import br.ufg.inf.quintacalendario.repository.RegionalRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class RegionalService {
    private SessionFactory sessionFactory;

    public RegionalService(SessionFactory session) {
        super();
        sessionFactory = session;
    }

    public boolean save(Regional regional) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {

            validateRegional(regional);

            new RegionalRepository(session).salvar(regional);
            transaction.commit();
            session.close();

            return true;

        } catch (Exception e) {
            transaction.rollback();
            session.close();
            return false;
        }
    }

    public void edit(long id, String description) {
        Session session = sessionFactory.openSession();
        RegionalRepository regionalRepository = new RegionalRepository(session);
        Regional regional = regionalRepository.listarPorId(id);

        Transaction transaction = session.beginTransaction();

        regional.setName(description);
        regionalRepository.atualizar(regional);

        transaction.commit();
        session.close();
    }

    private void validateRegional(Regional regional) throws IllegalArgumentException {
        if (regional.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da regional nao pode ser vazio");
        }

        if ((regional.getNome().trim().length()) < 4) {
            throw new IllegalArgumentException("O node da regional deve ter no minimo 4 caracteres");
        }
    }

    public List<Regional> listRecords() {
        Session session = sessionFactory.openSession();
        return new RegionalRepository(session).listar();
    }

    public List<Regional> listRecordsByDescription(String description) {
        Session session = sessionFactory.openSession();
        return new RegionalRepository(session).listarPorDescricao(description);
    }

    public Regional listById(long id) {
        Session session = sessionFactory.openSession();
        return new RegionalRepository(session).listarPorId(id);
    }

    public void truncateTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        new RegionalRepository(session).limparTabela();
        transaction.commit();
        session.close();
    }

    public void remove(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        new RegionalRepository(session).remover(id);
        transaction.commit();
        session.close();
    }
}
