package com.FirstMicroservices.MicroProject;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForexController {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private ExchangeValueRepository repo;
	
	@GetMapping("/cur-exc/from/{from}/to/{to}")
	public ExchangeValue retrivevalue(@PathVariable String from,
			@PathVariable String to)
	{
		ExchangeValue excvalue = repo.findByFromAndTo(from, to);
		
		excvalue.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		
		return excvalue;
	}
	
	

}
