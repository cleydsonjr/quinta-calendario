package br.ufg.inf.quintacalendario.main;

import br.ufg.inf.quintacalendario.model.Evento;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Application {
    public static void main(String[] args) {
        // TODO: Externalizar
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        // Exemplo de uso
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Evento evento = new Evento();
        evento.setId(1L);
        evento.setDescricao("Exemplo de evento.");
        session.save(evento);

        transaction.commit();
        session.close();
    }
}
