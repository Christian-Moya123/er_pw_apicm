package com.example.demo.service;

import java.util.List;

import com.example.demo.repository.modelo.Profesor;
import com.example.demo.service.to.ProfesorLigero;
import com.example.demo.service.to.ProfesorTO;

public interface IProfesorService {
	public void guardar(Profesor profesor);
	public List<ProfesorLigero> buscarTodos();
	public ProfesorTO buscar(String cedula);
	public void eliminar(String cedula);

}
