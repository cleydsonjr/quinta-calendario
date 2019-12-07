package br.ufg.inf.quintacalendario.service;

import br.ufg.inf.quintacalendario.main.Application;
import br.ufg.inf.quintacalendario.model.Event;
import br.ufg.inf.quintacalendario.model.Institute;
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

public class TesteInstitute {

    private SessionFactory sessionFactory;

    @Before
    public void init() {

        sessionFactory = Application.getInstance().getSessionFactory();

        limparObjetoEvento();
        new EventService(sessionFactory).limparTabela();
        new RegionalService(sessionFactory).limparTabela();
        new CategoryService(sessionFactory).limparTabela();
        new InstitutoService(sessionFactory).limparTabela();
    }

    @After
    public void finalizar() {
        new InstitutoService(sessionFactory).limparTabela();
    }

    @Test
    public void testeSalvarInstituto() {
        Institute institute = new Institute();
        institute.setName("Feriado");
        boolean retorno = new InstitutoService(sessionFactory).salvar(institute);
        Assert.assertTrue(retorno);
    }

    @Test
    public void testeSalvarInstitutoComNomeVazio() {
        Institute institute = new Institute();
        institute.setName("");
        boolean retorno = new InstitutoService(sessionFactory).salvar(institute);
        Assert.assertFalse(retorno);
    }

    public void limparObjetoEvento() {
        EventService eventService = new EventService(sessionFactory);
        List<Event> events = eventService.listRecords();

        events.stream().forEach(x -> eventService.limparObjeto(x));
    }
}
