package br.ufg.inf.quintacalendario.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

public class HomePageControllerTest {

	private final HomePageController homePageControllerUnderTest = new HomePageController();

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
	public void redirectScreenByTelaInicialConsole() {
		homePageControllerUnderTest.redirectScreenBy(6);

		assertThat(outContent.toString(), containsString("Finalizando programa"));
	}

	@Test
	public void redirectScreenByDefault() {
		homePageControllerUnderTest.redirectScreenBy(0);

		assertThat(outContent.toString(), containsString(""));
	}
}
