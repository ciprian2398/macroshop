package com.technetwork.macroshop.service.impl;

import com.technetwork.macroshop.dao.ProductDao;
import com.technetwork.macroshop.dao.UserDao;
import com.technetwork.macroshop.model.Product;
import com.technetwork.macroshop.model.User;
import com.technetwork.macroshop.service.ProductService;
import com.technetwork.macroshop.util.NotAllowedToBuyException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.technetwork.macroshop.util.Utils.isValidTransaction;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;
    private final UserDao userDao;

    @Override
    public Page<Product> findPaginated(PageRequest pageRequest) {
        return productDao.findPaginated(pageRequest);
    }

    @Override
    @Transactional
    public void buy(String username, List<Long> productIds) {
        final User buyingUser = userDao.findByLogin(username);
        final List<Product> productList = productDao.findAllById(productIds);
        if (isValidTransaction(buyingUser.getWallet(), productList)) {// todo don't buy from yourself
            updateAndPersist(buyingUser, productList);
        } else {
            throw new NotAllowedToBuyException();
        }
    }

    private void updateAndPersist(User buyingUser, List<Product> products) {
        products.forEach(product -> {
            final User productOwner = product.getOwner();
            product.setStock(product.getStock() - 1);
            buyingUser.setWallet(buyingUser.getWallet() - product.getPrice());
            productOwner.setWallet(productOwner.getWallet() + product.getPrice());
            product.setOwner(buyingUser);
        });
    }
}
