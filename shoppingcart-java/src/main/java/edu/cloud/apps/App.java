package edu.cloud.apps;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import edu.cloud.apps.adapters.db.entity.ProductEntity;
import edu.cloud.apps.repos.ProductRepository;

@ComponentScan(basePackages = {"edu.cloud.apps.rest", "edu.cloud.apps.config", "edu.cloud.apps.repos", "edu.cloud.apps.adapters.web", "edu.cloud.apps.adapters.db"})
@EntityScan(basePackages = {"edu.cloud.apps.adapters.db.entity"})
@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
    
    @Bean
	CommandLineRunner run(ProductRepository productRepository){
		return args -> {
			if (productRepository.count() == 0) {
				productRepository.save(new ProductEntity("brand",59,"Product 1"));
				productRepository.save(new ProductEntity("brand",123,"Product 2"));
				productRepository.save(new ProductEntity("brand 1",100,"Product 3"));
				productRepository.save(new ProductEntity("brand 2",8,"Product 4"));
			}
		};
	}
    
    
}
