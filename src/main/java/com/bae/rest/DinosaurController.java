package com.bae.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

//	@PostMapping("/create")
//	public Dinosaur createDinosaur(@RequestBody Dinosaur dinosaur) {
//		return this.service.createDinosaur(dinosaur);
//	}

	@PostMapping("/create")
	public ResponseEntity<Dinosaur> createDino(@RequestBody Dinosaur dinosaur) {
		Dinosaur created = this.service.createDinosaur(dinosaur);
		return new ResponseEntity<>(created, HttpStatus.CREATED);
	}

	@GetMapping("/getall")
	public List<Dinosaur> getDinosaurs() {
		return this.service.getDinosaurs();
	}

	@GetMapping("/getid/{id}")
	public Dinosaur getDinosaurById(@PathVariable int id) {
		return this.service.getDinosaurById(id);
	}

	@GetMapping("/getByGenus/{genus}")
	public List<Dinosaur> getByGenus(@PathVariable String genus) {
		return this.service.getByGenus(genus);
	}

	@GetMapping("/genusContains/{genus}")
	public List<Dinosaur> genusContains(@PathVariable String genus) {
		return this.service.genusContains(genus);
	}

	@PatchMapping("/updatelength/{id}")
	public Dinosaur editDinosaur(@PathVariable int id, @RequestBody int length) {
		return this.service.editDinosaur(id, length);
	}

	@PutMapping("/replace/{id}")
	public Dinosaur replaceDinosaur(@PathVariable int id, @RequestBody Dinosaur dinosaur) {
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
