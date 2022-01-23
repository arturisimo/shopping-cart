package edu.cloud.apps.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import edu.cloud.apps.adapters.db.entity.CartEntity;

public interface CartRepository extends JpaRepository<CartEntity, Long> {
}
