package com.technetwork.macroshop.dao.impl;

import com.technetwork.macroshop.dao.ProductDao;
import com.technetwork.macroshop.model.Product;
import com.technetwork.macroshop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductDaoImpl implements ProductDao {

    private final ProductRepository productRepository;

    @Override
    public Page<Product> findPaginated(PageRequest pageRequest) {
        return productRepository.findAll(pageRequest);
    }
}
