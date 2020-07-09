package br.com.nagem.docbrasil;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DocbrasilMergePdfApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DocbrasilMergePdfApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		PdfMerge.processaPdf();
	}

}
