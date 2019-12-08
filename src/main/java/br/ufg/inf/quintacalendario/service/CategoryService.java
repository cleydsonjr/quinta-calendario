package br.ufg.inf.quintacalendario.service;

import br.ufg.inf.quintacalendario.model.Category;
import br.ufg.inf.quintacalendario.repository.CategoriaRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Service responsible for validating and communicating with the CategoryRepository.
 *
 * @author Joao Pedro Pinheiro
 */
public class CategoryService {

    private SessionFactory sessionFactory;

    /**
     * Class's default constructor
     * @param sessionFactory entity's SessionFactory
     */
    public CategoryService(SessionFactory sessionFactory) {
        super();
        this.sessionFactory = sessionFactory;
    }

    /**
     * Persist the object into the Database
     * @param category category to be persisted
     * @return true if the operation was successful or false if it wasn't
     */
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

    /**
     * Validate a single instance of Category
     * @param category category to be validated
     * @throws IllegalArgumentException Validation unsuccessful
     */
    private void validateCategory(Category category) throws IllegalArgumentException {
        if (category.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da categoria nao pode ser vazio");
        }

        if ((category.getName().trim().length()) < 4) {
            throw new IllegalArgumentException("O node da categoria deve ter no minimo 4 caracteres");
        }
    }

    /**
     * List all categories
     * @return a list of categories
     */
    public List<Category> listRecords() {
        Session session = sessionFactory.openSession();
        return new CategoriaRepository(session).listar();
    }

    /**
     * Delete all categories in the database
     */
    public void truncateTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        new CategoriaRepository(session).limparTabela();
        transaction.commit();
        session.close();
    }

    /**
     * List categories by description
     * @param description description to be searched by
     * @return a list of categories
     */
    public List<Category> listRecordsByDescription(String description) {
        Session session = sessionFactory.openSession();
        return new CategoriaRepository(session).listarPorDescricao(description);
    }

    /**
     * Get single category by id
     * @param id id to be searched by
     * @return a Category
     */
    public Category listById(Integer id) {
        Session session = sessionFactory.openSession();
        return new CategoriaRepository(session).listarPorId(id);
    }

    /**
     * Edit one instance of category in the database
     * @param id id of the category to be edited
     * @param name new category's name
     */
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

    /**
     * Delete a single category in the database
     * @param id id of the category to be deleted
     */
    public void remove(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        new CategoriaRepository(session).remover(id);
        transaction.commit();
        session.close();
    }
}
