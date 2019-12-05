package br.ufg.inf.quintacalendario.testes.service;

import br.ufg.inf.quintacalendario.main.Application;
import br.ufg.inf.quintacalendario.model.Category;
import br.ufg.inf.quintacalendario.model.Event;
import br.ufg.inf.quintacalendario.service.CategoryService;
import br.ufg.inf.quintacalendario.service.EventService;
import br.ufg.inf.quintacalendario.service.InstitutoService;
import br.ufg.inf.quintacalendario.service.RegionalService;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TesteCategory {

    SessionFactory sessionFactory;

    @Before
    public void init() {
        sessionFactory = Application.getInstance().getSessionFactory();

        limparObjetoEvento();
        new RegionalService(sessionFactory).limparTabela();
        new EventService(sessionFactory).limparTabela();
        new InstitutoService(sessionFactory).limparTabela();
        new CategoryService(sessionFactory).limparTabela();
    }

    @After
    public void finalizar() {
        new CategoryService(sessionFactory).limparTabela();
    }

    @Test
    public void testeSalvarCategoria() {
        Category category = new Category();
        category.setName("Feriado");
        boolean retorno = new CategoryService(sessionFactory).save(category);
        Assert.assertTrue(retorno);
    }

    @Test
    public void testeSalvarCategoriaComNomeVazio() {
        Category category = new Category();
        category.setName("");
        boolean retorno = new CategoryService(sessionFactory).save(category);
        Assert.assertFalse(retorno);
    }

    public void limparObjetoEvento() {
        EventService eventService = new EventService(sessionFactory);
        List<Event> events = eventService.listRecords();

        events.forEach(eventService::limparObjeto);
    }
}
