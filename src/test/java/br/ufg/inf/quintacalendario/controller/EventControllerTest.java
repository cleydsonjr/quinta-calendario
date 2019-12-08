package br.ufg.inf.quintacalendario.controller;

import br.ufg.inf.quintacalendario.main.Application;
import br.ufg.inf.quintacalendario.model.Category;
import br.ufg.inf.quintacalendario.model.Event;
import br.ufg.inf.quintacalendario.model.Institute;
import br.ufg.inf.quintacalendario.model.Regional;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class EventControllerTest {

	private final EventController eventControllerUnderTest = new EventController();
	private final RegionalController regionalController = new RegionalController();
	private final CategoryController categoryController = new CategoryController();
	private final InstituteController instituteController = new InstituteController();

	private final String description = "Creating Event";
	private final String title = "Festinha";
	private final String initialDate = "28/01/1999";
	private final String finalDate = "30/01/1999";
	private final Integer categoryId = 1;
	private final Integer regionalId = 1;
	private final Integer instituteId = 1;

	private static SessionFactory sessionFactory;

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;

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
		new EventService(sessionFactory).truncateTable();
	}

	@Test
	public void testRegisterSuccessfully() {
		Assert.assertTrue(eventControllerUnderTest.register(description, title, null, finalDate, categoryId, regionalId, instituteId));
	}

	@Test
	public void testListRecords() {
		generateTwoRandomEvents();
		List<Event> events = eventControllerUnderTest.listRecords();

		List<Event> eventsByDescription = eventControllerUnderTest.listRecordsByDescription(description);

		assertEquals(2, events.size());
		assertEquals(events.get(0).getId(), eventsByDescription.get(0).getId());
	}

	@Test
	public void testListRegionals() {
		generateRandomRegional();

		List<Regional> regionals = eventControllerUnderTest.listRegionals();

		assertEquals(1, regionals.size());
	}

	@Test
	public void testListInstitute() {
		generateRandomInstitute();

		List<Institute> institutes = eventControllerUnderTest.listInstitutes();

		assertEquals(1, institutes.size());
	}

	@Test
	public void testListCategories() {
		generateRandomCategory();

		List<Category> categories = eventControllerUnderTest.listCategories();

		assertEquals(1, categories.size());
	}

	@Test
	public void testListByPeriod() {
		generateTwoRandomEvents();
		List<Event> noOneEvents = eventControllerUnderTest.listByPeriod("30/03/2002", "30/04/2002");
		List<Event> events = eventControllerUnderTest.listByPeriod(initialDate, "30/04/2002");

		assertEquals(2, events.size());
		assertEquals(0, noOneEvents.size());
	}

	@Test
	public void testListByIdReturnsNoOne() {
		Event event = eventControllerUnderTest.listById(409);

		assertNull(event);
	}

	@Test
	public void testListByDescriptionReturnsEmptyList() {
		List<Event> events = eventControllerUnderTest.listRecordsByDescription("409");

		assertEquals(Collections.emptyList(), events);
	}

	public void generateTwoRandomEvents() {
		eventControllerUnderTest.register(description, title, initialDate, finalDate, categoryId, regionalId, instituteId);
		eventControllerUnderTest.register("Another description", title, initialDate, finalDate, categoryId, regionalId, instituteId);
	}

	public void generateRandomRegional() {
		regionalController.register("Regional to this test");
	}

	public void generateRandomCategory() {
		categoryController.register("Category to this test");
	}

	public void generateRandomInstitute() {
		instituteController.register("Institute to this test");

	}

	public void limparObjetoEvento() {
		EventService eventService = new EventService(sessionFactory);
		List<Event> events = eventService.listRecords();

		events.forEach(eventService::clearObject);
	}
}
