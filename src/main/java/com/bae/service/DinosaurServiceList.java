package com.bae.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bae.data.Dinosaur;

@Service
public class DinosaurServiceList implements DinosaurService {

	public List<Dinosaur> dinosaurs = new ArrayList<>();

	public DinosaurServiceList() { // just so I don't have to re-post every time
		super();
		this.dinosaurs.add(new Dinosaur("Tyrannosaurus", 10, true));
		this.dinosaurs.add(new Dinosaur("Diplodocus", 30, false));
		this.dinosaurs.add(new Dinosaur("Velociraptor", 2, true));
		this.dinosaurs.add(new Dinosaur("Triceratops", 5, false));
	}

	@Override
	public String createDinosaur(Dinosaur dinosaur) {
		dinosaurs.add(dinosaur);
		System.out.println("New dinosaur added: " + dinosaur);
		return "New dinosaur added: " + dinosaur;
	}

	@Override
	public String getDinosaurs() {
		String finalStr = "";

		for (Dinosaur dinosaur : dinosaurs) {
			finalStr = finalStr + "\n" + dinosaur;
		}
		return "<table style=\"text-align:center;font-size:30;font-family:'Comic Sans MS';color:yellow;background-color:red\"><tr><th>Genus</th><th>Length(m)</th><th>Eats meat</th>"
				+ finalStr + "</table>";
	}

	@Override
	public String getDinosaurById(int id) {
		return "Your chosen dinosaur is:" + dinosaurs.get(id) + "!";
	}

	@Override
	public String editDinosaur(int id, int length) {
		dinosaurs.get(id).setLength(length);
		return dinosaurs.get(id).getGenus() + "'s length changed to " + length;
	}

	@Override
	public String replaceDinosaur(int id, Dinosaur dinosaur) {
		dinosaurs.set(id, dinosaur);
		return "Dinosaur " + id + " has evolved into " + dinosaur;
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
}
