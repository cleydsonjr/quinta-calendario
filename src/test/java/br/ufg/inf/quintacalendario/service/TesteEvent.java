package br.ufg.inf.quintacalendario.service;

import br.ufg.inf.quintacalendario.main.Application;
import br.ufg.inf.quintacalendario.model.Category;
import br.ufg.inf.quintacalendario.model.Event;
import br.ufg.inf.quintacalendario.model.Institute;
import br.ufg.inf.quintacalendario.model.Regional;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class TesteEvent {

    private SessionFactory sessionFactory;

    @Before
    public void init() {
        sessionFactory = Application.getInstance().getSessionFactory();

        limparObjeto();
        new EventService(sessionFactory).truncateTable();
        new InstituteService(sessionFactory).truncateTable();
        new RegionalService(sessionFactory).limparTabela();
        new CategoryService(sessionFactory).truncateTable();
    }

    @After
    public void finalizar() {
        limparObjeto();

        EventService eventService = new EventService(sessionFactory);
        eventService.truncateTable();
    }

    @Test
    public void testeSalvarEvento() {

        Event event = criarEvento();

        EventService eventService = new EventService(sessionFactory);
        boolean retorno = eventService.save(event);
        Assert.assertTrue(retorno);
    }

    @Test
    public void testeAlterarEvento() {
        long idEvento = 0;
        EventService eventService = new EventService(sessionFactory);
        Event event = null;
        boolean eventoFoiAtualizado = false;
        eventService.save(criarEvento());

        List<Event> events = eventService.listRecords();

        if (!events.isEmpty()) {
            event = events.get(0);
            idEvento = event.getId();
            event.setDescription("Novo Evento");
            eventService.edit(event);
        }

        if (idEvento > 0) {
            event = eventService.listById(idEvento);
        } else {
            Assert.assertTrue(eventoFoiAtualizado);
        }

        Assert.assertTrue(event.getDescricao().equals("Novo Evento"));
    }

    @Test
    public void testeListarPorCategoria() {
        boolean eventosListados = false;

        EventService eventService = new EventService(sessionFactory);
        eventService.save(criarEvento());

        Category category = null;
        CategoryService categoryService = new CategoryService(sessionFactory);
        List<Category> categories = categoryService.listRecords();


        if (!categories.isEmpty()) {
            category = categories.get(0);
        } else {
            Assert.assertTrue(eventosListados);
        }


        if (!(category == null)) {
            List<Event> events = eventService.listByCategory(category.getId());
            Assert.assertTrue(!events.isEmpty());
        } else {
            Assert.assertTrue(eventosListados);
        }
    }

    @Test
    public void testeListarPorInstituto() {
        boolean eventosListados = false;

        EventService eventService = new EventService(sessionFactory);
        eventService.save(criarEvento());

        Institute institute = null;
        InstituteService instituteService = new InstituteService(sessionFactory);
        List<Institute> institutes = instituteService.listRecords();


        if (!institutes.isEmpty()) {
            institute = institutes.get(0);
        } else {
            Assert.assertTrue(eventosListados);
        }

        if (!(institute == null)) {
            List<Event> events = eventService.listByInstitute(institute.getId());
            Assert.assertTrue(!events.isEmpty());
        } else {
            Assert.assertTrue(eventosListados);
        }
    }

    @Test
    public void testeListarPorRegional() {
        boolean eventosListados = false;

        EventService eventService = new EventService(sessionFactory);
        eventService.save(criarEvento());

        Regional regional = null;
        RegionalService regionalService = new RegionalService(sessionFactory);

        List<Regional> regionais = regionalService.listRecords();


        if (!regionais.isEmpty()) {
            regional = regionais.get(0);
        } else {
            Assert.assertTrue(eventosListados);
        }

        if (!(regional == null)) {
            List<Event> events = eventService.listByRegional(regional.getId());
            Assert.assertTrue(!events.isEmpty());
        } else {
            Assert.assertTrue(eventosListados);
        }
    }

    @SuppressWarnings("deprecation")
    @Test
    public void testeListarPorPeriodo() {
        EventService eventService = new EventService(sessionFactory);

        Event event = criarEvento();
        event.setInitialDate(new Date(2016, 11, 24));
        event.setFinalDate(new Date(2016, 12, 24));

        eventService.save(event);

        event = criarEvento();

        event.setInitialDate(new Date(2015, 11, 24));
        event.setFinalDate(new Date(2015, 12, 24));

        eventService.save(event);


        List<Event> events = eventService.listByPeriod(new Date(2016, 11, 24), new Date(2016, 12, 24));

        Assert.assertTrue(events.size() == 1);
    }

    @SuppressWarnings("deprecation")
    @Test
    public void testeListarPorData() {
        EventService eventService = new EventService(sessionFactory);

        Event event = criarEvento();
        event.setInitialDate(new Date(2016, 11, 24));
        eventService.save(event);

        event = criarEvento();
        event.setInitialDate(new Date(2015, 11, 24));
        eventService.save(event);

        List<Event> events = eventService.listByDate(new Date(2016, 11, 24));

        Assert.assertTrue(events.size() == 1);

    }

    public void limparObjeto() {
        EventService eventService = new EventService(sessionFactory);
        List<Event> events = eventService.listRecords();

        events.stream().forEach(x -> eventService.clearObject(x));
    }

    public Event criarEvento() {
        inserirCategoria();

        Event event = new Event();

        event.setInitialDate(new Date());
        event.setFinalDate(new Date());
        event.setDescription("Nascimento do menino jesus");
        event.setTitle("Vespera de Natal");
        event.setCategory(pesquisarCategoriaPorDescricao());

        List<Regional> regionais = consultarRegionais();
        if (regionais.isEmpty()) {
            inserirRegional();
            regionais = consultarRegionais();
        }

        event.setRegionais(regionais);

        List<Institute> institutes = consultarInstitutos();
        if (institutes.isEmpty()) {
            inserirInstituto();
            institutes = consultarInstitutos();
        }

        event.setInstitutes(institutes);

        return event;
    }

    public void inserirCategoria() {
        Category category = new Category();
        category.setName("Feriado");
        CategoryService service = new CategoryService(sessionFactory);
        service.save(category);
    }

    public Category pesquisarCategoriaPorDescricao() {
        CategoryService categoryService = new CategoryService(sessionFactory);
        List<Category> categories = categoryService.searchByDescription("Feriado");
        if (!categories.isEmpty()) {
            return categories.get(0);
        }
        return new Category();
    }

    public void inserirRegional() {
        Regional regional = new Regional();
        regional.setName("Goiania");
        RegionalService regionalService = new RegionalService(sessionFactory);
        regionalService.save(regional);
    }

    public List<Regional> consultarRegionais() {
        RegionalService regionalService = new RegionalService(sessionFactory);
        List<Regional> regionais = regionalService.listRecords();

        return regionais;
    }

    public void inserirInstituto() {
        Institute institute = new Institute();
        institute.setName("INF - Instituto de Informatica");

        InstituteService instituteService = new InstituteService(sessionFactory);
        instituteService.salvar(institute);
    }

    public List<Institute> consultarInstitutos() {
        InstituteService instituteService = new InstituteService(sessionFactory);
        List<Institute> institutes = instituteService.listRecords();

        return institutes;
    }
}
