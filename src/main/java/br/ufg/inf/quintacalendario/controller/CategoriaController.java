package br.ufg.inf.quintacalendario.controller;

import java.util.List;

import org.hibernate.SessionFactory;

import br.ufg.inf.quintacalendario.main.Application;
import br.ufg.inf.quintacalendario.model.Categoria;
import br.ufg.inf.quintacalendario.service.CategoriaService;
import br.ufg.inf.quintacalendario.view.console.TelaCategoriaConsole;

/**
 * Classe que contém métodos que controlam o funcionamento das categorias de
 * evento no programa.
 */
public class CategoriaController {

    /**
     * Tela que apresentará as opções disponíveis para o controle das
     * categorias de evento.
     */
    private TelaCategoriaConsole tela;

    /**
     * Fábrica de dados responsável pela base de dados das categorias de evento.
     */
    private SessionFactory sessionFactory;

    public CategoriaController() {
        tela = new TelaCategoriaConsole(System.out);
        sessionFactory = Application.getInstance().getSessionFactory();
    }

    /**
     * Método que exibe as opções do usuário na tela.
     */
    public void exibaOpcoes() {
        getTela().exibaOpcoes();
    }

    /**
     * Método que permite o cadastro de novas categorias no abnco de dados.
     *
     * @param nome nome da categoria de evento cadastrada.
     * @return o valor lógico do sucesso da operação.
     */
    public boolean cadastrar(String nome) {
        Categoria Categoria = new Categoria();
        Categoria.setNome(nome);

        CategoriaService service = new CategoriaService(getSessionFactory());
        return service.salvar(Categoria);
    }

   /**
     * Método que realiza um consulta no banco de dados por uma categoria de
     * evento pelo nome.
     * @return A listagem de todoas as categorias de evento encontrados na
     * consulta.
     */
    public List<Categoria> listar() {
        CategoriaService service = new CategoriaService(getSessionFactory());
        return service.listar();
    }

    /**
      * Método que recebe uma descrição de evento e retorna as categorias dos
      * eventos com a descrição inserida, se houver.
     * @param descricao entrada do usuário utilizada para a busca de descrições
     * de eventos.
     * @return Uma lista de categorias cujos eventos apresentam a descrição
     * inserida.
     */
    public List<Categoria> listar(String descricao) {
        CategoriaService service = new CategoriaService(getSessionFactory());
        return service.listar(descricao);
    }

    /**
      * Método que recebe um código inteiro e retorna uma categoria de evento
      * que apresenta o codigo de identificação inserido, se houver.
     * @param codigo codigo inserido para comparação com os codigos de
     * identificação das categorias.
     * @return A categoria com o código de identificação inserido, se houver.
     */
    public Categoria listarPorId(Integer codigo) {
        CategoriaService service = new CategoriaService(getSessionFactory());
        Categoria Categoria = service.listarPorId(codigo);
        return Categoria;
    }

    /**
      * Método que recebe um código de identificação e um nome. O código de
      * identificação denota a categoria que passará a ter o atributo nome
      * igual ao nome inserido.
     * @param codigo codigo de identificação da categoria de evento que terá o
     * nome editado.
     * @param nome nome que será atribuído à categoria de evento que apresenta o
     * ID inserido.
     */
    public void editar(Integer codigo, String nome) {
        CategoriaService service = new CategoriaService(getSessionFactory());
        service.editar(codigo, nome);
    }

    /**
      * Método que recebe um códgo de identificação que denota uma categoria a
      * ser removida da base de dados.
     * @param codigo código da categoria que será removida.
     */
    public void remover(Integer codigo) {
        CategoriaService service = new CategoriaService(getSessionFactory());
        Categoria Categoria = service.listarPorId(codigo);
        if (Categoria == null) {
            System.out.println("*******Codigo invalido*******");
            System.out.println("");
            getTela().remover();
        } else {
            service.remover(codigo);
        }
    }

    public TelaCategoriaConsole getTela() {
        return tela;
    }

    public void setTela(TelaCategoriaConsole tela) {
        this.tela = tela;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
