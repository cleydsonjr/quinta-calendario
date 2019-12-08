package br.ufg.inf.quintacalendario.service;

import br.ufg.inf.quintacalendario.main.Application;
import br.ufg.inf.quintacalendario.model.Category;
import br.ufg.inf.quintacalendario.model.Event;
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

public class CategoryServiceTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    private final PrintStream originalOut = System.out;

    private SessionFactory sessionFactory;

    private CategoryService categoryServiceUnderTest;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));

        sessionFactory = Application.getInstance().getSessionFactory();
        categoryServiceUnderTest = new CategoryService(sessionFactory);

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
    public void testSaveSuccessfully() {
        Category category = new Category();
        category.setName("Creating Category");
        Assert.assertTrue(categoryServiceUnderTest.save(category));
    }

    @Test
    public void testDontSave() {
        Assert.assertFalse(categoryServiceUnderTest.save(null));
    }

    @Test(expected = NullPointerException.class)
    public void testEdit() {
        //setup
        Category category = new Category();
        category.setId(1);
        category.setName("Category under test");

        //run
        categoryServiceUnderTest.edit(1, "New name");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveInvalidCode() {
        //setup
        Category category = new Category();
        category.setId(1);

        //run
        categoryServiceUnderTest.remove(409);

        assertThat(outContent.toString(), containsString("Codigo invalido"));
    }

    @Test
    public void testRemove() {
        //setup
        generateRandomCategory();
        List<Category> categories = categoryServiceUnderTest.listRecords();
        long id = categories.get(0).getId();

        //run
        categoryServiceUnderTest.remove((int) id);

        assertEquals("", outContent.toString());
    }

    @Test
    public void testListByIdReturnsNoOne() {
        Category category = categoryServiceUnderTest.listById(409);

        assertNull(category);
    }

    @Test
    public void testListByDescriptionReturnsEmptyList() {
        List<Category> categories = categoryServiceUnderTest.listRecordsByDescription("409");

        assertEquals(Collections.emptyList(), categories);
    }

    private void generateRandomCategory() {
        Category category = new Category();
        category.setName("Fresh Category");
        categoryServiceUnderTest.save(category);
    }

    private void limparObjetoEvento() {
        EventService eventService = new EventService(sessionFactory);
        List<Event> events = eventService.listRecords();

        events.forEach(eventService::clearObject);
    }
}
