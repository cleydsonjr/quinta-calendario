package br.ufg.inf.quintacalendario.main;

import br.ufg.inf.quintacalendario.model.Evento;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

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
        // TODO: Chamar controlador principal

        // TODO Remover: Exemplo de uso da persistencia
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Evento evento = new Evento();
        
        session.save(evento);

        transaction.commit();
        session.close();
    }

    public static void main(String[] args) {
        Application application = Application.getInstance();
        application.run();
    }

}
