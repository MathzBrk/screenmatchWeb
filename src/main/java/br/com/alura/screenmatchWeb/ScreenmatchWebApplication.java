package br.com.alura.screenmatchWeb;

import br.com.alura.screenmatchWeb.main.Main;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchWebApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchWebApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main();
		main.exibirMenu();




	}
}
