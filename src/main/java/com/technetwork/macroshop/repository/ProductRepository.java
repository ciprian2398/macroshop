package com.technetwork.macroshop.repository;

import com.technetwork.macroshop.model.Product;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

}
