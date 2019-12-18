package com.taleantacq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "com")
@EntityScan(basePackages = {"com"})
public class TaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaServiceApplication.class, args);
	}

}
