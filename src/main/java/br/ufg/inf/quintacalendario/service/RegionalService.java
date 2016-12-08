package br.ufg.inf.quintacalendario.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import br.ufg.inf.quintacalendario.model.Regional;
import br.ufg.inf.quintacalendario.repository.RegionalRepository;

/**
 * Classe que contém os métodos que realizam os serviços relacionados às
 * categorias de evento.
 */
public class RegionalService {
    /**
     * Fábrica de dados responsável pela base de dados de categorias de evento.
     */
    private SessionFactory sessionFactory;

    public RegionalService(SessionFactory session) {
        super();
        sessionFactory = session;
    }

    /**
      * Método que recebe uma regional a ser salva na base de dados.
     * @param regional objeto da classe Regional que será salvo na estrutura
     * de persistência.
     * @return O valor lógico do sucesso da operação.
     */
    public boolean salvar(Regional regional) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {

            validarRegional(regional);

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

    /**
      * Método que recebe um código de identificação e um nome. O código de
      * identificação denota a regional que passará a ter o atributo nome
      * igual ao inserido.
     * @param codigo codigo de identificação daregional que terá o nome editado.
     * @param nome nome que será atribuído à regional que apresenta o Id
     * inserido.
     */
    public void editar(long codigo, String nome) {
        Session session = sessionFactory.openSession();
        RegionalRepository repository = new RegionalRepository(session);
        Regional regional = repository.listarPorId(codigo);

        Transaction transaction = session.beginTransaction();

        regional.setNome(nome);
        repository.atualizar(regional);

        transaction.commit();
        session.close();
    }

    /**
      * Método que verifica a validade (ter um nome não vazio e com no mínimo 4
      * caracteres de uma regional.
     * @param regional objeto da classe Regional que será validado.
     * @throws IllegalArgumentException se o nome for vazio ou tiver menos de 4
     * caracteres.
     */
    public void validarRegional(Regional regional) throws IllegalArgumentException {
        if (regional.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da regional nao pode ser vazio");
        }

        if ((regional.getNome().trim().length()) < 4) {
            throw new IllegalArgumentException("O node da regional deve ter no minimo 4 caracteres");
        }
    }

    /**
      * Método que lista todas as regionais cadastradas.
     * @return Uma lista de todas as regionais na base de dados.
     */
    public List<Regional> listar() {
        Session session = sessionFactory.openSession();
        return new RegionalRepository(session).listar();
    }

    /**
      * Método que lista todas as regionais que remetem a um evento que
      * apresentam a descrição recebida pelo método.
     * @param descricao descrição a ser comparada com a dos eventos cadastrados.
     * @return Uma lista de regionais que remetem a eventos com a descrição
     * recebida.
     */
    public List<Regional> listar(String descricao) {
        Session session = sessionFactory.openSession();
        return new RegionalRepository(session).listarPorDescricao(descricao);
    }

    /**
      * Método que recebe um código de identificação e retorna a regional que
      * apresenta este código como atributo Id.
     * @param id código a ser comparado com os dos eventos cadastrados.
     * @return A regional que apresenta o código de identificação recebido.
     */
    public Regional listarPorId(long id) {
        Session session = sessionFactory.openSession();
        return new RegionalRepository(session).listarPorId(id);
    }

    /**
      * Método que remove todas as categorias do banco de categorias de evento.
     */
    public void limparTabela() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        new RegionalRepository(session).limparTabela();
        transaction.commit();
        session.close();
    }

    /**
      * Método que recebe um códgo de identificação que denota uma regional a
      * ser removida da base de dados.
     * @param codigo código da regional que será removida.
     */
    public void remover(long codigo) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        new RegionalRepository(session).remover(codigo);
        transaction.commit();
        session.close();
    }
}
