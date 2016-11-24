package br.ufg.inf.quintacalendario.testes.service;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.ufg.inf.quintacalendario.main.Application;
import br.ufg.inf.quintacalendario.model.Categoria;
import br.ufg.inf.quintacalendario.model.Evento;
import br.ufg.inf.quintacalendario.service.CategoriaService;
import br.ufg.inf.quintacalendario.service.EventoService;

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
		
		evento.setDataInicial(LocalDate.of(2016, 12, 24));
		evento.setDataFinal(LocalDate.of(2016, 12, 24));
		evento.setDescricao("Nascimento do menino jesus");
		evento.setTitulo("Vespera de Natal");
		evento.setCategoria(pesquisarCategoriaPorDescricao());
		
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
}
