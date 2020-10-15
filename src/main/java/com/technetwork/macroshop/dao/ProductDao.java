package com.technetwork.macroshop.dao;

import com.technetwork.macroshop.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ProductDao {

    Page<Product> findPaginated(PageRequest pageRequest);

    List<Product> findAllById(List<Long> list);
}
