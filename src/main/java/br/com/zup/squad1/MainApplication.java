package br.com.zup.squad1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


//@SpringBootApplication
//@ComponentScan("br.com.zup.squad1")
@SpringBootApplication()
@ComponentScan(basePackages = "br.com.zup.squad1.*")
@EnableJpaRepositories(basePackages = "br.com.zup.squad1.*")
@EntityScan(basePackages = "br.com.zup.squad1.*")
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}