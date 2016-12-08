package br.ufg.inf.quintacalendario.service;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import br.ufg.inf.quintacalendario.model.Evento;
import br.ufg.inf.quintacalendario.repository.EventoRepository;

/**
 * Classe que contém os métodos que realizam os serviços relacionados aos
 * eventos.
 */
public class EventoService {

    /**
     * Fábrica de dados responsável pela base de dados de institutos.
     */
    private SessionFactory sessionFactory;

    public EventoService(SessionFactory sessionFactory) {
        super();
        this.setSessionFactory(sessionFactory);
    }

    /**
      * Método que recebe um evento a ser salvo na base de dados.
     * @param evento objeto da classe Evento que será salvo na estrutura
     * de persistência.
     * @return O valor lógico do sucesso da operação.
     */
    public boolean salvar(Evento evento) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            validarEvento(evento);

            new EventoRepository(session).salvar(evento);
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

    public void atualizar(Evento evento) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        EventoRepository eventoRepository = new EventoRepository(session);
        eventoRepository.atualizar(evento);
        transaction.commit();
    }

    /**
      * Método que lista todaos os eventos do banco de categorias institutos.
     * @return Uma lista de todos os eventos na base de dados.
     */
    public List<Evento> listar() {
        Session session = sessionFactory.openSession();
        return new EventoRepository(session).listar();
    }

    /**
      * Método que lista todos eventos que apresentam uma descrição recebida.
     * @param descricao descrição a ser comparada com a dos eventos cadastrados.
     * @return Uma lista de eventos que apresentam a descrição recebida.
     */
    public List<Evento> listarPorDescricao(String descricao) {
        Session session = sessionFactory.openSession();
        List<Evento> eventos = new EventoRepository(session).
                listarPorDescricao(descricao);
        return eventos;
    }

    /**
      * Método que encontra um evento pelo seu código de identificação.
     * @param id código que será comparado ao dos eventos cadastrados.
     * @return O evento que apresenta o código recebido, se houver.
     */
    public Evento listarPorId(long id) {
        Session session = sessionFactory.openSession();
        return new EventoRepository(session).listarPorId(id);
    }

    /**
      * Método que lista todos eventos que apresentam uma categoria recebida.
     * @param idCategoria id de categoria a ser comparado com o dos eventos
     * cadastrados.
     * @return Uma lista de eventos que apresentam a categoria recebida.
     */
    public List<Evento> listarPorCategoria(long idCategoria) {
        Session session = sessionFactory.openSession();
        return new EventoRepository(session).listarPorCategoria(idCategoria);
    }

    /**
      * Método que lista todos eventos que apresentam uma descrição recebida.
     * @param idInstituto id de instituto a ser comparado com o dos eventos
     * cadastrados.
     * @return Uma lista de eventos que apresentam o instituto recebido.
     */
    public List<Evento> listarPorInstituto(long idInstituto) {
        Session session = sessionFactory.openSession();
        return new EventoRepository(session).listarPorInstituto(idInstituto);
    }

    /**
      * Método que lista todos eventos que apresentam uma categoria recebida.
     * @param idRegional id de categoria a ser comparado com o dos eventos
     * cadastrados.
     * @return Uma lista de eventos que apresentam a categoria recebida.
     */
    public List<Evento> listarPorRegional(long idRegional) {
        Session session = sessionFactory.openSession();
        return new EventoRepository(session).listarPorRegional(idRegional);
    }

    /**
      * Método que lista eventos que começam e terminam dentro do intervalo de
      * tempo recebido.
     * @param dataInicial primeira data do intervalo de tempo recebido.
     * @param dataFinal última data do intervalo de tempo recebido.
     * @return Uma lista de eventos cadastrados que ocorrem dentro do intervalo
     * de tempo recebido.
     */
    public List<Evento> listarEventosPorPeriodo(Date dataInicial,
            Date dataFinal) {
        Session session = sessionFactory.openSession();
        return new EventoRepository(session).listarPorPeriodo(dataInicial,
                dataFinal);
    }

    /**
      * Método que lista os eventos que se iniciam na data de entrada.
     * @param dataInicial data a ser comparada com a data de início dos eventos
     * cadastrados.
     * @return Uma lista de eventos que se iniciam na data de entrada.
     */
    public List<Evento> listarEventosPorData(Date dataInicial) {
        Session session = sessionFactory.openSession();
        return new EventoRepository(session).listarPorData(dataInicial);

    }

    /**
      * Método que remove todos os institutos do banco de eventos.
     */
    public void limparTabela() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        new EventoRepository(session).limparTabela();
        transaction.commit();
        session.close();
    }

    /**
      * Método que torna nula a categoria de um evento entrado.
     * @param evento evento que terá a categoria tornada nula.
     */
    public void removerCategoriasEvento(Evento evento) {
        evento.setCategoria(null);
    }

    /**
      * Método que torna nulo o instituto de um evento entrado.
     * @param evento evento que terá o instituto tornado nulo.
     */
    public void removerInstitutoEvento(Evento evento) {
        evento.getInstitutos().clear();
    }

    /**
      * Método que torna nula a regional de um evento entrado.
     * @param evento evento que terá a regional tornada nula.
     */
    public void removerRegionalEvento(Evento evento) {
        evento.getRegionais().clear();
    }

    /**
      * Método que verifica a validade de um evento.
     * @param categoria objeto da classe Evento que será validado.
     */
    private void validarEvento(Evento evento) {
        // TODO Criar validacoes de evento
    }

    /**
      * Método que torna nulos todos os atributos de um objeto Evento
     * @param evento evento que terá seus atributos tornados nulos.
     */
    public void limparObjeto(Evento evento) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        removerCategoriasEvento(evento);
        removerInstitutoEvento(evento);
        removerRegionalEvento(evento);

        new EventoRepository(session).atualizar(evento);
        transaction.commit();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
