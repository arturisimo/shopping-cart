package edu.cloud.apps;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import edu.cloud.apps.domain.Product;
import edu.cloud.apps.repos.ProductRepository;


@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
    
    @Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
    
    @Bean
	CommandLineRunner run(ProductRepository productRepository){
		return args -> {
			if (productRepository.count() == 0) {
				productRepository.save(new Product("brand",59,"Product 1"));
				productRepository.save(new Product("brand",123,"Product 2"));
				productRepository.save(new Product("brand 1",100,"Product 3"));
				productRepository.save(new Product("brand 2",8,"Product 4"));
			}
		};
	}
    
    
}
