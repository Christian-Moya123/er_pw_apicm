package com.example.demo.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.repository.modelo.Profesor;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class ProfesorRepositoryImpl implements IProfesorRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Profesor buscarPorCedula(String cedula) {
		TypedQuery<Profesor> myQuery = this.entityManager
				.createQuery("SELECT p FROM Profesor p WHERE p.cedula = :datoCedula", Profesor.class);
		myQuery.setParameter("datoCedula", cedula);
		return myQuery.getSingleResult();
	}

	@Override
	public void insertar(Profesor profesor) {
		this.entityManager.persist(profesor);
	}

	@Override
	public void borrar(String cedula) {
		this.entityManager.remove(this.buscarPorCedula(cedula));
	}

	@Override
	public List<Profesor> buscarTodos() {
		TypedQuery<Profesor> myQuery = this.entityManager.createQuery("SELECT p FROM Profesor p ",
				Profesor.class);
		return myQuery.getResultList();
	}

}
