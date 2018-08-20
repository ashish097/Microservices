package com.Microservices.CurrencyConversion;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CurrencyExProxyService proxy;
	
	@GetMapping("/cur-con/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertvalue(@PathVariable String from
			,@PathVariable String to,@PathVariable BigDecimal quantity)
	{
		Map<String, String> urivar=new HashMap<>();
		urivar.put("from", from);
		urivar.put("to", to);
		
		ResponseEntity<CurrencyConversionBean> responseentity =
				new RestTemplate().getForEntity("http://localhost:8000/cur-exc/from/{from}/to/{to}",
						CurrencyConversionBean.class,urivar);
		
		CurrencyConversionBean response = responseentity.getBody();
		
		return new CurrencyConversionBean(response.getId(),from, to,response.getConversionMultiple()
				,quantity,quantity.multiply(response.getConversionMultiple()),response.getPort());
	}
	
	@GetMapping("/cur-con-feign/from/{from}/to/{to}/quantity/{quantity}")
	  public CurrencyConversionBean convertCurrencyFeign(@PathVariable String from, @PathVariable String to,
	      @PathVariable BigDecimal quantity) {

	    CurrencyConversionBean response = proxy.retrivevalue(from, to);

	    logger.info("{}", response);

	    return new CurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple(), quantity,
	        quantity.multiply(response.getConversionMultiple()), response.getPort());
	  }
	

}
