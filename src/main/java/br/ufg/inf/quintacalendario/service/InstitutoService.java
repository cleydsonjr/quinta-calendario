package br.ufg.inf.quintacalendario.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import br.ufg.inf.quintacalendario.model.Instituto;
import br.ufg.inf.quintacalendario.repository.InstitutoRepository;

public class InstitutoService {
	private SessionFactory sessionFactory;
	
	public InstitutoService(SessionFactory session) {
		super();
		sessionFactory = session;
	}

	public boolean salvar(Instituto instituto){
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			validarInstituto(instituto);
	        
	        new InstitutoRepository(session).salvar(instituto);
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
	
	private void validarInstituto(Instituto instituto) {
		if (instituto.getNome().trim().isEmpty()) {
			throw new IllegalArgumentException("O nome do instituto não pode ser vazio");
		}
		
		if ((instituto.getNome().trim().length()) < 4) {
			throw new IllegalArgumentException("O node do instituto deve ter no minimo 4 caracteres");
		}
	}

	public List<Instituto> listar(){
		Session session = sessionFactory.openSession();
		return new InstitutoRepository(session).listar();
	}
}
