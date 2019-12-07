package br.ufg.inf.quintacalendario.testes.service;

import br.ufg.inf.quintacalendario.main.Application;
import br.ufg.inf.quintacalendario.model.Event;
import br.ufg.inf.quintacalendario.model.Regional;
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

public class TesteRegional {

    private SessionFactory sessionFactory;

    @Before
    public void init() {
        sessionFactory = Application.getInstance().getSessionFactory();

        limparObjetoEvento();

        new EventService(sessionFactory).limparTabela();
        new InstitutoService(sessionFactory).limparTabela();
        new CategoryService(sessionFactory).limparTabela();
        new RegionalService(sessionFactory).limparTabela();
    }

    @After
    public void finalizar() {
        new RegionalService(sessionFactory).limparTabela();
    }

    @Test
    public void testeSalvarRegional() {
        Regional regional = criarRegional();

        boolean retorno = new RegionalService(sessionFactory).save(regional);
        Assert.assertTrue(retorno);
    }

    @Test
    public void testeRegionalComNomeVazio() {
        Regional regional = new Regional();
        regional.setName("");

        boolean retorno = new RegionalService(sessionFactory).save(regional);
        Assert.assertFalse(retorno);
    }

    @Test
    public void testeListarRegionaisPorDescricao() {
        RegionalService service = new RegionalService(sessionFactory);

        service.save(criarRegional());
        List<Regional> regionais = service.listRecordsByDescription("Goiania");

        Assert.assertTrue(!regionais.isEmpty());
    }

    @Test
    public void testeAlterarRegional() {
        RegionalService service = new RegionalService(sessionFactory);
        long id = 0;
        service.save(criarRegional());

        List<Regional> regionais = service.listRecords();

        if (!regionais.isEmpty()) {
            Regional regional = regionais.get(0);
            id = regional.getId();
            service.edit(id, "Catalão");
        }

        Regional regional = service.listById(id);
        Assert.assertTrue(regional.getNome().equals("Catalão"));
    }

    public Regional criarRegional() {
        Regional regional = new Regional();
        regional.setName("Goiania");
        return regional;
    }

    public void limparObjetoEvento() {
        EventService eventService = new EventService(sessionFactory);
        List<Event> events = eventService.listRecords();

        events.stream().forEach(x -> eventService.limparObjeto(x));
    }
}
