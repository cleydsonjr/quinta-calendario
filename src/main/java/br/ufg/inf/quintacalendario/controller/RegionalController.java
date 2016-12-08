package br.ufg.inf.quintacalendario.controller;

import java.util.List;

import org.hibernate.SessionFactory;

import br.ufg.inf.quintacalendario.main.Application;
import br.ufg.inf.quintacalendario.model.Regional;
import br.ufg.inf.quintacalendario.service.RegionalService;
import br.ufg.inf.quintacalendario.view.console.TelaRegionalConsole;

/**
 * Classe que contém métodos que controlam o funcionamento das categorias de
 * evento no programa.
 */
public class RegionalController {

    /**
     * Tela que apresentará as opções disponíveis para o controle das regionais
     */
    private TelaRegionalConsole tela;

    /**
     * Fábrica de dados responsável pela base de dados das categorias de evento.
     */
    private SessionFactory sessionFactory;

    public RegionalController() {
        tela = new TelaRegionalConsole(System.out);
        sessionFactory = Application.getInstance().getSessionFactory();
    }

    /**
     * Método que exibe as opções do usuário na tela.
     */
    public void exibaOpcoes() {
        getTela().exibaOpcoes();
    }

    /**
     * Método que permite o cadastro de novas regionais no banco de dados.
     *
     * @param nome nome da regional cadastrada.
     * @return o valor lógico do sucesso da operação.
     */
    public boolean cadastrar(String nome) {
        Regional regional = new Regional();
        regional.setNome(nome);

        RegionalService service = new RegionalService(getSessionFactory());
        return service.salvar(regional);
    }

    /**
     * Método que realiza um consulta no banco de dados por uma regional pelo
     * nome.
     * @return A listagem de todoas as regionais encontrados na consulta.
     */
    public List<Regional> listar() {
        RegionalService service = new RegionalService(getSessionFactory());
        return service.listar();
    }

    /**
      * Método que recebe uma descrição de evento e retorna as regionais com a
      * descrição inserida, se houver.
     * @param descricao entrada do usuário utilizada para a busca de descrições
     * de eventos.
     * @return Uma lista de regionais apresentam a descrição inserida.
     */
    public List<Regional> listar(String descricao) {
        RegionalService service = new RegionalService(getSessionFactory());
        return service.listar(descricao);
    }

    /**
      * Método que recebe um código inteiro e retorna uma regional que apresenta
      * o codigo de identificação inserido, se houver.
     * @param codigo codigo inserido para comparação com os codigos de
     * identificação das categorias.
     * @return A categoria com o código de identificação inserido, se houver.
     */
    public Regional listarPorId(Integer codigo) {
        RegionalService service = new RegionalService(getSessionFactory());
        Regional regional = service.listarPorId(codigo);
        return regional;
    }

    /**
      * Método que recebe um código de identificação e um nome. O código de
      * identificação denota a regional que passará a ter o atributo nome igual
      * ao nome inserido.
     * @param codigo codigo de identificação da regional que terá o nome
     * editado.
     * @param nome nome que será atribuído à regional que apresenta o ID
     * inserido.
     */
    public void editar(Integer codigo, String nome) {
        RegionalService service = new RegionalService(getSessionFactory());
        service.editar(codigo, nome);
    }

    /**
      * Método que recebe um códgo de identificação que denota uma regional a
      * ser removida da base de dados.
     * @param codigo código da categoria que será removida.
     */
    public void remover(Integer codigo) {
        RegionalService service = new RegionalService(getSessionFactory());
        Regional regional = service.listarPorId(codigo);
        if (regional == null) {
            System.out.println("*******Codigo invalido*******");
            System.out.println("");
            getTela().remover();
        } else {
            service.remover(codigo);
        }
    }

    public TelaRegionalConsole getTela() {
        return tela;
    }

    public void setTela(TelaRegionalConsole tela) {
        this.tela = tela;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
