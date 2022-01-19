package edu.cloud.apps.repos;

import edu.cloud.apps.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {
}
