package com.technetwork.macroshop.service.impl;

import com.technetwork.macroshop.dao.ProductDao;
import com.technetwork.macroshop.model.Product;
import com.technetwork.macroshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;

    @Override
    public Page<Product> findPaginated(PageRequest pageRequest) {
        return productDao.findPaginated(pageRequest);
    }
}
