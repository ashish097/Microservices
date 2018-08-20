package com.Microservices.CurrencyConversion;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="forex-service")
@RibbonClient(name="forex-service")
public interface CurrencyExProxyService {
	@GetMapping("/cur-exc/from/{from}/to/{to}")
	public CurrencyConversionBean retrivevalue(@PathVariable("from") String from
			,@PathVariable("to") String to);
}
