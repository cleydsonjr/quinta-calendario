package br.ufg.inf.quintacalendario.controller;

import br.ufg.inf.quintacalendario.main.Application;
import br.ufg.inf.quintacalendario.model.Event;
import br.ufg.inf.quintacalendario.model.Institute;
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

public class InstituteControllerTest {

	private final InstituteController instituteControllerUnderTest = new InstituteController();

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;

	private static SessionFactory sessionFactory;

	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));

		sessionFactory = Application.getInstance().getSessionFactory();

		limparObjetoEvento();
		new EventService(sessionFactory).truncateTable();
		new RegionalService(sessionFactory).truncateTable();
		new CategoryService(sessionFactory).truncateTable();
		new InstituteService(sessionFactory).truncateTable();
	}

	@After
	public void restoreStreams() {
		System.setOut(originalOut);

		new InstituteService(sessionFactory).truncateTable();
	}

	@Test
	public void testRegisterSuccessfully() {
		String name = "Creating Institute";

		Assert.assertTrue(instituteControllerUnderTest.register(name));
	}

	@Test
	public void testDontRegister() {
		Assert.assertFalse(instituteControllerUnderTest.register(null));
	}

	@Test(expected = NullPointerException.class)
	public void testEdit() {
		//setup
		Institute institute = new Institute();
		institute.setId(1);
		institute.setName("Institute under test");

		//run
		instituteControllerUnderTest.edit(1, "New name");
	}

	@Test
	public void testRemoveInvalidCode() {
		//setup
		Institute institute = new Institute();
		institute.setId(1);

		//run
		instituteControllerUnderTest.remove(409);

		assertThat(outContent.toString(), containsString("Codigo invalido"));
	}

	@Test
	public void testRemove() {
		//setup
		generateRandomInstitute();
		List<Institute> institutes = instituteControllerUnderTest.listRecords();
		long id = institutes.get(0).getId();

		//run
		instituteControllerUnderTest.remove((int) id);

		assertEquals("", outContent.toString());
	}

	@Test
	public void testListByIdReturnsNoOne() {
		Institute regional = instituteControllerUnderTest.listById(409);

		assertNull(regional);
	}

	@Test
	public void testListByDescriptionReturnsEmptyList() {
		List<Institute> institutes = instituteControllerUnderTest.listRecordsByDescription("409");

		assertEquals(Collections.emptyList(), institutes);
	}

	public void generateRandomInstitute() {
		instituteControllerUnderTest.register("Creating to test list");
	}

	public void limparObjetoEvento() {
		EventService eventService = new EventService(sessionFactory);
		List<Event> events = eventService.listRecords();

		events.stream().forEach(x -> eventService.clearObject(x));
	}

}
