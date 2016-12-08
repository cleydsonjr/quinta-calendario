package br.ufg.inf.quintacalendario.controller;

import java.util.List;

import org.hibernate.SessionFactory;

import br.ufg.inf.quintacalendario.main.Application;
import br.ufg.inf.quintacalendario.model.Instituto;
import br.ufg.inf.quintacalendario.service.InstitutoService;
import br.ufg.inf.quintacalendario.view.console.TelaInstitutoConsole;

/**
 * Classe que contém métodos que controlam o funcionamento dos institutos no
 * programa.
 */
public class InstitutoController {

    /**
     * Tela que apresentará as opções disponíveis para o controle dos
     * institutos.
     */
    private TelaInstitutoConsole tela;

    /**
     * Fábrica de dados responsável pela base de dados dos institutos.
     */
    private SessionFactory sessionFactory;

    
    public InstitutoController() {
        setTela(new TelaInstitutoConsole(System.out));
        setSessionFactory(Application.getInstance().getSessionFactory());
    }

    /**
     * Método que exibe as opções do usuário na tela.
     */
    public void exibaOpcoes() {
        getTela().exibaOpcoes();
    }

    /**
      * Método que permite o cadastro de novos institutos no abnco de dados.
     *
     * @param nome nome do instituto cadastrado.
     * @return o valor lógico do sucesso da operação.
     */
    public boolean cadastrar(String nome) {
        Instituto instituto = new Instituto();
        instituto.setNome(nome);

        InstitutoService service = new InstitutoService(getSessionFactory());
        return service.salvar(instituto);
    }

    /**
     * Método que realiza um consulta no banco de dados por um instituto pelo
     * nome.
     *
     * @return A listagem de todos os institutos encontrados na consulta.
     */
    public List<Instituto> listar() {
        InstitutoService service = new InstitutoService(getSessionFactory());
        return service.listar();
    }

    /**
      * Método que recebe uma descrição de evento e retorna os institutos dos
      * eventos com a descrição inserida, se houver.
     * @param descricao entrada do usuário utilizada para a busca de descrições
     * de eventos.
     * @return Uma lista de institutos cujos eventos apresentam a descrição
     * inserida.
     */
    public List<Instituto> listar(String descricao) {
        InstitutoService service = new InstitutoService(getSessionFactory());
        return service.listar(descricao);
    }

    /**
      * Método que recebe um código inteiro e retorna um instituto que apresenta
      * o código de identificação inserido, se houver.
     * @param codigo codigo inserido para comparação com os codigos de
     * identificação dos institutos.
     * @return O instituto com o código de identificação inserido, se houver.
     */
    public Instituto listarPorId(Integer codigo) {
        InstitutoService service = new InstitutoService(getSessionFactory());
        Instituto Instituto = service.listarPorId(codigo);
        return Instituto;
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
        InstitutoService service = new InstitutoService(getSessionFactory());
        service.editar(codigo, nome);
    }

    /**
      * Método que recebe um códgo de identificação que denota um instituto a
      * ser removido da base de dados.
     * @param codigo código do instituto que será removido.
     */
    public void remover(Integer codigo) {
        InstitutoService service = new InstitutoService(getSessionFactory());
        Instituto Instituto = service.listarPorId(codigo);
        if (Instituto == null) {
            System.out.println("*******Codigo invalido*******");
            System.out.println("");
            getTela().remover();
        } else {
            service.remover(codigo);
        }
    }

    public TelaInstitutoConsole getTela() {
        return tela;
    }

    public void setTela(TelaInstitutoConsole tela) {
        this.tela = tela;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
