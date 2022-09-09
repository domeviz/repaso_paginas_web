package com.uce.edu.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uce.edu.demo.repository.modelo.Persona;
import com.uce.edu.demo.service.IPersonaService;

@Controller
@RequestMapping("/personas")
public class PersonaController {

	@Autowired
	private IPersonaService iPersonaService;

	// A este metodo le decimos que tipo de metodo usamos
	// GET
	@GetMapping("/buscar")
	public String buscarTodos(Model modelo) {
		List<Persona> lista = this.iPersonaService.buscarTodos();
		// le damos un nombre a la entidad que en este caso sera la lista de personas
		// que vamos a mostrar en la vista
		modelo.addAttribute("listapersonas", lista);
		return "VistaListaPersonas";
	}

	@GetMapping("/buscarUno/{idPersona}")
	public String buscarPersona(@PathVariable("idPersona") Integer id, Model modelo) {
		System.out.println("El ID: " + id);
		Persona p = this.iPersonaService.buscarPorId(id);
		modelo.addAttribute("unapersonita", p);
		return "VistaPersona";
	}

	@PutMapping("/actualizar/{idPersona}")
	public String actualizarPersona(@PathVariable("idPersona") Integer id, Persona unapersonita) {
		unapersonita.setId(id);
		this.iPersonaService.actualizar(unapersonita);
		return "redirect:/personas/buscar";
	}

	@DeleteMapping("/borrar/{idPersona}")
	public String borrarPersona(@PathVariable("idPersona") Integer id) {
		this.iPersonaService.eliminar(id);
		return "redirect:/personas/buscar";
	}

	@PostMapping("/insertar")
	public String insertarPersona(Persona personaNueva) {
		this.iPersonaService.guardar(personaNueva);
		return "redirect:/personas/buscar";
	}

	// Metodos de redireccionamiento a paginas
	@GetMapping("/nuevaPersona")
	public String paginaNuevaPersona(Persona personaNueva) {
		return "VistaNuevaPersona";
	}
}
