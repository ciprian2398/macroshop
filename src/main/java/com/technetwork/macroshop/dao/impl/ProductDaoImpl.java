package com.technetwork.macroshop.dao.impl;

import com.technetwork.macroshop.dao.ProductDao;
import com.technetwork.macroshop.model.Product;
import com.technetwork.macroshop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductDaoImpl implements ProductDao {

    private final ProductRepository productRepository;

    @Override
    public Page<Product> findPaginated(PageRequest pageRequest) {
        return productRepository.findAll(pageRequest);
    }

    @Override
    public List<Product> findAllById(List<Long> list) {
        return (List<Product>) productRepository.findAllById(list);
    }
}
