package br.ufg.inf.quintacalendario.controller;

import br.ufg.inf.quintacalendario.main.Application;
import br.ufg.inf.quintacalendario.model.Category;
import br.ufg.inf.quintacalendario.model.Event;
import br.ufg.inf.quintacalendario.model.Institute;
import br.ufg.inf.quintacalendario.model.Regional;
import br.ufg.inf.quintacalendario.service.CategoryService;
import br.ufg.inf.quintacalendario.service.EventService;
import br.ufg.inf.quintacalendario.service.InstitutoService;
import br.ufg.inf.quintacalendario.service.RegionalService;
import br.ufg.inf.quintacalendario.view.console.EventScreenConsole;
import org.hibernate.SessionFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class EventController {

    private EventScreenConsole eventScreen;
    private SessionFactory sessionFactory;

    public EventController() {
        setEventScreen(new EventScreenConsole(System.out));
        setSessionFactory(Application.getInstance().getSessionFactory());
    }

    public boolean register(String description,
                            String title,
                            String initialDate,
                            String finalDate,
                            Integer categoryId,
                            Integer regionalId,
                            Integer instituteId
    ) {

        try {
            Event event = new Event();

            event.setDescription(description);
            event.setTitle(title);

            Date data = from(initialDate);
            event.setInitialDate(data);

            data = from(finalDate);
            event.setFinalDate(data);

            event.setCategory(new CategoryService(getSessionFactory()).listById(categoryId));

            List<Institute> institutes = new ArrayList<>();
            institutes.add(new InstitutoService(getSessionFactory()).listById(instituteId));

            event.setInstitutes(institutes);

            List<Regional> regionals = new ArrayList<>();
            regionals.add(new RegionalService(getSessionFactory()).listById(regionalId));

            event.setRegionais(regionals);

            EventService eventService = new EventService(getSessionFactory());
            eventService.save(event);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    void showHisOptions() {
        getEventScreen().showOptions();
    }

    private Date from(String date) {
        if (Objects.isNull(date) || date.isEmpty()) {
            return null;
        }

        Date newDate = null;
        try {
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            newDate = formatter.parse(date);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return newDate;
    }

    public List<Event> listRecords() {
        EventService eventService = new EventService(getSessionFactory());
        return eventService.listRecords();
    }

    public List<Event> listRecordsByDescription(String description) {
        EventService eventService = new EventService(getSessionFactory());
        return eventService.listRecordsByDescription(description);
    }

    public Event listById(Integer id) {
        EventService eventService = new EventService(getSessionFactory());
        return eventService.listById(id);
    }

    public List<Event> listByPeriod(String initialDate, String finalDate) {
        EventService eventService = new EventService(getSessionFactory());
        return eventService.listarEventosPorPeriodo(from(initialDate), from(finalDate));
    }

    public List<Regional> listRegionals() {
        RegionalService regionalService = new RegionalService(getSessionFactory());
        return regionalService.listRecords();
    }

    public List<Institute> listInstitutes() {
        InstitutoService institutoService = new InstitutoService(getSessionFactory());
        return institutoService.listRecords();
    }

    public List<Category> listCategories() {
        CategoryService categoryService = new CategoryService(getSessionFactory());
        return categoryService.listRecords();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private EventScreenConsole getEventScreen() {
        return eventScreen;
    }

    private void setEventScreen(EventScreenConsole eventScreen) {
        this.eventScreen = eventScreen;
    }
}
