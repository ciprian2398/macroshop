package com.technetwork.macroshop.service.impl;

import com.technetwork.macroshop.model.Product;
import com.technetwork.macroshop.repository.ProductRepository;
import com.technetwork.macroshop.service.ProductService;
import com.technetwork.macroshop.util.NotAllowedToBuyException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class ProductServiceImplTest {

    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    @Test
    void findPaginated() {
        Page<Product> productList = productService.findPaginated(PageRequest.of(1, 5));
        assertThat(productList.getContent().size()).isEqualTo(5);
        assertThat(productList.getContent().get(0).getOwner().getLogin()).isEqualTo("Andrei");
    }

    @Test
    void filedToBuyHisOwnProduct() {
        assertThrows(NotAllowedToBuyException.class,
                () -> productService.buy("Andrei", singletonList(1L)));
    }

    @Test
    void filedBuyingForeignProductInsufficientMoney() {
        assertThrows(NotAllowedToBuyException.class,
                () -> productService.buy("Andrei", asList(11L, 12L)));
    }

    @Test
    void succeedBuyingForeignProduct() {
        List<Product> initialList = (List<Product>) productRepository.findAll();
        Optional<Product> initialProduct = productRepository.findById(11L);
        Double initialTotalWalletMoney = initialList.stream().mapToDouble(Product::getPrice).sum();
        Double initialAndreiWalletMoney = initialList.stream()
                .filter(product -> product.getOwner().getLogin().equals("Andrei"))
                .mapToDouble(Product::getPrice).sum();

        assertThat(initialList.stream()
                .filter(product -> product.getOwner().getLogin().equals("Andrei"))
                .count()).isEqualTo(10);

        productService.buy("Andrei", singletonList(11L));
        // test results

        List<Product> modifiedList = (List<Product>) productRepository.findAll();
        Optional<Product> modifiedProduct = productRepository.findById(11L);
        Double modifiedTotalWalletMoney = modifiedList.stream().mapToDouble(Product::getPrice).sum();

        Double finalAndreiWalletMoney = modifiedList.stream()
                .filter(product -> product.getOwner().getLogin().equals("Andrei"))
                .mapToDouble(Product::getPrice).sum();

        assertThat(finalAndreiWalletMoney - 30008).isEqualTo(initialAndreiWalletMoney);

        assertThat(modifiedList.stream()
                .filter(product -> product.getOwner().getLogin().equals("Andrei"))
                .count()).isEqualTo(11);

        assertThat(initialTotalWalletMoney).isEqualTo(modifiedTotalWalletMoney);

        assertThat(modifiedProduct.get().getStock()).isEqualTo(initialProduct.get().getStock() - 1);

    }

}