package com.bae.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.bae.data.Dinosaur;
import com.bae.data.repos.DinosaurRepo;

@SpringBootTest
@ActiveProfiles("test")
public class DinosaurServiceDBUnitTest {

	@Autowired
	private DinosaurServiceDB service;

	@MockBean
	private DinosaurRepo repo;

	@Test
	void testReplace() {
		int id = 1;

		Dinosaur testDinosaur = new Dinosaur(id, "Argentinosaurus", 40, false);
		Dinosaur newTestDinosaur = new Dinosaur(id, "Allosaurus", 9, true);

//		test findById: ie  running 'find by ID', input id 1 and return Argentinosaurus as if that were 
		Mockito.when(this.repo.findById(id)).thenReturn(Optional.of(testDinosaur));

//		test save: i.e. as if giving Allosaurus as replacements vals, returns the object given same values
		Mockito.when(this.repo.save(new Dinosaur(id, "Allosaurus", 9, true))).thenReturn(newTestDinosaur);

//		test results - runs 'replaceDinosaur', inputting the id and the replacement object
		Dinosaur actual = this.service.replaceDinosaur(id, newTestDinosaur);

//		check if object produced by test run matches the expected output
		assertThat(actual).isEqualTo(newTestDinosaur);

		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
		Mockito.verify(this.repo, Mockito.times(1)).save(new Dinosaur(id, "Allosaurus", 9, true));
	}

	@Test
	void testDelete() {
		int id1 = 1;
		int id2 = 2;
//		test existsById() - variants for true and false
		Mockito.when(this.repo.existsById(id1)).thenReturn(true);
		Mockito.when(this.repo.existsById(id2)).thenReturn(false);

//		test id1 - should produce false
		String actual1 = this.service.deleteDinosaur(id1);
		assertThat(actual1).isEqualTo("Not deleted: " + id1);

//		test id2 - should produce true
		String actual2 = this.service.deleteDinosaur(id2);
		assertThat(actual2).isEqualTo("Deleted: " + id2);

		Mockito.verify(this.repo, Mockito.times(1)).existsById(id1);
		Mockito.verify(this.repo, Mockito.times(1)).existsById(id2);

	}

	@Test
	void testCreate() {
		int id = 1;
		Dinosaur testDinosaur = new Dinosaur(id, "Allosaurus", 9, false);
//		on test save, return testDinosaur
		Mockito.when(this.repo.save(new Dinosaur(id, "Allosaurus", 9, false))).thenReturn(testDinosaur);

//		input test dino arguments
		Dinosaur actual = this.service.createDinosaur(new Dinosaur(id, "Allosaurus", 9, false));

//		check that this has returned the same as testDinosaur
		assertThat(actual).isEqualTo(testDinosaur);

		Mockito.verify(this.repo, Mockito.times(1)).save(testDinosaur);

	}

	@Test
	void testGetById() {
		int id = 1;
//		Expected outcome:
		Dinosaur testDinosaur = new Dinosaur(1, "Allosaurus", 9, false);

//		on running get with id, return testDinosaur
		Mockito.when(this.repo.findById(id)).thenReturn(Optional.of(testDinosaur));

//		Get actual result using id input
		Dinosaur actual = this.service.getDinosaurById(id);

//		Check if actual result is equal to testDinosaur
		assertThat(actual).isEqualTo(testDinosaur);

		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
	}

	@Test
	void testGetAll() {
		// expected outcome (arraylist)
		List<Dinosaur> testList = new ArrayList<>(
				List.of(new Dinosaur(1, "Allosaurus", 9, true), new Dinosaur(2, "Tyrannosaurus", 12, true)));

		// on running findAll, return the testlist
		Mockito.when(this.repo.findAll()).thenReturn(testList);

		// get actual results
		List<Dinosaur> actualList = this.service.getDinosaurs();

		// check if equal
		assertThat(actualList).isEqualTo(testList);

		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}

	@Test
	void testDeleteAll() {
		// expected outcome
		long testNo1 = 4;
//		String expected = "Your dinosaurs are now extinct.";

		// test count
		Mockito.when(this.repo.count()).thenReturn(testNo1);

		// get actual
		assertThat(this.service.deleteAll()).isEqualTo("Some dinosaurs survived the meteor!");

	}

}
