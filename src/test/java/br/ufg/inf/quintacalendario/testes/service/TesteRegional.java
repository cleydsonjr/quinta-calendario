package br.ufg.inf.quintacalendario.testes.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.ufg.inf.quintacalendario.main.Application;
import br.ufg.inf.quintacalendario.model.Regional;
import br.ufg.inf.quintacalendario.service.RegionalService;

public class TesteRegional {
	
	private RegionalService regionalService;
	
	@Before
	public void init(){
		setRegionalService(new RegionalService(Application.getInstance().getSessionFactory()));
	}
	
	@Test
	public void testeSalvarRegional(){
		Regional regional = new Regional();
		regional.setNome("Goiânia");
		
		boolean retorno = getRegionalService().salvar(regional);
		Assert.assertTrue(retorno);
	}
	
	@Test
	public void testeRegionalComNomeVazio(){
		Regional regional = new Regional();
		regional.setNome("");
		
		boolean retorno = getRegionalService().salvar(regional);
		Assert.assertFalse(retorno);
	}

	public RegionalService getRegionalService() {
		return regionalService;
	}

	public void setRegionalService(RegionalService regionalService) {
		this.regionalService = regionalService;
	}
}
