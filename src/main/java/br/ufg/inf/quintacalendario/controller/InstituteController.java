package br.ufg.inf.quintacalendario.controller;

import br.ufg.inf.quintacalendario.main.Application;
import br.ufg.inf.quintacalendario.model.Institute;
import br.ufg.inf.quintacalendario.service.InstituteService;
import br.ufg.inf.quintacalendario.view.console.InstituteScreenConsole;

import java.util.List;
import java.util.Objects;

/**
 * Controller responsible for intercepting institute entity operations, extend AbstractController class
 *
 * @author Hyago Souza
 * @see AbstractController
 */
public class InstituteController extends AbstractController {

    private InstituteScreenConsole instituteScreen;

    /**
     * Constructor's class
     */
    public InstituteController() {
        super(Application.getInstance().getSessionFactory());
        setInstituteScreen(new InstituteScreenConsole(System.out));
    }

    /**
     * Show category options on screen
     */
    void showHisOptions() {
        getInstituteScreen().showOptions();
    }

    /**
     * Register a new institute with received name
     *
     * @param name name of institute to create
     * @return boolean representing success or error
     */
    public boolean register(String name) {
        Institute institute = new Institute();
        institute.setName(name);

        InstituteService instituteService = new InstituteService(getAbstractSessionFactory());
        return instituteService.save(institute);
    }

    /**
     * Returns all institute records on database
     *
     * @return list of records
     * @see List<Institute>
     */
    public List<Institute> listRecords() {
        InstituteService instituteService = new InstituteService(getAbstractSessionFactory());
        return instituteService.listRecords();
    }

    /**
     * Returns all institute records on database filtering by their description
     *
     * @param description field used on query filter
     * @return list of records filtered by description
     * @see List<Institute>
     */
    public List<Institute> listRecordsByDescription(String description) {
        InstituteService instituteService = new InstituteService(getAbstractSessionFactory());
        return instituteService.listRecordsByDescription(description);
    }

    /**
     * Returns a single institute record on database filtering by your id
     *
     * @param id institute identifier
     * @return institute with specified identifier
     * @see Institute
     */
    public Institute listById(Integer id) {
        InstituteService instituteService = new InstituteService(getAbstractSessionFactory());
        return instituteService.listById(id);
    }

    /**
     * Edit a specific institute
     *
     * @param id institute identifier
     * @param name institute name
     */
    public void edit(Integer id, String name) {
        InstituteService instituteService = new InstituteService(getAbstractSessionFactory());
        instituteService.edit(id, name);
    }

    /**
     * Remove a specific institute of records
     *
     * @param id institute identifier
     */
    public void remove(Integer id) {
        InstituteService instituteService = new InstituteService(getAbstractSessionFactory());
        Institute institute = instituteService.listById(id);

        if (Objects.isNull(institute)) {
            System.out.println("******* Codigo invalido *******");
            getInstituteScreen().remove();
        } else {
            instituteService.remove(id);
        }
    }

    /**
     * Returns screen console of institute entity
     *
     * @return institute screen console
     * @see InstituteScreenConsole
     */
    public InstituteScreenConsole getInstituteScreen() {
        return instituteScreen;
    }

    /**
     * Attribute a institute screen console to entity
     *
     * @param instituteScreen institute screen console
     * @see InstituteScreenConsole
     */
    public void setInstituteScreen(InstituteScreenConsole instituteScreen) {
        this.instituteScreen = instituteScreen;
    }

}
