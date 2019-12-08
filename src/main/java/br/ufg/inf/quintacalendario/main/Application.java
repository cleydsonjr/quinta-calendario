package br.ufg.inf.quintacalendario.main;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import br.ufg.inf.quintacalendario.view.TelaInicial;
import br.ufg.inf.quintacalendario.view.console.TelaInicialConsole;

import java.util.Objects;

public class Application {

    private static Application applicationInstance;
    private SessionFactory sessionFactory;

    private Application() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public static synchronized Application getInstance() {
        if (Objects.isNull(applicationInstance)) {
            applicationInstance = new Application();
        }

        return applicationInstance;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private void run() {
        TelaInicial startScreen = new TelaInicialConsole(System.out);
        startScreen.exibaCabecalho();
        startScreen.showOptions();
    }

    public static void main(String[] args) {
        Application.getInstance().run();
    }

}
