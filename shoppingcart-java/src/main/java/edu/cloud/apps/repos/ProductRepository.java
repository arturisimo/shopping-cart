package edu.cloud.apps.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.cloud.apps.adapters.db.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
