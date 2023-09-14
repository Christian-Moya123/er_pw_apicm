package com.example.demo.repository;

import java.util.List;

import com.example.demo.repository.modelo.Profesor;

public interface IProfesorRepository {
	
	public Profesor buscarPorCedula(String cedula);

	public void insertar(Profesor profesor);

	public void borrar(String cedula);

	public List<Profesor> buscarTodos();
}
