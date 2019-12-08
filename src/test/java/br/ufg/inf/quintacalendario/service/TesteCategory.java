package br.ufg.inf.quintacalendario.service;

import br.ufg.inf.quintacalendario.main.Application;
import br.ufg.inf.quintacalendario.model.Category;
import br.ufg.inf.quintacalendario.model.Event;
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
        new RegionalService(sessionFactory).truncateTable();
        new EventService(sessionFactory).truncateTable();
        new InstituteService(sessionFactory).truncateTable();
        new CategoryService(sessionFactory).truncateTable();
    }

    @After
    public void finalizar() {
        new CategoryService(sessionFactory).truncateTable();
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

        events.forEach(eventService::clearObject);
    }
}
