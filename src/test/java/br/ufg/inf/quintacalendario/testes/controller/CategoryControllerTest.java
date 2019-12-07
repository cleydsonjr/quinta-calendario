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
	public void register() {
		CategoryController categoryController = new CategoryController();
		String name = "Creating Category";

		Assert.assertTrue(categoryController.register(name));
		Assert.assertFalse(categoryController.register(null));
	}

	@Test
	public void listRecords() {
	}

	@Test
	public void listRecordsByDescription() {
	}

	@Test
	public void listById() {
	}

	@Test
	public void edit() {
	}

	@Test
	public void remove() {
	}
}
