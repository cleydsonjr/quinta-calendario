package br.ufg.inf.quintacalendario.controller;

import br.ufg.inf.quintacalendario.main.Application;
import br.ufg.inf.quintacalendario.model.Category;
import br.ufg.inf.quintacalendario.model.Event;
import br.ufg.inf.quintacalendario.service.CategoryService;
import br.ufg.inf.quintacalendario.service.EventService;
import br.ufg.inf.quintacalendario.service.InstituteService;
import br.ufg.inf.quintacalendario.service.RegionalService;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.*;

public class CategoryControllerTest {

	private CategoryController categoryControllerUnderTest = new CategoryController();

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;

	private SessionFactory sessionFactory;

	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));

		sessionFactory = Application.getInstance().getSessionFactory();

		limparObjetoEvento();
		new RegionalService(sessionFactory).truncateTable();
		new EventService(sessionFactory).truncateTable();
		new InstituteService(sessionFactory).truncateTable();
		new CategoryService(sessionFactory).truncateTable();
	}

	@After
	public void restoreStreams() {
		System.setOut(originalOut);

		new CategoryService(sessionFactory).truncateTable();
	}

	@Test
	public void testRegisterSuccessfully() {
		String name = "Creating Category";

		Assert.assertTrue(categoryControllerUnderTest.register(name));
	}

	@Test
	public void testDontRegister() {
		Assert.assertFalse(categoryControllerUnderTest.register(null));
	}

	@Test(expected = NullPointerException.class)
	public void testEdit() {
		//setup
		Category category = new Category();
		category.setId(1);
		category.setName("Category under test");

		//run
		categoryControllerUnderTest.edit(1, "New name");
	}

	@Test
	public void testRemoveInvalidCode() {
		//setup
		Category category = new Category();
		category.setId(1);

		//run
		categoryControllerUnderTest.remove(409);

		assertThat(outContent.toString(), containsString("Codigo invalido"));
	}

	@Test
	public void testRemove() {
		//setup
		generateRandomCategory();
		List<Category> categories = categoryControllerUnderTest.listRecords();
		long id = categories.get(0).getId();

		//run
		categoryControllerUnderTest.remove((int) id);

		assertEquals("", outContent.toString());
	}

	@Test
	public void testListByIdReturnsNoOne() {
		Category category = categoryControllerUnderTest.listById(409);

		assertNull(category);
	}

	@Test
	public void testListByDescriptionReturnsEmptyList() {
		List<Category> categories = categoryControllerUnderTest.listRecordsByDescription("409");

		assertEquals(Collections.emptyList(), categories);
	}

	public void generateRandomCategory() {
		categoryControllerUnderTest.register("Fresh Category");
	}

	public void limparObjetoEvento() {
		EventService eventService = new EventService(sessionFactory);
		List<Event> events = eventService.listRecords();

		events.forEach(eventService::clearObject);
	}

}
