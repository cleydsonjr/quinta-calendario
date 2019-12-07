package br.ufg.inf.quintacalendario.controller;

import br.ufg.inf.quintacalendario.main.Application;
import br.ufg.inf.quintacalendario.model.Category;
import br.ufg.inf.quintacalendario.service.CategoryService;
import br.ufg.inf.quintacalendario.view.console.CategoryScreenConsole;

import java.util.List;
import java.util.Objects;

/**
 * Controller responsible for intercepting category entity operations, extend AbstractController class
 *
 * @author Hyago Souza
 * @see AbstractController
 */
public class CategoryController extends AbstractController {

    private CategoryScreenConsole categoryScreen;

    /**
     * Constructor's class
     */
    public CategoryController() {
        super(Application.getInstance().getSessionFactory());
        categoryScreen = new CategoryScreenConsole(System.out);
    }

    /**
     * Show category options on screen
     */
    void showHisOptions() {
        getCategoryScreen().showOptions();
    }

    /**
     * Register a new category with received name
     *
     * @param name name of category to create
     * @return boolean representing success or error
     */
    public boolean register(String name) {
        Category category = new Category();
        category.setName(name);

        CategoryService categoryService = new CategoryService(getAbstractSessionFactory());
        return categoryService.save(category);
    }

    /**
     * Returns all category records on database
     *
     * @return list of records
     * @see List<Category>
     */
    public List<Category> listRecords() {
        CategoryService service = new CategoryService(getAbstractSessionFactory());
        return service.listRecords();
    }

    /**
     * Returns all category records on database filtering by their description
     *
     * @param description field used on query filter
     * @return list of records filtered by description
     * @see List<Category>
     */
    public List<Category> listRecordsByDescription(String description) {
        CategoryService categoryService = new CategoryService(getAbstractSessionFactory());
        return categoryService.listRecordsByDescription(description);
    }

    /**
     * Returns a single category record on database filtering by your id
     *
     * @param id category identifier
     * @return category with specified identifier
     * @see Category
     */
    public Category listById(Integer id) {
        CategoryService categoryService = new CategoryService(getAbstractSessionFactory());
        return categoryService.listById(id);
    }

    /**
     * Edit a specific category
     *
     * @param id category identifier
     * @param name category name
     */
    public void edit(Integer id, String name) {
        CategoryService categoryService = new CategoryService(getAbstractSessionFactory());
        categoryService.edit(id, name);
    }

    /**
     * Remove a specific category of records
     *
     * @param id category identifier
     */
    public void remove(Integer id) {
        CategoryService categoryService = new CategoryService(getAbstractSessionFactory());
        Category category = categoryService.listById(id);

        if (Objects.isNull(category)) {
            System.out.println("******* Codigo invalido *******");
            getCategoryScreen().remove();
        } else {
            categoryService.remove(id);
        }
    }

    /**
     * Returns screen console of category entity
     *
     * @return category screen console
     * @see CategoryScreenConsole
     */
    public CategoryScreenConsole getCategoryScreen() {
        return categoryScreen;
    }

    /**
     * Attribute a category screen console to entity
     *
     * @param categoryScreen category screen console
     * @see CategoryScreenConsole
     */
    public void setCategoryScreen(CategoryScreenConsole categoryScreen) {
        this.categoryScreen = categoryScreen;
    }

}
