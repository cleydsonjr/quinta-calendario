package br.ufg.inf.quintacalendario.service;

import br.ufg.inf.quintacalendario.model.Category;
import br.ufg.inf.quintacalendario.repository.CategoriaRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class CategoryService {

    private SessionFactory sessionFactory;

    public CategoryService(SessionFactory session) {
        super();
        sessionFactory = session;
    }

    public boolean save(Category category) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            validateCategory(category);

            new CategoriaRepository(session).salvar(category);
            transaction.commit();
            session.close();

            return true;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            return false;
        }
    }

    public List<Category> searchByDescription(String description) {
        Session session = sessionFactory.openSession();
        CategoriaRepository categoryRepository = new CategoriaRepository(session);
        return categoryRepository.listarPorDescricao(description);
    }

    private void validateCategory(Category category) throws IllegalArgumentException {
        if (category.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da categoria nao pode ser vazio");
        }

        if ((category.getName().trim().length()) < 4) {
            throw new IllegalArgumentException("O node da categoria deve ter no minimo 4 caracteres");
        }
    }

    public List<Category> listRecords() {
        Session session = sessionFactory.openSession();
        return new CategoriaRepository(session).listar();
    }

    public void truncateTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        new CategoriaRepository(session).limparTabela();
        transaction.commit();
        session.close();
    }

    public List<Category> listRecordsByDescription(String descricao) {
        Session session = sessionFactory.openSession();
        return new CategoriaRepository(session).listarPorDescricao(descricao);
    }

    public Category listById(Integer id) {
        Session session = sessionFactory.openSession();
        return new CategoriaRepository(session).listarPorId(id);
    }

    public void edit(Integer id, String name) {
        Session session = sessionFactory.openSession();
        CategoriaRepository repository = new CategoriaRepository(session);
        Category category = repository.listarPorId(id);

        Transaction transaction = session.beginTransaction();

        category.setName(name);
        repository.atualizar(category);

        transaction.commit();
        session.close();
    }

    public void remove(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        new CategoriaRepository(session).remover(id);
        transaction.commit();
        session.close();
    }
}
