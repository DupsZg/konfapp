package hr.tvz.dupan.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.junit.Assume.assumeTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import hr.tvz.dupan.dodklase.Predavac;
import hr.tvz.dupan.dodklase.Predavanje;



@ExtendWith(SpringExtension.class) 
@SpringBootTest 
@AutoConfigureMockMvc
class PredavanjaControllerTest {

	private MockMvc mockMvc;
	
	@Autowired 
	PredavanjaControllerTest(MockMvc mockMvc){ 
		this.mockMvc = mockMvc; 
	} 
	
	@Test 
	public void testPrikaziSvaPredavanja() throws Exception{ 
		this.mockMvc 
			.perform(get("/predavanja").with(user("test").password("testpass").roles("USER", "ADMIN"))) 
			.andExpect(status().isOk()) 
			.andExpect(model().attributeExists("svaPredavanja")) 
			.andExpect(view().name("unesenaPredavanja")); 
	}
	
	@Test 
	public void testInvalidPredavanjaSubmit() throws Exception{ 
		this.mockMvc 
			.perform(post("/predavanja/novo")
					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.with(csrf())
					.with(user("test").password("testpass").roles("USER", "ADMIN")))
			.andExpect(status().isOk()) 
			.andExpect(view().name("predloziPredavanje")); 
	}
	
	@Test 
	public void testValidPredavanjaSubmit() throws Exception{
		
		assumeTrue(this.mockMvc != null);
	
		this.mockMvc 
			.perform(post("/predavanja/novo")
					.param("tema", "Test tema")
					.param("sadrzaj", "Kaos kaos kaos kaos kaos kaos kaos kaos kos kaos")
					.param("vrsta", Predavanje.Vrsta.PREZENTACIJA.toString())
					.param("predavac.ime", "tester")
					.param("predavac.pozicija", Predavac.Pozicija.MID.toString())
					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.with(csrf())
					.with(user("test").password("testpass").roles("USER", "ADMIN")))
			.andExpect(status().isOk()) 
			.andExpect(view().name("prihvacenoPredavanje")); 
	}
	

}
