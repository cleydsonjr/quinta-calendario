package br.ufg.inf.quintacalendario.service;

import br.ufg.inf.quintacalendario.model.Institute;
import br.ufg.inf.quintacalendario.repository.InstitutoRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class InstituteService {
    private SessionFactory sessionFactory;

    public InstituteService(SessionFactory session) {
        super();
        sessionFactory = session;
    }

    public boolean save(Institute institute) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            validadeInstitute(institute);

            new InstitutoRepository(session).salvar(institute);
            transaction.commit();
            session.close();

            return true;

        } catch (Exception e) {
            transaction.rollback();
            session.close();
            return false;
        }
    }

    private void validadeInstitute(Institute institute) {
        if (institute.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do instituto nao pode ser vazio");
        }

        if ((institute.getNome().trim().length()) < 4) {
            throw new IllegalArgumentException("O nome do instituto deve ter no minimo 4 caracteres");
        }
    }

    public List<Institute> listRecords() {
        Session session = sessionFactory.openSession();
        return new InstitutoRepository(session).listar();
    }

    public void truncateTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        new InstitutoRepository(session).limparTabela();
        transaction.commit();
        session.close();
    }

    public List<Institute> listRecordsByDescription(String description) {
        Session session = sessionFactory.openSession();
        return new InstitutoRepository(session).listarPorDescricao(description);
    }

    public Institute listById(Integer id) {
        Session session = sessionFactory.openSession();
        return new InstitutoRepository(session).listarPorId(id);
    }

    public void edit(Integer id, String name) {
        Session session = sessionFactory.openSession();
        InstitutoRepository instituteRepository = new InstitutoRepository(session);
        Institute institute = instituteRepository.listarPorId(id);

        Transaction transaction = session.beginTransaction();

        institute.setName(name);
        instituteRepository.atualizar(institute);

        transaction.commit();
        session.close();
    }

    public void remove(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        new InstitutoRepository(session).remover(id);
        transaction.commit();
        session.close();
    }
}
