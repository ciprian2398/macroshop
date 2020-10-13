package com.technetwork.macroshop.service;

import com.technetwork.macroshop.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface ProductService {

    Page<Product> findPaginated(PageRequest pageRequest);

}
