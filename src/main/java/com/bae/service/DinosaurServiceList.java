package com.bae.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bae.data.Dinosaur;

@Service
public class DinosaurServiceList implements DinosaurService {

	public List<Dinosaur> dinosaurs = new ArrayList<>();

//	public DinosaurServiceList() { // just so I don't have to re-post every time
//		super();
//		this.dinosaurs.add(new Dinosaur("Tyrannosaurus", 10, true));
//		this.dinosaurs.add(new Dinosaur("Diplodocus", 30, false));
//		this.dinosaurs.add(new Dinosaur("Velociraptor", 2, true));
//		this.dinosaurs.add(new Dinosaur("Triceratops", 5, false));
//	}

	@Override
	public Dinosaur createDinosaur(Dinosaur dinosaur) {
		dinosaurs.add(dinosaur);
		return dinosaur;
	}

	@Override
	public List<Dinosaur> getDinosaurs() {
		return dinosaurs;
	}

	@Override
	public Dinosaur getDinosaurById(int id) {
		return dinosaurs.get(id);
	}

	@Override
	public Dinosaur editDinosaur(int id, int length) {
		dinosaurs.get(id).setLength(length);
		return dinosaurs.get(id);
	}

	@Override
	public Dinosaur replaceDinosaur(int id, Dinosaur dinosaur) {
		dinosaurs.set(id, dinosaur);
		return (dinosaur);
	}

	@Override
	public String deleteDinosaur(int id) {
		String message = "Deleted dinosaur: " + dinosaurs.get(id);
		dinosaurs.remove(id);
		return message;
	}

	@Override
	public String deleteAll() {
		dinosaurs.clear();
		return "Your dinosaurs are extinct.";
	}

	@Override
	public List<Dinosaur> getByGenus(String genus) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Dinosaur> genusContains(String genus) {
		// TODO Auto-generated method stub
		return null;
	}

}
