package com.bridgelabz.fundoo.configuartion;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bridgelabz.fundoo.response.Response;



/**
 * @author Rohan Kadam
 *Purpose: To provide Configuration class along with  Bean
 */
@Configuration
//@EnableSwagger2
public class ApplicationConfig {
	
	
	/**
	 * Purpose:Model Mapper Bean 
	 * @return modelmapper
	 */
	@Bean
	ModelMapper getModelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper;
	}

	/**
	 * Purpose:   Custom Response
	 * @return Response
	 */
	@Bean
	public Response getResponse() {
		return new Response();
	}
	
	

	/**
	 * Purpose: Password Encoding Bean
	 * @return BCryptPasswordEncoder
	 */
	@Bean
	public PasswordEncoder getpasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
