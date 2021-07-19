package com.bae.data;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Dinosaur {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String genus;
	private int length;
	private boolean carnivore;

	public Dinosaur(int id, String genus, int length, boolean carnivore) {
		super();
		this.id = id;
		this.genus = genus;
		this.length = length;
		this.carnivore = carnivore;
	}

	public Dinosaur(String genus, int length, boolean carnivore) {
		super();
		this.genus = genus;
		this.length = length;
		this.carnivore = carnivore;
	}

	public Dinosaur() {

	}

	@Override
	public String toString() {
		return genus + ", " + length + "m, " + carnivore;
	}

	@Override
	public int hashCode() {
		return Objects.hash(carnivore, genus, id, length);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dinosaur other = (Dinosaur) obj;
		return carnivore == other.carnivore && Objects.equals(genus, other.genus) && id == other.id
				&& length == other.length;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getGenus() {
		return genus;
	}

	public void setGenus(String genus) {
		this.genus = genus;
	}

	public boolean isCarnivore() {
		return carnivore;
	}

	public void setCarnivore(boolean carnivore) {
		this.carnivore = carnivore;
	}

}
