package br.ufg.inf.quintacalendario.testes.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.ufg.inf.quintacalendario.main.Application;
import br.ufg.inf.quintacalendario.model.Evento;
import br.ufg.inf.quintacalendario.model.Instituto;
import br.ufg.inf.quintacalendario.service.CategoriaService;
import br.ufg.inf.quintacalendario.service.EventoService;
import br.ufg.inf.quintacalendario.service.InstitutoService;
import br.ufg.inf.quintacalendario.service.RegionalService;

public class TesteInstituto {
	
	private SessionFactory sessionFactory;
	
	@Before
	public void init(){
		
		sessionFactory = Application.getInstance().getSessionFactory();
		
		limparObjetoEvento();
		new EventoService(sessionFactory).limparTabela();
		new RegionalService(sessionFactory).limparTabela();
		new CategoriaService(sessionFactory).limparTabela();
		new InstitutoService(sessionFactory).limparTabela();
	}
	
	@After
	public void finalizar(){
		new InstitutoService(sessionFactory).limparTabela();
	}
	
	@Test
	public void testeSalvarInstituto(){
		Instituto instituto = new Instituto();
		instituto.setNome("Feriado");
		boolean retorno = new InstitutoService(sessionFactory).salvar(instituto);
		Assert.assertTrue(retorno);
	}
	
	@Test
	public void testeSalvarInstitutoComNomeVazio(){
		Instituto instituto = new Instituto();
		instituto.setNome("");
		boolean retorno = new InstitutoService(sessionFactory).salvar(instituto);
		Assert.assertFalse(retorno);
	}
	
	public void limparObjetoEvento(){
		EventoService eventoService = new EventoService(sessionFactory);
		List<Evento> eventos = eventoService.listar();
		
		eventos.stream().forEach(x-> eventoService.limparObjeto(x));
	}
}
