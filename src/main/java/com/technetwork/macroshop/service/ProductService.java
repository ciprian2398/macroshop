package com.technetwork.macroshop.service;

import com.technetwork.macroshop.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ProductService {

    Page<Product> findPaginated(PageRequest pageRequest);

    void buy(String username, List<Long> products);

}
