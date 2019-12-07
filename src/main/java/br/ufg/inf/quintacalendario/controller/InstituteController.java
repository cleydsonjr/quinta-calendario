package br.ufg.inf.quintacalendario.controller;

import br.ufg.inf.quintacalendario.main.Application;
import br.ufg.inf.quintacalendario.model.Institute;
import br.ufg.inf.quintacalendario.service.InstitutoService;
import br.ufg.inf.quintacalendario.view.console.InstituteScreenConsole;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Objects;

/**
 * Controller responsible for intercepting institute entity operations
 *
 * @author Hyago Souza
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

    public boolean register(String name) {
        Institute institute = new Institute();
        institute.setName(name);

        InstitutoService institutoService = new InstitutoService(getAbstractSessionFactory());
        return institutoService.salvar(institute);
    }

    public List<Institute> listRecords() {
        InstitutoService institutoService = new InstitutoService(getAbstractSessionFactory());
        return institutoService.listRecords();
    }

    public List<Institute> listRecordsByDescripton(String description) {
        InstitutoService institutoService = new InstitutoService(getAbstractSessionFactory());
        return institutoService.listRecordsByDescription(description);
    }

    public Institute listById(Integer id) {
        InstitutoService institutoService = new InstitutoService(getAbstractSessionFactory());
        return institutoService.listById(id);
    }

    public void edit(Integer code, String name) {
        InstitutoService institutoService = new InstitutoService(getAbstractSessionFactory());
        institutoService.edit(code, name);
    }

    public void remove(Integer id) {
        InstitutoService institutoService = new InstitutoService(getAbstractSessionFactory());
        Institute institute = institutoService.listById(id);

        if (Objects.isNull(institute)) {
            System.out.println("*******Codigo invalido*******");
            getInstituteScreen().remove();
        } else {
            institutoService.remove(id);
        }
    }

    public InstituteScreenConsole getInstituteScreen() {
        return instituteScreen;
    }

    public void setInstituteScreen(InstituteScreenConsole instituteScreen) {
        this.instituteScreen = instituteScreen;
    }

}
