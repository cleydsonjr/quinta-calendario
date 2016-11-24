package br.ufg.inf.quintacalendario.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import br.ufg.inf.quintacalendario.model.Evento;
import br.ufg.inf.quintacalendario.repository.EventoRepository;

public class EventoService {
	
	private SessionFactory sessionFactory;
	
	public EventoService(SessionFactory sessionFactory) {
		super();
		this.setSessionFactory(sessionFactory);
	}

	public boolean salvar(Evento evento){
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			validarEvento(evento);
			
			new EventoRepository(session).salvar(evento);
			transaction.commit();
			session.close();
			
			return true;
		} catch (Exception e) {
			transaction.rollback();
			session.close();
			System.out.println(e.getMessage());
			return false;
		}
	}

	private void validarEvento(Evento evento) {
		// TODO Criar validacoes de evento
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
