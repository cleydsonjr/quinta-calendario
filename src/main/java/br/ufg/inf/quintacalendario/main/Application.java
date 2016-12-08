package br.ufg.inf.quintacalendario.main;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import br.ufg.inf.quintacalendario.view.TelaInicial;
import br.ufg.inf.quintacalendario.view.console.TelaInicialConsole;

public class Application {

    private static Application applicationInstance;
    private SessionFactory sessionFactory;

    private Application() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public static synchronized Application getInstance() {
        if (applicationInstance == null) {
            applicationInstance = new Application();
        }

        return applicationInstance;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private void run() {
        TelaInicial telaInicial = new TelaInicialConsole(System.out);
        telaInicial.exibaCabecalho();
        telaInicial.exibaOpcoes();
    }

    public static void main(String[] args) {
        Application application = Application.getInstance();
        application.run();
    }

}