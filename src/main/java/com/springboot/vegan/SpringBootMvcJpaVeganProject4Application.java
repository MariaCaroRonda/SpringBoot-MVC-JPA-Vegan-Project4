package com.springboot.vegan;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootMvcJpaVeganProject4Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMvcJpaVeganProject4Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        /*System.out.println("Examples of Spring Data JPA");*/
        save();
    }

    private void save() {
        System.out.println("Saving a registry in the DB");
    }


}
