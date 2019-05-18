package com.fonix.FonixFlightOffers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fonix.FonixFlightOffers.model.UserSubscription;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FonixFlightOffersApplicationTests 
{

	@Autowired
	private MockMvc mvc;
	
	@Test
	public void contextLoads() throws Exception {
		
		UserSubscription userSubscription = new UserSubscription("test@gmail.com", "LDN", "ADI", "Daily");
		mvc.perform(MockMvcRequestBuilders.post("/subscribe")
				  .content(asJsonString(userSubscription))
				  .contentType(MediaType.APPLICATION_JSON)
				  .accept(MediaType.APPLICATION_JSON))
      		  .andExpect(status().isOk());
	}

	public static String asJsonString(final Object obj) {
	    try {
	        final ObjectMapper mapper = new ObjectMapper();
	        final String jsonContent = mapper.writeValueAsString(obj);
	        return jsonContent;
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	} 
}
