package br.ufg.inf.quintacalendario.controller;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTest {

	@Test
	public void testSetName() {
		User user = new User();
		user.setName("Hyago");

		assertEquals("Hyago", user.getName());
	}

	@Test
	public void testSetPassword() {
		User user = new User();
		user.setPassword("123");

		assertEquals("123", user.getPassword());
	}
}
