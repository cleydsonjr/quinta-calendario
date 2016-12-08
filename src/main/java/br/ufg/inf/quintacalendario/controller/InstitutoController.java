package br.ufg.inf.quintacalendario.controller;

import br.ufg.inf.quintacalendario.main.Application;
import br.ufg.inf.quintacalendario.model.Instituto;
import br.ufg.inf.quintacalendario.service.InstitutoService;
import br.ufg.inf.quintacalendario.view.console.TelaInstitutoConsole;
import org.hibernate.SessionFactory;

import java.util.List;

public class InstitutoController {

    private TelaInstitutoConsole tela;
    private SessionFactory sessionFactory;

    public InstitutoController() {
        setTela(new TelaInstitutoConsole(System.out));
        setSessionFactory(Application.getInstance().getSessionFactory());
    }

    public void exibaOpcoes() {
        getTela().exibaOpcoes();
    }

    public boolean cadastrar(String nome) {
        Instituto instituto = new Instituto();
        instituto.setNome(nome);

        InstitutoService service = new InstitutoService(getSessionFactory());
        return service.salvar(instituto);
    }

    public List<Instituto> listar() {
        InstitutoService service = new InstitutoService(getSessionFactory());
        return service.listar();
    }

    public List<Instituto> listar(String descricao) {
        InstitutoService service = new InstitutoService(getSessionFactory());
        return service.listar(descricao);
    }

    public Instituto listarPorId(Integer codigo) {
        InstitutoService service = new InstitutoService(getSessionFactory());
        Instituto Instituto = service.listarPorId(codigo);
        return Instituto;
    }

    public void editar(Integer codigo, String nome) {
        InstitutoService service = new InstitutoService(getSessionFactory());
        service.editar(codigo, nome);
    }

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
