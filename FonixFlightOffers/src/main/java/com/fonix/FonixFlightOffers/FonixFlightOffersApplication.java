package com.fonix.FonixFlightOffers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class FonixFlightOffersApplication {

	public static void main(String[] args) {
		SpringApplication.run(FonixFlightOffersApplication.class, args);
	}

}
