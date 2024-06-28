package io.lectures;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LecturesApplication {

	public static void main(String[] args) {
		SpringApplication.run(LecturesApplication.class, args);
	}


}
