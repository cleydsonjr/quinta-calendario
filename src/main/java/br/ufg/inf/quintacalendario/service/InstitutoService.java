package br.ufg.inf.quintacalendario.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import br.ufg.inf.quintacalendario.model.Instituto;
import br.ufg.inf.quintacalendario.repository.InstitutoRepository;

/**
 * Classe que contém os métodos que realizam os serviços relacionados às
 * categorias de evento.
 */
public class InstitutoService {

    /**
     * Fábrica de dados responsável pela base de dados de institutos.
     */
    private SessionFactory sessionFactory;

    public InstitutoService(SessionFactory session) {
        super();
        sessionFactory = session;
    }

    /**
      * Método que recebe um instituto a ser salvo na base de dados.
     * @param instituto objeto da classe Instituto que será salvo na estrutura
     * de persistência.
     * @return O valor lógico do sucesso da operação.
     */
    public boolean salvar(Instituto instituto) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            validarInstituto(instituto);

            new InstitutoRepository(session).salvar(instituto);
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
      * Método que verifica a validade (ter um nome não vazio e com no mínimo 4
      * caracteres de um instituto.
     * @param instituto objeto da classe Instituto que será validado.
     * @throws IllegalArgumentException se o nome for vazio ou tiver menos de 4
     * caracteres.
     */
    private void validarInstituto(Instituto instituto) {
        if (instituto.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do instituto nao pode ser vazio");
        }

        if ((instituto.getNome().trim().length()) < 4) {
            throw new IllegalArgumentException("O nome do instituto deve ter no minimo 4 caracteres");
        }
    }

    /**
      * Método que lista todaos os institutos do banco de categorias institutos.
     * @return Uma lista de todos os institutos na base de dados.
     */
    public List<Instituto> listar() {
        Session session = sessionFactory.openSession();
        return new InstitutoRepository(session).listar();
    }

    /**
      * Método que remove todos os institutos do banco de institutos.
     */
    public void limparTabela() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        new InstitutoRepository(session).limparTabela();
        transaction.commit();
        session.close();
    }

    /**
      * Método que lista todas os institutos que remetem a um evento que
      * apresentam a descrição recebida pelo método.
     * @param descricao descrição a ser comparada com a dos eventos cadastrados.
     * @return Uma lista de institutos que remetem a eventos com a descrição
     * recebida.
     */
    public List<Instituto> listar(String descricao) {
        Session session = sessionFactory.openSession();
        return new InstitutoRepository(session).listarPorDescricao(descricao);
    }

    /**
      * Método que recebe um código inteiro e retorna um instituto que apresenta
      * o código de identificação inserido, se houver.
     * @param codigo codigo inserido para comparação com os codigos de
     * identificação dos institutos.
     * @return O instituto com o código de identificação inserido, se houver.
     */
    public Instituto listarPorId(Integer codigo) {
        Session session = sessionFactory.openSession();
        return new InstitutoRepository(session).listarPorId(codigo);
    }

    /**
      * Método que recebe um código de identificação e um nome. O código de
      * identificação denota o instituo que passará a ter o atributo nome
      * igual ao nome inserido.
     * @param codigo codigo de identificação do instituto que terá o nome
     * editado.
     * @param nome nome que será atribuído so instituto que apresenta o ID
     * inserido.
     */
    public void editar(Integer codigo, String nome) {
        Session session = sessionFactory.openSession();
        InstitutoRepository repository = new InstitutoRepository(session);
        Instituto instituto = repository.listarPorId(codigo);

        Transaction transaction = session.beginTransaction();

        instituto.setNome(nome);
        repository.atualizar(instituto);

        transaction.commit();
        session.close();
    }

    /**
      * Método que recebe um códgo de identificação que denota um instituto a
      * ser removido da base de dados.
     * @param codigo código do instituto que será removido.
     */
    public void remover(Integer codigo) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        new InstitutoRepository(session).remover(codigo);
        transaction.commit();
        session.close();
    }
}
