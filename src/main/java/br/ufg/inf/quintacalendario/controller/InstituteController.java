package br.ufg.inf.quintacalendario.controller;

import br.ufg.inf.quintacalendario.main.Application;
import br.ufg.inf.quintacalendario.model.Institute;
import br.ufg.inf.quintacalendario.service.InstitutoService;
import br.ufg.inf.quintacalendario.view.console.InstituteScreenConsole;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Objects;

public class InstituteController {

    private InstituteScreenConsole instituteScreen;
    private SessionFactory sessionFactory;

    public InstituteController() {
        setInstituteScreen(new InstituteScreenConsole(System.out));
        setSessionFactory(Application.getInstance().getSessionFactory());
    }

    void showHisOptions() {
        getInstituteScreen().showOptions();
    }

    public boolean register(String name) {
        Institute institute = new Institute();
        institute.setName(name);

        InstitutoService institutoService = new InstitutoService(getSessionFactory());
        return institutoService.salvar(institute);
    }

    public List<Institute> listRecords() {
        InstitutoService institutoService = new InstitutoService(getSessionFactory());
        return institutoService.listRecords();
    }

    public List<Institute> listRecordsByDescripton(String description) {
        InstitutoService institutoService = new InstitutoService(getSessionFactory());
        return institutoService.listRecordsByDescription(description);
    }

    public Institute listById(Integer id) {
        InstitutoService institutoService = new InstitutoService(getSessionFactory());
        return institutoService.listById(id);
    }

    public void edit(Integer code, String name) {
        InstitutoService institutoService = new InstitutoService(getSessionFactory());
        institutoService.edit(code, name);
    }

    public void remove(Integer id) {
        InstitutoService institutoService = new InstitutoService(getSessionFactory());
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

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
