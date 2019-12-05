package br.ufg.inf.quintacalendario.controller;

import br.ufg.inf.quintacalendario.main.Application;
import br.ufg.inf.quintacalendario.model.Category;
import br.ufg.inf.quintacalendario.service.CategoryService;
import br.ufg.inf.quintacalendario.view.console.CategoryScreenConsole;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Objects;

public class CategoryController {

    private CategoryScreenConsole categoryScreen;
    private SessionFactory sessionFactory;

    public CategoryController() {
        categoryScreen = new CategoryScreenConsole(System.out);
        sessionFactory = Application.getInstance().getSessionFactory();
    }

    void showHisOptions() {
        getCategoryScreen().showOptions();
    }

    public boolean register(String name) {
        Category category = new Category();
        category.setName(name);

        CategoryService categoryService = new CategoryService(getSessionFactory());
        return categoryService.save(category);
    }

    public List<Category> listRecords() {
        CategoryService service = new CategoryService(getSessionFactory());
        return service.listRecords();
    }

    public List<Category> listRecordsByDescription(String description) {
        CategoryService categoryService = new CategoryService(getSessionFactory());
        return categoryService.listRecordsByDescription(description);
    }

    public Category listById(Integer id) {
        CategoryService categoryService = new CategoryService(getSessionFactory());
        return categoryService.listById(id);
    }

    public void edit(Integer id, String name) {
        CategoryService categoryService = new CategoryService(getSessionFactory());
        categoryService.edit(id, name);
    }

    public void remove(Integer id) {
        CategoryService categoryService = new CategoryService(getSessionFactory());
        Category category = categoryService.listById(id);

        if (Objects.isNull(category)) {
            System.out.println("******* Codigo invalido *******");
            getCategoryScreen().remove();
        } else {
            categoryService.remove(id);
        }
    }

    private CategoryScreenConsole getCategoryScreen() {
        return categoryScreen;
    }

    public void setCategoryScreen(CategoryScreenConsole categoryScreen) {
        this.categoryScreen = categoryScreen;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
