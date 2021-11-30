package br.com.magicstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class MagicStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(MagicStoreApplication.class, args);
	}

}
