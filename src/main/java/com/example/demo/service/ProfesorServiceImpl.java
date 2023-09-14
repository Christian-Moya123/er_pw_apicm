package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.IProfesorRepository;
import com.example.demo.repository.modelo.Profesor;
import com.example.demo.service.to.ProfesorLigero;
import com.example.demo.service.to.ProfesorTO;

@Service
public class ProfesorServiceImpl implements IProfesorService {

	@Autowired
	private IProfesorRepository profesorRepository;

	@Override
	public void guardar(Profesor profesor) {
		this.profesorRepository.insertar(profesor);
	}

	@Override
	public List<ProfesorLigero> buscarTodos() {
		// TODO Auto-generated method stub
		List<Profesor> profs = this.profesorRepository.buscarTodos();
		List<ProfesorLigero> profsLigero = profs.stream().map(p -> this.convertirLigero(p))
				.collect(Collectors.toList());

		return profsLigero;
	}

	@Override
	public void eliminar(String cedula) {
		this.profesorRepository.borrar(cedula);
	}

	@Override
	public ProfesorTO buscar(String cedula) {
		// TODO Auto-generated method stub
		return convertir(this.profesorRepository.buscarPorCedula(cedula));
	}

	private ProfesorLigero convertirLigero(Profesor prof) {
		ProfesorLigero p = new ProfesorLigero();
		p.setNombre(prof.getNombre());
		p.setCedula(prof.getCedula());
		return p;
	}

	private ProfesorTO convertir(Profesor prof) {
		ProfesorTO p = new ProfesorTO();
		p.setId(prof.getId());
		p.setNombre(prof.getNombre());
		p.setApellido(prof.getApellido());
		p.setCedula(prof.getCedula());
		p.setFechaNacimiento(prof.getFechaNacimiento());
		return p;
	}

}
