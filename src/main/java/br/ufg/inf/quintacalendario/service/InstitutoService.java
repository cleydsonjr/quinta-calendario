package br.ufg.inf.quintacalendario.service;

import br.ufg.inf.quintacalendario.model.Institute;
import br.ufg.inf.quintacalendario.repository.InstitutoRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class InstitutoService {
    private SessionFactory sessionFactory;

    public InstitutoService(SessionFactory session) {
        super();
        sessionFactory = session;
    }

    public boolean salvar(Institute institute) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            validarInstituto(institute);

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

    private void validarInstituto(Institute institute) {
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

    public void limparTabela() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        new InstitutoRepository(session).limparTabela();
        transaction.commit();
        session.close();
    }

    public List<Institute> listRecordsByDescription(String descricao) {
        Session session = sessionFactory.openSession();
        return new InstitutoRepository(session).listarPorDescricao(descricao);
    }

    public Institute listById(Integer codigo) {
        Session session = sessionFactory.openSession();
        return new InstitutoRepository(session).listarPorId(codigo);
    }

    public void edit(Integer codigo, String nome) {
        Session session = sessionFactory.openSession();
        InstitutoRepository repository = new InstitutoRepository(session);
        Institute institute = repository.listarPorId(codigo);

        Transaction transaction = session.beginTransaction();

        institute.setName(nome);
        repository.atualizar(institute);

        transaction.commit();
        session.close();
    }

    public void remove(Integer codigo) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        new InstitutoRepository(session).remover(codigo);
        transaction.commit();
        session.close();
    }
}
