package br.ufg.inf.quintacalendario.controller;

import br.ufg.inf.quintacalendario.main.Application;
import br.ufg.inf.quintacalendario.model.Regional;
import br.ufg.inf.quintacalendario.service.RegionalService;
import br.ufg.inf.quintacalendario.view.console.TelaRegionalConsole;
import org.hibernate.SessionFactory;

import java.util.List;

public class RegionalController {

    private TelaRegionalConsole tela;
    private SessionFactory sessionFactory;

    public RegionalController() {
        tela = new TelaRegionalConsole(System.out);
        sessionFactory = Application.getInstance().getSessionFactory();
    }

    public void exibaOpcoes() {
        getTela().exibaOpcoes();
    }

    public boolean cadastrar(String nome) {
        Regional regional = new Regional();
        regional.setNome(nome);

        RegionalService service = new RegionalService(getSessionFactory());
        return service.salvar(regional);
    }

    public List<Regional> listar() {
        RegionalService service = new RegionalService(getSessionFactory());
        return service.listar();
    }

    public List<Regional> listar(String descricao) {
        RegionalService service = new RegionalService(getSessionFactory());
        return service.listar(descricao);
    }

    public Regional listarPorId(Integer codigo) {
        RegionalService service = new RegionalService(getSessionFactory());
        Regional regional = service.listarPorId(codigo);
        return regional;
    }

    public void editar(Integer codigo, String nome) {
        RegionalService service = new RegionalService(getSessionFactory());
        service.editar(codigo, nome);
    }

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
