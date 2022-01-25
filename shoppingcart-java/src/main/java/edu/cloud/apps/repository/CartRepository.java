package edu.cloud.apps.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.cloud.apps.adapters.db.entity.CartEntity;
	
public interface CartRepository extends JpaRepository<CartEntity, Long> {
}
