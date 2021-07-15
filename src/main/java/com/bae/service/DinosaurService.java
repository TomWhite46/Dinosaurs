package com.bae.service;

import java.util.List;

import com.bae.data.Dinosaur;

public interface DinosaurService {

	public Dinosaur createDinosaur(Dinosaur dinosaur);

	public List<Dinosaur> getDinosaurs();

	public Dinosaur getDinosaurById(int id);

	public Dinosaur editDinosaur(int id, int length);

	public Dinosaur replaceDinosaur(int id, Dinosaur dinosaur);

	public String deleteDinosaur(int id);

	public String deleteAll();

	List<Dinosaur> getByGenus(String genus);

	List<Dinosaur> genusContains(String genus);
}
