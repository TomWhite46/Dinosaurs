package com.bae.data.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bae.data.Dinosaur;

public interface DinosaurRepo extends JpaRepository<Dinosaur, Integer> {

	List<Dinosaur> findByGenusIgnoreCase(String genus);

	List<Dinosaur> findByGenusContaining(String genus);
}
