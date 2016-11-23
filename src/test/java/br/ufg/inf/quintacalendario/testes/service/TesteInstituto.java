package br.ufg.inf.quintacalendario.testes.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.ufg.inf.quintacalendario.main.Application;
import br.ufg.inf.quintacalendario.model.Instituto;
import br.ufg.inf.quintacalendario.service.InstitutoService;

public class TesteInstituto {
	private InstitutoService institutoService;
	
	@Before
	public void init(){
		setInstitutoService(new InstitutoService(Application.getInstance().getSessionFactory()));
	}
	
	@Test
	public void testeSalvarInstituto(){
		Instituto instituto = new Instituto();
		instituto.setNome("Feriado");
		boolean retorno = getInstitutoService().salvar(instituto);
		Assert.assertTrue(retorno);
	}
	
	@Test
	public void testeSalvarInstitutoComNomeVazio(){
		Instituto instituto = new Instituto();
		instituto.setNome("");
		boolean retorno = getInstitutoService().salvar(instituto);
		Assert.assertFalse(retorno);
	}

	public InstitutoService getInstitutoService() {
		return institutoService;
	}

	public void setInstitutoService(InstitutoService institutoService) {
		this.institutoService = institutoService;
	}
}
