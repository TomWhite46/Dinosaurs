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

	@PostMapping("/create")
	public ResponseEntity<Dinosaur> createDino(@RequestBody Dinosaur dinosaur) {
		Dinosaur created = this.service.createDinosaur(dinosaur);
		return new ResponseEntity<>(created, HttpStatus.CREATED);
	}

	@GetMapping("/getall")
	public ResponseEntity<List<Dinosaur>> getDinosaurs() {
		List<Dinosaur> allDinos = this.service.getDinosaurs();
		return new ResponseEntity<>(allDinos, HttpStatus.OK);
	}

	@GetMapping("/getid/{id}")
	public ResponseEntity<Dinosaur> getDinosaurById(@PathVariable int id) {
		Dinosaur found = this.service.getDinosaurById(id);
		return new ResponseEntity<>(found, HttpStatus.OK);
	}

	@GetMapping("/getByGenus/{genus}")
	public List<Dinosaur> getByGenus(@PathVariable String genus) {
		return this.service.getByGenus(genus);
	}

	@GetMapping("/genusContains/{genus}")
	public ResponseEntity<List<Dinosaur>> genusContains(@PathVariable String genus) {
		List<Dinosaur> foundDinos = this.service.genusContains(genus);
		return new ResponseEntity<>(foundDinos, HttpStatus.OK);

	}

	@PatchMapping("/updatelength/{id}")
	public ResponseEntity<Dinosaur> editDinosaur(@PathVariable int id, @RequestBody int length) {
		Dinosaur updated = this.service.editDinosaur(id, length);
		return new ResponseEntity<>(updated, HttpStatus.ACCEPTED);
	}

	@PutMapping("/replace/{id}")
	public ResponseEntity<Dinosaur> replaceDinosaur(@PathVariable int id, @RequestBody Dinosaur dinosaur) {
		Dinosaur replacement = this.service.replaceDinosaur(id, dinosaur);
		return new ResponseEntity<>(replacement, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteDinosaur(@PathVariable int id) {
		String body = this.service.deleteDinosaur(id);
		return new ResponseEntity<>(body, HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/meteor")
	public ResponseEntity<String> deleteAll() {
		String delAll = this.service.deleteAll();
		return new ResponseEntity<>(delAll, HttpStatus.NO_CONTENT);
	}

}
