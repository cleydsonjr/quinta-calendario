package br.ufg.inf.quintacalendario.testes.controller;

import br.ufg.inf.quintacalendario.controller.CategoryController;
import br.ufg.inf.quintacalendario.main.Application;
import br.ufg.inf.quintacalendario.service.CategoryService;
import br.ufg.inf.quintacalendario.service.EventService;
import br.ufg.inf.quintacalendario.service.InstitutoService;
import br.ufg.inf.quintacalendario.service.RegionalService;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryControllerTest {

	CategoryController categoryController = new CategoryController();
	SessionFactory sessionFactory;

	@Before
	public void init() {
		sessionFactory = Application.getInstance().getSessionFactory();

		new CategoryService(sessionFactory).limparTabela();
	}

	@After
	public void finalizar() {
		new CategoryService(sessionFactory).limparTabela();
	}

	@Test
	public void registerSuccessfully() {
		String name = "Creating Category";

		Assert.assertTrue(categoryController.register(name));
	}

	@Test
	public void dontRegister() {
		Assert.assertFalse(categoryController.register(null));
	}

}
