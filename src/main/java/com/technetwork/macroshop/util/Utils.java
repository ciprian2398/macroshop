package com.technetwork.macroshop.util;

import com.technetwork.macroshop.model.Product;
import com.technetwork.macroshop.model.User;
import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

@UtilityClass
public class Utils {

    //todo why can't static import only with @UtilityClass but still need static on declaration?
    public static List<Long> csvNumbersToLongList(String csvNumbers) {
        return Arrays.stream(csvNumbers.split(","))
                .map(Long::parseLong)
                .collect(toList());
    }

    public static boolean isValidTransaction(User buyingUser, List<Product> products) {
        boolean availableStock = products.stream()
                .allMatch(product -> product.getStock() > 0);

        boolean enoughMoney = (Double) products.stream()
                .mapToDouble(Product::getPrice).sum() <= buyingUser.getWallet();

        boolean notBuyingHisProducts = products.stream()
                .noneMatch(product -> product.getOwner().equals(buyingUser));

        return availableStock && enoughMoney && notBuyingHisProducts;
    }

    public static List<Integer> generateRange(int totalPages) {
        return IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(toList());
    }

}
