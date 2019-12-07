package br.ufg.inf.quintacalendario.controller;

import br.ufg.inf.quintacalendario.main.Application;
import br.ufg.inf.quintacalendario.model.Regional;
import br.ufg.inf.quintacalendario.service.RegionalService;
import br.ufg.inf.quintacalendario.view.console.RegionalScreenConsole;

import java.util.List;
import java.util.Objects;

/**
 * Controller responsible for intercepting regional entity operations, extend AbstractController class
 *
 * @author Hyago Souza
 * @see AbstractController
 */
public class RegionalController extends AbstractController {

    private RegionalScreenConsole regionalScreen;

    /**
     * Constructor's class
     */
    public RegionalController() {
        super(Application.getInstance().getSessionFactory());
        regionalScreen = new RegionalScreenConsole(System.out);
    }

    /**
     * Show category options on screen
     */
    void showHisOptions() {
        getRegionalScreen().showOptions();
    }

    /**
     * Register a new regional with received name
     *
     * @param name name of regional to create
     * @return boolean representing success or error
     */
    public boolean register(String name) {
        Regional regional = new Regional();
        regional.setName(name);

        RegionalService regionalService = new RegionalService(getAbstractSessionFactory());
        return regionalService.save(regional);
    }

    /**
     * Returns all regional records on database
     *
     * @return list of records
     * @see List<Regional>
     */
    public List<Regional> listRecords() {
        RegionalService regionalService = new RegionalService(getAbstractSessionFactory());
        return regionalService.listRecords();
    }

    /**
     * Returns all regional records on database filtering by their description
     *
     * @param description field used on query filter
     * @return list of records filtered by description
     * @see List<Regional>
     */
    public List<Regional> listRecordsByDescription(String description) {
        RegionalService regionalService = new RegionalService(getAbstractSessionFactory());
        return regionalService.listRecordsByDescription(description);
    }

    /**
     * Returns a single regional record on database filtering by your id
     *
     * @param id regional identifier
     * @return regional with specified identifier
     * @see Regional
     */
    public Regional listById(Integer id) {
        RegionalService service = new RegionalService(getAbstractSessionFactory());
        return service.listById(id);
    }

    /**
     * Edit a specific regional
     *
     * @param id regional identifier
     * @param name regional name
     */
    public void edit(Integer id, String name) {
        RegionalService regionalService = new RegionalService(getAbstractSessionFactory());
        regionalService.edit(id, name);
    }

    /**
     * Remove a specific regional of records
     *
     * @param id regional identifier
     */
    public void remove(Integer id) {
        RegionalService regionalService = new RegionalService(getAbstractSessionFactory());
        Regional regional = regionalService.listById(id);

        if (Objects.isNull(regional)) {
            System.out.println("*******Codigo invalido*******");
            getRegionalScreen().remove();
        } else {
            regionalService.remove(id);
        }
    }

    /**
     * Returns screen console of regional entity
     *
     * @return regional screen console
     * @see RegionalScreenConsole
     */
    public RegionalScreenConsole getRegionalScreen() {
        return regionalScreen;
    }

    /**
     * Attribute a regional screen console to entity
     *
     * @param regionalScreen regional screen console
     * @see RegionalScreenConsole
     */
    public void setRegionalScreen(RegionalScreenConsole regionalScreen) {
        this.regionalScreen = regionalScreen;
    }

}
