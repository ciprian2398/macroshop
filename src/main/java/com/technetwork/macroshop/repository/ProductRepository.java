package com.technetwork.macroshop.repository;

import com.technetwork.macroshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
