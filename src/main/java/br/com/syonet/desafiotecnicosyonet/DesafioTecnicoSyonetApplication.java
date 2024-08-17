package br.com.syonet.desafiotecnicosyonet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DesafioTecnicoSyonetApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioTecnicoSyonetApplication.class, args);
	}

}
