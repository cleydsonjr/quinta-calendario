package br.ufg.inf.quintacalendario.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import br.ufg.inf.quintacalendario.model.Categoria;
import br.ufg.inf.quintacalendario.repository.CategoriaRepository;

/**
 * Classe que contém os métodos que realizam os serviços relacionados às
 * categorias de evento.
 */
public class CategoriaService {

    /**
     * Fábrica de dados responsável pela base de dados de categorias de evento.
     */
    private SessionFactory sessionFactory;

    public CategoriaService(SessionFactory session) {
        super();
        sessionFactory = session;
    }

    /**
      * Método que recebe uma categoria de evento a ser salva na base de dados.
     * @param categoria objeto da classe Categoria que será salvo na estrutura
     * de persistência.
     * @return O valor lógico do sucesso da operação.
     */
    public boolean salvar(Categoria categoria) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            validarCategoria(categoria);

            new CategoriaRepository(session).salvar(categoria);
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
      * Método que realiza uma consulta no banco de categorias de evento a
      * partir da descrição dos eventos.
     * @param descricao descrição que será comparada às dos eventos para
     * encontrar as categorias de eventos com a descrição recebida pelo método.
     * @return As categorias que remetem a eventos com a descrição recebida.
     */
    public List<Categoria> pesquisarPorDescricao(String descricao) {
        Session session = sessionFactory.openSession();
        CategoriaRepository categoriaRepository = new CategoriaRepository
            (session);
        List<Categoria> categorias = categoriaRepository.listarPorDescricao
            (descricao);
        return categorias;
    }

    /**
      * Método que verifica a validade (ter um nome não vazio e com no mínimo 4
      * caracteres de uma categoria.
     * @param categoria objeto da classe Categoria que será validado.
     * @throws IllegalArgumentException se o nome for vazio ou tiver menos de 4
     * caracteres.
     */
    public void validarCategoria(Categoria categoria) throws
            IllegalArgumentException {
        if (categoria.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da categoria nao pode "
                    + "ser vazio");
        }

        if ((categoria.getNome().trim().length()) < 4) {
            throw new IllegalArgumentException("O node da categoria deve ter "
                    + "no minimo 4 caracteres");
        }
    }

    /**
      * Método que lista todas as categorias do banco de categorias de evento.
     * @return Uma lista de todas as categorias de evento na base de dados.
     */
    public List<Categoria> listar() {
        Session session = sessionFactory.openSession();
        return new CategoriaRepository(session).listar();
    }

    /**
      * Método que remove todas as categorias do banco de categorias de evento.
     */
    public void limparTabela() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        new CategoriaRepository(session).limparTabela();
        transaction.commit();
        session.close();
    }

    /**
      * Método que lista todas as categorias que remetem a um evento que
      * apresentam a descrição recebida pelo método.
     * @param descricao descrição a ser comparada com a dos eventos cadastrados.
     * @return Uma lista de categorias de evento que remetem a eventos com a
     * descrição recebida.
     */
    public List<Categoria> listar(String descricao) {
        Session session = sessionFactory.openSession();
        return new CategoriaRepository(session).listarPorDescricao(descricao);
    }

    /**
      * Método que recebe um código de identificação e retorna a categoria de
      * evento que apresenta este código como atributo Id.
     * @param codigo código a ser comparado com os dos eventos cadastrados.
     * @return A categoria que apresenta o código de identificação recebido.
     */
    public Categoria listarPorId(Integer codigo) {
        Session session = sessionFactory.openSession();
        return new CategoriaRepository(session).listarPorId(codigo);
    }

    /**
      * Método que recebe um código de identificação e um nome. O código de
      * identificação denota a categoria que passará a ter o atributo nome
      * igual ao inserido.
     * @param codigo codigo de identificação da categoria de evento que terá o
     * nome editado.
     * @param nome nome que será atribuído à categoria de evento que apresenta o
     * Id inserido.
     */
    public void editar(Integer codigo, String nome) {
        Session session = sessionFactory.openSession();
        CategoriaRepository repository = new CategoriaRepository(session);
        Categoria categoria = repository.listarPorId(codigo);

        Transaction transaction = session.beginTransaction();

        categoria.setNome(nome);
        repository.atualizar(categoria);

        transaction.commit();
        session.close();
    }

    /**
      * Método que recebe um códgo de identificação que denota uma categoria a
      * ser removida da base de dados.
     * @param codigo código da categoria que será removida.
     */
    public void remover(Integer codigo) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        new CategoriaRepository(session).remover(codigo);
        transaction.commit();
        session.close();
    }
}
