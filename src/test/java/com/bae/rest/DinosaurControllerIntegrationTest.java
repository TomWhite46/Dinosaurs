package com.bae.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.bae.data.Dinosaur;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class DinosaurControllerIntegrationTest {

	@Autowired
	private MockMvc mockMVC;

	@Autowired
	private ObjectMapper mapper;

	@Test
	void testCreate() throws Exception {
		Dinosaur testDino = new Dinosaur("Tyrannosaurus", 12, true);

		// converts to JSON
		String testDinoAsJSON = this.mapper.writeValueAsString(testDino);

		// build request using JSON string
		RequestBuilder request = post("/create").contentType(MediaType.APPLICATION_JSON).content(testDinoAsJSON);

		// *** build response checkers**

		// for status code
		ResultMatcher checkStatus = status().isCreated();

//		// for returned JSON
//		Dinosaur testCreatedDino = new Dinosaur("Tyrannosaurus", 12, true); // create test dino
//		testCreatedDino.setId(1); // sets to 1 to match auto-increment
//		String testCreatedDinoAsJSON = this.mapper.writeValueAsString(testCreatedDino);
//		ResultMatcher checkBody = content().json(testCreatedDinoAsJSON);

		testDino.setId(1); // sets to 1 to match auto-increment
		testDinoAsJSON = this.mapper.writeValueAsString(testDino);
		ResultMatcher checkBody = content().json(testDinoAsJSON);

		// run test
		this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody);

	}

}
