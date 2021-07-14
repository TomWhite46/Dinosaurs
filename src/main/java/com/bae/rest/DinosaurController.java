package com.bae.rest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bae.data.Dinosaur;
import com.bae.service.DinosaurService;

@RestController
public class DinosaurController {

	private DinosaurService service;

	public DinosaurController(DinosaurService service) {
		super();
		this.service = service;
	}

//	@GetMapping("/") // MAPS a GET request to "/" to this method
//	public String hello() {
//		return "<span style=\"color:green;font-size:120px;font-family:'Comic Sans MS';background:red;\">&#129430; <strong><em>Hello</em>, World!</strong> &#129429;</span>";
//	}

	@PostMapping("/create")
	public String createDinosaur(@RequestBody Dinosaur dinosaur) {
		return this.service.createDinosaur(dinosaur);
	}

	@GetMapping("/getall")
	public String getDinosaurs() {
		return this.service.getDinosaurs();
	}

	@GetMapping("/getid/{id}")
	public String getDinosaurById(@PathVariable int id) {
		return this.service.getDinosaurById(id);
	}

	@PatchMapping("/updatelength/{id}")
	public String editDinosaur(@PathVariable int id, @RequestBody int length) {
		return this.service.editDinosaur(id, length);
	}

	@PutMapping("/replace/{id}")
	public String replaceDinosaur(@PathVariable int id, @RequestBody Dinosaur dinosaur) {
		return this.service.replaceDinosaur(id, dinosaur);
	}

	@DeleteMapping("/delete/{id}")
	public String deleteDinosaur(@PathVariable int id) {
		return this.service.deleteDinosaur(id);
	}

	@DeleteMapping("/meteor")
	public String deleteAll() {
		return this.service.deleteAll();
	}

}
