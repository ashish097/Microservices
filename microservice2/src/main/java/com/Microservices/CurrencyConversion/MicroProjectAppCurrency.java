package com.Microservices.CurrencyConversion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients("com.Microservices.CurrencyConversion")
public class MicroProjectAppCurrency {

	public static void main(String[] args) {
		SpringApplication.run(MicroProjectAppCurrency.class, args);
	}
}
