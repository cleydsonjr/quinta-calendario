package br.ufg.inf.quintacalendario.controller;

import br.ufg.inf.quintacalendario.main.Application;
import br.ufg.inf.quintacalendario.model.Regional;
import br.ufg.inf.quintacalendario.service.RegionalService;
import br.ufg.inf.quintacalendario.view.console.RegionalScreenConsole;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Objects;

public class RegionalController {

    private RegionalScreenConsole regionalScreen;
    private SessionFactory sessionFactory;

    public RegionalController() {
        regionalScreen = new RegionalScreenConsole(System.out);
        sessionFactory = Application.getInstance().getSessionFactory();
    }

    void showHisOptions() {
        getRegionalScreen().showOptions();
    }

    public boolean register(String name) {
        Regional regional = new Regional();
        regional.setName(name);

        RegionalService regionalService = new RegionalService(getSessionFactory());
        return regionalService.save(regional);
    }

    public List<Regional> listRecords() {
        RegionalService regionalService = new RegionalService(getSessionFactory());
        return regionalService.listRecords();
    }

    public List<Regional> listRecordsByDescription(String description) {
        RegionalService regionalService = new RegionalService(getSessionFactory());
        return regionalService.listRecordsByDescription(description);
    }

    public Regional listById(Integer id) {
        RegionalService service = new RegionalService(getSessionFactory());
        return service.listById(id);
    }

    public void edit(Integer id, String name) {
        RegionalService regionalService = new RegionalService(getSessionFactory());
        regionalService.edit(id, name);
    }

    public void remove(Integer id) {
        RegionalService regionalService = new RegionalService(getSessionFactory());
        Regional regional = regionalService.listById(id);

        if (Objects.isNull(regional)) {
            System.out.println("*******Codigo invalido*******");
            getRegionalScreen().remove();
        } else {
            regionalService.remove(id);
        }
    }

    private RegionalScreenConsole getRegionalScreen() {
        return regionalScreen;
    }

    public void setRegionalScreen(RegionalScreenConsole regionalScreen) {
        this.regionalScreen = regionalScreen;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
