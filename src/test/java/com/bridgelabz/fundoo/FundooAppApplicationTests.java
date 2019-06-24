package com.bridgelabz.fundoo;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.bridgelabz.fundoo.user.dto.LoginDto;
import com.bridgelabz.fundoo.user.dto.UserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FundooAppApplicationTests {
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext wac;
	
	
	@InjectMocks
	private UserDTO userDto;
	
	@Before
	public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
	
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	
@Test
public void regsiter() throws Exception {

//userDto.setName("rohan kadam");
//userDto.setEmailId("rohankadam965@gmail.com");
//userDto.setPassword("Rohan@2589");
//userDto.setPhNumber("7894561230");
mockMvc.perform( MockMvcRequestBuilders
  .post("/user/registration")
//  .content(asJsonString(userDto))
  .content(asJsonString(new UserDTO("rohan kadam","rohankadam@gmail.com","Rohan@2589","8280946115")))
  .contentType(MediaType.APPLICATION_JSON)
  .accept(MediaType.APPLICATION_JSON))
	  .andDo(print())
.andExpect(status().isOk());
} 

@Test
public void login() throws Exception{
	mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
			.content(asJsonString(new LoginDto("rohanKadam@gmail.com","Rohan@07")))
				      .contentType(MediaType.APPLICATION_JSON)
				      .accept(MediaType.APPLICATION_JSON))
				  	  .andDo(print())
			.andExpect(status().isOk());
}

@Test
public void forgot() throws Exception{
	mockMvc.perform(MockMvcRequestBuilders.post("/user/forgotPassword")
			.content(asJsonString(new LoginDto("rohanKadam@gmail.com","Rohan@074")))
				      .contentType(MediaType.APPLICATION_JSON)
				      .accept(MediaType.APPLICATION_JSON))
				  	  .andDo(print())
			.andExpect(status().isOk());
}



}
