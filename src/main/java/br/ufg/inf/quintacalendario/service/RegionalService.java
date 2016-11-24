package br.ufg.inf.quintacalendario.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import br.ufg.inf.quintacalendario.model.Regional;
import br.ufg.inf.quintacalendario.repository.RegionalRepository;

public class RegionalService {
	private SessionFactory sessionFactory;
	
	public RegionalService(SessionFactory session) {
		super();
		sessionFactory = session;
	}

	public boolean salvar(Regional regional){
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			
			validarRegional(regional);
			
			new RegionalRepository(session).salvar(regional);
			transaction.commit();
			session.close();
			
			return true;
			
		} catch (Exception e) {
			transaction.rollback();
			session.close();
			return false;
		}
	}
	
	public void validarRegional(Regional regional) throws IllegalArgumentException{
		if (regional.getNome().trim().isEmpty()) {
			throw new IllegalArgumentException("O nome da regional nao pode ser vazio");
		}
		
		if ((regional.getNome().trim().length()) < 4) {
			throw new IllegalArgumentException("O node da regional deve ter no minimo 4 caracteres");
		}
	}
	
	public List<Regional> listar(){
		Session session = sessionFactory.openSession();
		return new RegionalRepository(session).listar();
	}
}
