package br.ufg.inf.quintacalendario.testes.service;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.ufg.inf.quintacalendario.main.Application;
import br.ufg.inf.quintacalendario.model.Categoria;
import br.ufg.inf.quintacalendario.model.Evento;
import br.ufg.inf.quintacalendario.model.Instituto;
import br.ufg.inf.quintacalendario.model.Regional;
import br.ufg.inf.quintacalendario.service.CategoriaService;
import br.ufg.inf.quintacalendario.service.EventoService;
import br.ufg.inf.quintacalendario.service.InstitutoService;
import br.ufg.inf.quintacalendario.service.RegionalService;

public class TesteEvento {
	private SessionFactory sessionFactory;
	
	@Before
	public void init(){
		sessionFactory = Application.getInstance().getSessionFactory();
	}
	
	@Test
	public void testaSalvarEvento(){
		inserirCategoria();
		
		Evento evento = new Evento();
		
		evento.setDataInicial(new Date());
		evento.setDataFinal(new Date());
		evento.setDescricao("Nascimento do menino jesus");
		evento.setTitulo("Vespera de Natal");
		evento.setCategoria(pesquisarCategoriaPorDescricao());
		
//		List<Regional> regionais = consultarRegionais();
//		if (regionais.isEmpty()) {
//			inserirRegional();
//			regionais = consultarRegionais();
//		}
//		
//		evento.setRegionais(regionais);
//		
//		List<Instituto> institutos = consultarInstitutos();
//		if (institutos.isEmpty()) {
//			inserirInstituto();
//			institutos = consultarInstitutos();
//		}
//		
//		evento.setInstitutos(institutos);
		
		EventoService eventoService = new EventoService(sessionFactory);
		boolean retorno = eventoService.salvar(evento);
		Assert.assertTrue(retorno);
	}
	
	public void inserirCategoria(){
		Categoria categoria = new Categoria();
		categoria.setNome("Feriado");
		CategoriaService service = new CategoriaService(sessionFactory);
		service.salvar(categoria);
	}
	
	public Categoria pesquisarCategoriaPorDescricao(){
		CategoriaService categoriaService = new CategoriaService(sessionFactory);
		List<Categoria> categorias = categoriaService.pesquisarPorDescricao("Feriado");
		if (!categorias.isEmpty()) {
			return categorias.get(0);
		}
		return new Categoria();
	}
	
	public void inserirRegional(){
		Regional regional = new Regional();
		regional.setNome("Goiania");
		RegionalService regionalService = new RegionalService(sessionFactory);
		regionalService.salvar(regional);
	}
	
	public List<Regional> consultarRegionais(){
		RegionalService regionalService = new RegionalService(sessionFactory);
		List<Regional> regionais = regionalService.listar();
		
		return regionais;
	}
	
	public void inserirInstituto(){
		Instituto instituto = new Instituto();
		instituto.setNome("INF - Instituto de Informatica");
		
		InstitutoService institutoService = new InstitutoService(sessionFactory);
		institutoService.salvar(instituto);
	}
	
	public List<Instituto> consultarInstitutos(){
		InstitutoService institutoService = new InstitutoService(sessionFactory);
		List<Instituto> institutos = institutoService.listar();
		
		return institutos;
	}
}
