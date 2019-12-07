package br.ufg.inf.quintacalendario.controller;

import org.hibernate.SessionFactory;

public abstract class AbstractController {

	private SessionFactory abstractSessionFactory;

	/**
	 * Constructor's class
	 */
	AbstractController(SessionFactory sessionFactory) {
		abstractSessionFactory = sessionFactory;
	}

	/**
	 * Returns session factory of category entity
	 *
	 * @return category session factory
	 * @see SessionFactory
	 */
	public SessionFactory getAbstractSessionFactory() {
		return abstractSessionFactory;
	}

	/**
	 * Attribute a session factory to entity
	 *
	 * @param abstractSessionFactory session factory
	 * @see SessionFactory
	 */
	public void setAbstractSessionFactory(SessionFactory abstractSessionFactory) {
		this.abstractSessionFactory = abstractSessionFactory;
	}
}
