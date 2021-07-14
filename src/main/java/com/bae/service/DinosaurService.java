package com.bae.service;

import com.bae.data.Dinosaur;

public interface DinosaurService {

	public String createDinosaur(Dinosaur dinosaur);

	public String getDinosaurs();

	public String getDinosaurById(int id);

	public String editDinosaur(int id, int length);

	public String replaceDinosaur(int id, Dinosaur dinosaur);

	public String deleteDinosaur(int id);

	public String deleteAll();
}
