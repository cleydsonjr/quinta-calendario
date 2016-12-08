package br.ufg.inf.quintacalendario.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import br.ufg.inf.quintacalendario.main.Application;
import br.ufg.inf.quintacalendario.model.Categoria;
import br.ufg.inf.quintacalendario.model.Evento;
import br.ufg.inf.quintacalendario.model.Instituto;
import br.ufg.inf.quintacalendario.model.Regional;
import br.ufg.inf.quintacalendario.service.CategoriaService;
import br.ufg.inf.quintacalendario.service.EventoService;
import br.ufg.inf.quintacalendario.service.InstitutoService;
import br.ufg.inf.quintacalendario.service.RegionalService;
import br.ufg.inf.quintacalendario.view.console.TelaEventoConsole;

/**
  * Classe que contém métodos que controlam o funcionamento dos institutos no
  * programa.
 */
public class EventosController {

    /**
      * Tela que apresentará as opções disponíveis para o controle dos
      * institutos.
     */
    private TelaEventoConsole tela;
    /**
      * Fábrica de dados responsável pela base de dados dos institutos.
     */
    private SessionFactory sessionFactory;

    public EventosController() {
        setTela(new TelaEventoConsole(System.out));
        setSessionFactory(Application.getInstance().getSessionFactory());
    }

    /**
      * Método que recebe os atributos de um evento para que o evento seja
      * cadastrado na estruttura de persistência.
     * @param descricao descrição do evento a ser cadastrado.
     * @param titulo nome do evento cadastrado.
     * @param dataInicial data de inicio do evento cadastrado.
     * @param dataFinal data de fim do evento cadastrado.
     * @param codigoCategoria id da categoria do evento cadastrado.
     * @param codigoRegional id da regional do evento cadastrado.
     * @param codigoInstituto id do instituto do evento cadastrado.
     * @return valor lógico do sucesso da operação
     */
    public boolean cadastrar(String descricao, String titulo,
            String dataInicial, String dataFinal, Integer codigoCategoria,
            Integer codigoRegional, Integer codigoInstituto) {

        try {
            Evento evento = new Evento();

            evento.setDescricao(descricao);
            evento.setTitulo(titulo);

            Date data = converterStringParaDate(dataInicial);
            evento.setDataInicial(data);

            data = converterStringParaDate(dataFinal);
            evento.setDataFinal(data);

            evento.setCategoria(new CategoriaService(getSessionFactory()).listarPorId(codigoCategoria));

            List<Instituto> institutos = new ArrayList<Instituto>();
            institutos.add(new InstitutoService(getSessionFactory()).listarPorId(codigoInstituto));

            evento.setInstitutos(institutos);

            List<Regional> regionais = new ArrayList<Regional>();
            regionais.add(new RegionalService(getSessionFactory()).listarPorId(codigoRegional));

            EventoService service = new EventoService(getSessionFactory());
            service.salvar(evento);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Método que exibe as opções do usuário na tela.
     */
    public void exibaOpcoes() {
        getTela().exibaOpcoes();
    }

    /**
      * Método que recebe uma string e a converte para um objeto da classe Date.
     * @param pData String, representando uma data no formato "dd/mm/yyyy", que
     * será convertida para Date.
     * @return Um objeto da classe Date que representa a mesma data que a
     * String no formato "dd/mm/yyyy".
     */
    public Date converterStringParaDate(String pData) {
        if (pData == null || pData.equals("")) {
            return null;
        }

        Date date = null;
        try {
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            date = (java.util.Date) formatter.parse(pData);
        } catch (ParseException e) {
            // TODO: handle exception
        }
        return date;
    }

    /**
      * Método que retorna uma lista de todos os eventos na base de dados.
     * @return Uma lista de todos os eventos cadastrados.
     */
    public List<Evento> listar() {
        EventoService service = new EventoService(getSessionFactory());
        return service.listar();
    }

    /**
      * Método que recebe uma descrição de evento e retorna eventos que
      * apresentam descrição inserida, se houver.
     * @param descricao entrada do usuário utilizada para comparação com 
     * descrições de eventos.
     * @return Uma lista de eventos cujas descrições apresentam a descrição
     * inserida.
     */
    public List<Evento> listar(String descricao) {
        EventoService service = new EventoService(getSessionFactory());
        return service.listarPorDescricao(descricao);
    }

    /**
      * Método que recebe um código inteiro e retorna um evento que apresenta
      * o código de identificação inserido, se houver.
     * @param codigo codigo inserido para comparação com os codigos de
     * identificação dos eventos.
     * @return O evento com o código de identificação inserido, se houver.
     */
    public Evento listarPorId(Integer codigo) {
        EventoService service = new EventoService(getSessionFactory());
        return service.listarPorId(codigo);
    }

    /**
      * Método que lista eventos que começam e terminam dentro do intervalo de
      * tempo recebido.
     * @param dataInicial primeira data do intervalo de tempo recebido.
     * @param dataFinal última data do intervalo de tempo recebido.
     * @return Uma lista de eventos cadastrados que ocorrem dentro do intervalo
     * de tempo recebido.
     */
    public List<Evento> listarPorPeriodo(String dataInicial, String dataFinal) {
        EventoService service = new EventoService(getSessionFactory());
        return service.
                listarEventosPorPeriodo(converterStringParaDate(dataInicial),
                        converterStringParaDate(dataFinal));
    }

    /**
     * Método que realiza um consulta no banco de dados por uma regional pelo
     * nome.
     * @return A listagem de todoas as regionais encontrados na consulta.
     */
    public List<Regional> listarRegionais() {
        RegionalService service = new RegionalService(getSessionFactory());
        return service.listar();
    }

    /**
     * Método que lista todos os institutos cadastrados.
     * @return A listagem de todos os institutos na base de dados.
     */
    public List<Instituto> listarInstitutos() {
        InstitutoService service = new InstitutoService(getSessionFactory());
        return service.listar();
    }

    /**
     * Método lista todas as categorias de evento cadastradas.
     * @return A listagem de todoas as categorias de evento na base de dados.
     */
    public List<Categoria> listarCategorias() {
        CategoriaService service = new CategoriaService(getSessionFactory());
        return service.listar();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public TelaEventoConsole getTela() {
        return tela;
    }

    public void setTela(TelaEventoConsole tela) {
        this.tela = tela;
    }
}
