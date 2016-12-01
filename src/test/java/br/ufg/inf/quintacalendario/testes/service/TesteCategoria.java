package br.ufg.inf.quintacalendario.testes.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.ufg.inf.quintacalendario.main.Application;
import br.ufg.inf.quintacalendario.model.Categoria;
import br.ufg.inf.quintacalendario.model.Evento;
import br.ufg.inf.quintacalendario.service.CategoriaService;
import br.ufg.inf.quintacalendario.service.EventoService;
import br.ufg.inf.quintacalendario.service.InstitutoService;
import br.ufg.inf.quintacalendario.service.RegionalService;

public class TesteCategoria {
	
	SessionFactory sessionFactory;
	
	@Before
	public void init(){
		sessionFactory = Application.getInstance().getSessionFactory();
		
		limparObjetoEvento();
		new RegionalService(sessionFactory).limparTabela();
		new EventoService(sessionFactory).limparTabela();
		new InstitutoService(sessionFactory).limparTabela();
		new CategoriaService(sessionFactory).limparTabela();
	}
	
	@After
	public void finalizar(){
		new CategoriaService(sessionFactory).limparTabela();
	}
	
	@Test
	public void testeSalvarCategoria(){
		Categoria categoria = new Categoria();
		categoria.setNome("Feriado");
		boolean retorno = new CategoriaService(sessionFactory).salvar(categoria);
		Assert.assertTrue(retorno);
	}
	
	@Test
	public void testeSalvarCategoriaComNomeVazio(){
		Categoria categoria = new Categoria();
		categoria.setNome("");
		boolean retorno = new CategoriaService(sessionFactory).salvar(categoria);
		Assert.assertFalse(retorno);
	}
	
	public void limparObjetoEvento(){
		EventoService eventoService = new EventoService(sessionFactory);
		List<Evento> eventos = eventoService.listar();
		
		eventos.stream().forEach(x-> eventoService.limparObjeto(x));
	}
}
