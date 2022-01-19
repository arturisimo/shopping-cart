package edu.cloud.apps.repos;

import edu.cloud.apps.domain.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
}
