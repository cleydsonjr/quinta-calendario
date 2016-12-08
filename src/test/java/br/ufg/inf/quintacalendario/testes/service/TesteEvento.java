package br.ufg.inf.quintacalendario.testes.service;

import br.ufg.inf.quintacalendario.main.Application;
import br.ufg.inf.quintacalendario.model.Categoria;
import br.ufg.inf.quintacalendario.model.Evento;
import br.ufg.inf.quintacalendario.model.Instituto;
import br.ufg.inf.quintacalendario.model.Regional;
import br.ufg.inf.quintacalendario.service.CategoriaService;
import br.ufg.inf.quintacalendario.service.EventoService;
import br.ufg.inf.quintacalendario.service.InstitutoService;
import br.ufg.inf.quintacalendario.service.RegionalService;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class TesteEvento {

    private SessionFactory sessionFactory;

    @Before
    public void init() {
        sessionFactory = Application.getInstance().getSessionFactory();

        limparObjeto();
        new EventoService(sessionFactory).limparTabela();
        new InstitutoService(sessionFactory).limparTabela();
        new RegionalService(sessionFactory).limparTabela();
        new CategoriaService(sessionFactory).limparTabela();
    }

    @After
    public void finalizar() {
        limparObjeto();

        EventoService eventoService = new EventoService(sessionFactory);
        eventoService.limparTabela();
    }

    @Test
    public void testeSalvarEvento() {

        Evento evento = criarEvento();

        EventoService eventoService = new EventoService(sessionFactory);
        boolean retorno = eventoService.salvar(evento);
        Assert.assertTrue(retorno);
    }

    @Test
    public void testeAlterarEvento() {
        long idEvento = 0;
        EventoService eventoService = new EventoService(sessionFactory);
        Evento evento = null;
        boolean eventoFoiAtualizado = false;
        eventoService.salvar(criarEvento());

        List<Evento> eventos = eventoService.listar();

        if (!eventos.isEmpty()) {
            evento = eventos.get(0);
            idEvento = evento.getId();
            evento.setDescricao("Novo Evento");
            eventoService.atualizar(evento);
        }

        if (idEvento > 0) {
            evento = eventoService.listarPorId(idEvento);
        } else {
            Assert.assertTrue(eventoFoiAtualizado);
        }

        Assert.assertTrue(evento.getDescricao().equals("Novo Evento"));
    }

    @Test
    public void testeListarPorCategoria() {
        boolean eventosListados = false;

        EventoService eventoService = new EventoService(sessionFactory);
        eventoService.salvar(criarEvento());

        Categoria categoria = null;
        CategoriaService categoriaService = new CategoriaService(sessionFactory);
        List<Categoria> categorias = categoriaService.listar();


        if (!categorias.isEmpty()) {
            categoria = categorias.get(0);
        } else {
            Assert.assertTrue(eventosListados);
        }


        if (!(categoria == null)) {
            List<Evento> eventos = eventoService.listarPorCategoria(categoria.getId());
            Assert.assertTrue(!eventos.isEmpty());
        } else {
            Assert.assertTrue(eventosListados);
        }
    }

    @Test
    public void testeListarPorInstituto() {
        boolean eventosListados = false;

        EventoService eventoService = new EventoService(sessionFactory);
        eventoService.salvar(criarEvento());

        Instituto instituto = null;
        InstitutoService institutoService = new InstitutoService(sessionFactory);
        List<Instituto> institutos = institutoService.listar();


        if (!institutos.isEmpty()) {
            instituto = institutos.get(0);
        } else {
            Assert.assertTrue(eventosListados);
        }

        if (!(instituto == null)) {
            List<Evento> eventos = eventoService.listarPorInstituto(instituto.getId());
            Assert.assertTrue(!eventos.isEmpty());
        } else {
            Assert.assertTrue(eventosListados);
        }
    }

    @Test
    public void testeListarPorRegional() {
        boolean eventosListados = false;

        EventoService eventoService = new EventoService(sessionFactory);
        eventoService.salvar(criarEvento());

        Regional regional = null;
        RegionalService regionalService = new RegionalService(sessionFactory);

        List<Regional> regionais = regionalService.listar();


        if (!regionais.isEmpty()) {
            regional = regionais.get(0);
        } else {
            Assert.assertTrue(eventosListados);
        }

        if (!(regional == null)) {
            List<Evento> eventos = eventoService.listarPorRegional(regional.getId());
            Assert.assertTrue(!eventos.isEmpty());
        } else {
            Assert.assertTrue(eventosListados);
        }
    }

    @SuppressWarnings("deprecation")
    @Test
    public void testeListarPorPeriodo() {
        EventoService eventoService = new EventoService(sessionFactory);

        Evento evento = criarEvento();
        evento.setDataInicial(new Date(2016, 11, 24));
        evento.setDataFinal(new Date(2016, 12, 24));

        eventoService.salvar(evento);

        evento = criarEvento();

        evento.setDataInicial(new Date(2015, 11, 24));
        evento.setDataFinal(new Date(2015, 12, 24));

        eventoService.salvar(evento);


        List<Evento> eventos = eventoService.listarEventosPorPeriodo(new Date(2016, 11, 24), new Date(2016, 12, 24));

        Assert.assertTrue(eventos.size() == 1);
    }

    @SuppressWarnings("deprecation")
    @Test
    public void testeListarPorData() {
        EventoService eventoService = new EventoService(sessionFactory);

        Evento evento = criarEvento();
        evento.setDataInicial(new Date(2016, 11, 24));
        eventoService.salvar(evento);

        evento = criarEvento();
        evento.setDataInicial(new Date(2015, 11, 24));
        eventoService.salvar(evento);

        List<Evento> eventos = eventoService.listarEventosPorData(new Date(2016, 11, 24));

        Assert.assertTrue(eventos.size() == 1);

    }

    public void limparObjeto() {
        EventoService eventoService = new EventoService(sessionFactory);
        List<Evento> eventos = eventoService.listar();

        eventos.stream().forEach(x -> eventoService.limparObjeto(x));
    }

    public Evento criarEvento() {
        inserirCategoria();

        Evento evento = new Evento();

        evento.setDataInicial(new Date());
        evento.setDataFinal(new Date());
        evento.setDescricao("Nascimento do menino jesus");
        evento.setTitulo("Vespera de Natal");
        evento.setCategoria(pesquisarCategoriaPorDescricao());

        List<Regional> regionais = consultarRegionais();
        if (regionais.isEmpty()) {
            inserirRegional();
            regionais = consultarRegionais();
        }

        evento.setRegionais(regionais);

        List<Instituto> institutos = consultarInstitutos();
        if (institutos.isEmpty()) {
            inserirInstituto();
            institutos = consultarInstitutos();
        }

        evento.setInstitutos(institutos);

        return evento;
    }

    public void inserirCategoria() {
        Categoria categoria = new Categoria();
        categoria.setNome("Feriado");
        CategoriaService service = new CategoriaService(sessionFactory);
        service.salvar(categoria);
    }

    public Categoria pesquisarCategoriaPorDescricao() {
        CategoriaService categoriaService = new CategoriaService(sessionFactory);
        List<Categoria> categorias = categoriaService.pesquisarPorDescricao("Feriado");
        if (!categorias.isEmpty()) {
            return categorias.get(0);
        }
        return new Categoria();
    }

    public void inserirRegional() {
        Regional regional = new Regional();
        regional.setNome("Goiania");
        RegionalService regionalService = new RegionalService(sessionFactory);
        regionalService.salvar(regional);
    }

    public List<Regional> consultarRegionais() {
        RegionalService regionalService = new RegionalService(sessionFactory);
        List<Regional> regionais = regionalService.listar();

        return regionais;
    }

    public void inserirInstituto() {
        Instituto instituto = new Instituto();
        instituto.setNome("INF - Instituto de Informatica");

        InstitutoService institutoService = new InstitutoService(sessionFactory);
        institutoService.salvar(instituto);
    }

    public List<Instituto> consultarInstitutos() {
        InstitutoService institutoService = new InstitutoService(sessionFactory);
        List<Instituto> institutos = institutoService.listar();

        return institutos;
    }
}
