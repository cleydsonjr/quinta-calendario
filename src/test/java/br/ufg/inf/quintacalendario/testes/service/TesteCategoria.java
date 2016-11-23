package br.ufg.inf.quintacalendario.testes.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.ufg.inf.quintacalendario.main.Application;
import br.ufg.inf.quintacalendario.model.Categoria;
import br.ufg.inf.quintacalendario.service.CategoriaService;

public class TesteCategoria {
	
	private CategoriaService categoriaService;
	
	@Before
	public void init(){
		categoriaService = new CategoriaService(Application.getInstance().getSessionFactory());
	}
	
	@Test
	public void testeSalvarCategoria(){
		Categoria categoria = new Categoria();
		categoria.setNome("Feriado");
		boolean retorno = categoriaService.salvar(categoria);
		Assert.assertTrue(retorno);
	}
	
	@Test
	public void testeSalvarCategoriaComNomeVazio(){
		Categoria categoria = new Categoria();
		categoria.setNome("");
		boolean retorno = categoriaService.salvar(categoria);
		Assert.assertFalse(retorno);
	}

	public CategoriaService getCategoriaService() {
		return categoriaService;
	}

	public void setCategoriaService(CategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}
}
