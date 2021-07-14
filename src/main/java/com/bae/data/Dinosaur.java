package com.bae.data;

public class Dinosaur {

	private String genus;
	private int length;
	private boolean eatsMeat;

	public Dinosaur(String genus, int length, boolean eatsMeat) {
		super();
		this.genus = genus;
		this.length = length;
		this.eatsMeat = eatsMeat;
	}

	@Override
	public String toString() {
		return genus + ", " + length + "m, " + eatsMeat;
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

	public boolean isEatsMeat() {
		return eatsMeat;
	}

	public void setEatsMeat(boolean eatsMeat) {
		this.eatsMeat = eatsMeat;
	}

}
