package com.example.demo.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.modelo.Profesor;
import com.example.demo.service.IProfesorService;
import com.example.demo.service.to.ProfesorLigero;
import com.example.demo.service.to.ProfesorTO;

@RestController
@RequestMapping("/profesores")
@CrossOrigin
public class ProfesorControllerRestFul {

	@Autowired
	private IProfesorService profesorService;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void insertarEstudiante(@RequestBody Profesor profesor) {
		this.profesorService.guardar(profesor);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProfesorLigero>> consultarTodos() {
		List<ProfesorLigero> lista = this.profesorService.buscarTodos();
		for (ProfesorLigero p : lista) {
			Link myLink = linkTo(methodOn(ProfesorControllerRestFul.class).buscarPorCedula(p.getCedula()))
					.withSelfRel();
			p.add(myLink);
		}
		return new ResponseEntity<>(lista, new HttpHeaders(), 200);
	}

	@GetMapping(path = "/{cedula}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProfesorTO> buscarPorCedula(@PathVariable String cedula) {
		return new ResponseEntity<>(this.profesorService.buscar(cedula), null, 200);
	}

	@DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/{cedula}")
	public void borrar(@PathVariable String cedula) {
		this.profesorService.eliminar(cedula);
	}

}
