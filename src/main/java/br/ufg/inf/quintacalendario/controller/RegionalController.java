package br.ufg.inf.quintacalendario.controller;

import br.ufg.inf.quintacalendario.main.Application;
import br.ufg.inf.quintacalendario.model.Regional;
import br.ufg.inf.quintacalendario.service.RegionalService;
import br.ufg.inf.quintacalendario.view.console.RegionalScreenConsole;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Objects;

/**
 * Controller responsible for intercepting regional entity operations
 *
 * @author Hyago Souza
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

    public boolean register(String name) {
        Regional regional = new Regional();
        regional.setName(name);

        RegionalService regionalService = new RegionalService(getAbstractSessionFactory());
        return regionalService.save(regional);
    }

    public List<Regional> listRecords() {
        RegionalService regionalService = new RegionalService(getAbstractSessionFactory());
        return regionalService.listRecords();
    }

    public List<Regional> listRecordsByDescription(String description) {
        RegionalService regionalService = new RegionalService(getAbstractSessionFactory());
        return regionalService.listRecordsByDescription(description);
    }

    public Regional listById(Integer id) {
        RegionalService service = new RegionalService(getAbstractSessionFactory());
        return service.listById(id);
    }

    public void edit(Integer id, String name) {
        RegionalService regionalService = new RegionalService(getAbstractSessionFactory());
        regionalService.edit(id, name);
    }

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

    public RegionalScreenConsole getRegionalScreen() {
        return regionalScreen;
    }

    public void setRegionalScreen(RegionalScreenConsole regionalScreen) {
        this.regionalScreen = regionalScreen;
    }

}
