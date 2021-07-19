package com.bae.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.bae.data.Dinosaur;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

@Sql(scripts = { "classpath:dinosaur-schema.sql",
		"classpath:dinosaur-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)

@AutoConfigureMockMvc

@ActiveProfiles("test")

public class DinosaurControllerIntegrationTest {

	@Autowired
	private MockMvc mockMVC;

	@Autowired
	private ObjectMapper mapper;

	@Test
	void testCreate() throws Exception {
		Dinosaur testDino = new Dinosaur("Diplodocus", 30, false);

		// converts to JSON
		String testDinoAsJSON = this.mapper.writeValueAsString(testDino);

		// build request using JSON string
		RequestBuilder request = post("/create").contentType(MediaType.APPLICATION_JSON).content(testDinoAsJSON);

		// *** build response checkers**

		// for status code
		ResultMatcher checkStatus = status().isCreated();

		// for returned JSON
		Dinosaur testCreatedDino = new Dinosaur(3, "Diplodocus", 30, false); // create test dino
		String testCreatedDinoAsJSON = this.mapper.writeValueAsString(testCreatedDino);
		ResultMatcher checkBody = content().json(testCreatedDinoAsJSON);

		// run test
		this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody);

	}

	@Test
	void testDelete() throws Exception {
		// create request
		RequestBuilder request = delete("/delete/1");

		// check response
		ResultMatcher checkStatus = status().is(204);
		ResultMatcher checkBody = content().string("Deleted: 1");

		// check results
		this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testDeleteAll() throws Exception {
		// create request
		RequestBuilder request = delete("/meteor");

		// check response
		ResultMatcher checkStatus = status().is(204);
		ResultMatcher checkBody = content().string("Your dinosaurs are now extinct.");

		// check results
		this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testGet() throws Exception {
		// create request
		RequestBuilder request = get("/getid/1");

		// create response
		ResultMatcher checkStatus = status().is(200);
		Dinosaur found = new Dinosaur(1, "Tyrannosaurus", 12, true);
		String foundAsJSON = this.mapper.writeValueAsString(found);
		ResultMatcher checkBody = content().json(foundAsJSON);

		// check response
		this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testGenusContains() throws Exception {
		// create request
		RequestBuilder request = get("/genusContains/r");

		// create response
		// code
		ResultMatcher checkStatus = status().is(200);

		// body
		List<Dinosaur> foundDinos = new ArrayList<>();
		Dinosaur found = new Dinosaur(1, "Tyrannosaurus", 12, true);
		Dinosaur found2 = new Dinosaur(2, "Velociraptor", 2, true);
		foundDinos.add(found);
		foundDinos.add(found2);
		String listAsJSON = this.mapper.writeValueAsString(foundDinos);
		ResultMatcher checkBody = content().json(listAsJSON);

		// check response
		this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody);

	}

	@Test
	void testGetAll() throws Exception {
		// create request
		RequestBuilder request = get("/getall");

		// create response
		ResultMatcher checkStatus = status().is(200);

		List<Dinosaur> finalList = new ArrayList<>();
		Dinosaur found = new Dinosaur(1, "Tyrannosaurus", 12, true);
		Dinosaur found2 = new Dinosaur(2, "Velociraptor", 2, true);
		finalList.add(found);
		finalList.add(found2);
		String listAsJSON = this.mapper.writeValueAsString(finalList);
		ResultMatcher checkBody = content().json(listAsJSON);

		// check response
		this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testReplace() throws Exception {
		// Replacement dino
		Dinosaur replacementDino = new Dinosaur("Argentinosaurus", 40, false);
		String replacementDinoJSON = this.mapper.writeValueAsString(replacementDino);

		// create request
		RequestBuilder request = put("/replace/1").contentType(MediaType.APPLICATION_JSON).content(replacementDinoJSON);

		// create response
		// status code
		ResultMatcher checkStatus = status().is(202);

		// body (the replacement as JSON)
		Dinosaur testReplacementDino = new Dinosaur(1, "Argentinosaurus", 40, false); // create test dino
		String testReplacementDinoAsJSON = this.mapper.writeValueAsString(testReplacementDino);
		ResultMatcher checkBody = content().json(testReplacementDinoAsJSON);

		// check response
		this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody);

	}

	@Test
	void testUpdate() throws Exception {
		// create request
		RequestBuilder request = patch("/updatelength/2").contentType(MediaType.APPLICATION_JSON).content("4");

		// create response
		// code
		ResultMatcher checkStatus = status().is(202);
		// body
		Dinosaur updatedDino = new Dinosaur(2, "Velociraptor", 4, true);
		String updatedDinoJSON = this.mapper.writeValueAsString(updatedDino);
		ResultMatcher checkBody = content().json(updatedDinoJSON);

		// check response
		this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody);

	}

}
