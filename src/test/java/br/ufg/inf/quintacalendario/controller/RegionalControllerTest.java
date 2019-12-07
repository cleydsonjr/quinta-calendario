package br.ufg.inf.quintacalendario.controller;

import br.ufg.inf.quintacalendario.model.Regional;
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

public class RegionalControllerTest {

	private final RegionalController regionalControllerUnderTest = new RegionalController();

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;

	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
	}

	@After
	public void restoreStreams() {
		System.setOut(originalOut);
	}

	@Test
	public void testRegisterSuccessfully() {
		String name = "Creating Regional";

		Assert.assertTrue(regionalControllerUnderTest.register(name));
	}

	@Test
	public void testDontRegister() {
		Assert.assertFalse(regionalControllerUnderTest.register(null));
	}

	@Test(expected = NullPointerException.class)
	public void testEdit() {
		//setup
		Regional regional = new Regional();
		regional.setId(1);
		regional.setName("Regional under test");

		//run
		regionalControllerUnderTest.edit(1, "New name");
	}

	@Test
	public void testRemoveInvalidCode() {
		//setup
		Regional regional = new Regional();
		regional.setId(1);

		//run
		regionalControllerUnderTest.remove(409);

		assertThat(outContent.toString(), containsString("Codigo invalido"));
	}

	@Test
	public void testListByIdReturnsNoOne() {
		Regional regional = regionalControllerUnderTest.listById(409);

		assertNull(regional);
	}

	@Test
	public void testListByDescriptionReturnsEmptyList() {
		List<Regional> regionals = regionalControllerUnderTest.listRecordsByDescription("409");

		assertEquals(Collections.emptyList(), regionals);
	}
}
