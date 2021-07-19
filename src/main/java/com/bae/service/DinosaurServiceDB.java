package com.bae.service;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.bae.data.Dinosaur;
import com.bae.data.repos.DinosaurRepo;

@Service
@Primary

public class DinosaurServiceDB implements DinosaurService {

	private DinosaurRepo repo;

	public DinosaurServiceDB(DinosaurRepo repo) {
		super();
		this.repo = repo;
	}

	@Override
	public Dinosaur createDinosaur(Dinosaur dinosaur) {
		return this.repo.save(dinosaur);
	}

	@Override
	public List<Dinosaur> getDinosaurs() {
		return this.repo.findAll();
	}

	@Override
	public Dinosaur getDinosaurById(int id) {
		return this.repo.findById(id).get();
	}

	@Override
	public List<Dinosaur> getByGenus(String genus) {
		return this.repo.findByGenusIgnoreCase(genus);
	}

	@Override
	public List<Dinosaur> genusContains(String genus) {
		return this.repo.findByGenusContaining(genus);
	}

	@Override
	public Dinosaur editDinosaur(int id, int length) {
		Dinosaur found = this.repo.findById(id).get();

		found.setLength(length);

		Dinosaur updated = this.repo.save(found);

		return updated;
	}

	@Override
	public Dinosaur replaceDinosaur(int id, Dinosaur newDinosaur) {
		Dinosaur found = this.repo.findById(id).get();

		found.setGenus(newDinosaur.getGenus());
		found.setLength(newDinosaur.getLength());
		found.setCarnivore(newDinosaur.isCarnivore());

		Dinosaur updated = this.repo.save(found);

		return updated;
	}

	@Override
	public String deleteDinosaur(int id) {
		this.repo.deleteById(id);

		if (this.repo.existsById(id)) {
			return "Not deleted: " + id;
		} else {
			return "Deleted: " + id;
		}

	}

	@Override
	public String deleteAll() {
		this.repo.deleteAll();

		if (this.repo.count() == 0) {
			return "Your dinosaurs are now extinct.";
		} else {
			return "Some dinosaurs survived the meteor!";
		}

	}

}
