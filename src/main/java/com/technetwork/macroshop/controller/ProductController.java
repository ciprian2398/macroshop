package com.technetwork.macroshop.controller;

import com.technetwork.macroshop.model.Product;
import com.technetwork.macroshop.service.ProductService;
import com.technetwork.macroshop.util.NotAllowedToBuyException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

import static com.technetwork.macroshop.util.Utils.csvNumbersToLongList;
import static com.technetwork.macroshop.util.Utils.generateRange;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping(value = "/userCabinet")
    public String listProducts(Model model,
                               @RequestParam("page") Optional<Integer> page,
                               @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<Product> productPage = productService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("productPage", productPage);

        int totalPages = productPage.getTotalPages();
        if (totalPages > 0) {
            model.addAttribute("pageNumbers", generateRange(totalPages));
        }

        return "userCabinet";
    }

    @PostMapping(value = "/userCabinet")
    public String buy(Authentication authentication, @RequestParam("selected") String selected) {
        if (authentication != null && authentication.isAuthenticated()) {
            User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            try {
                productService.buy(principal.getUsername(), csvNumbersToLongList(selected));
            } catch (NotAllowedToBuyException e) {
                return "redirect:/userCabinet?failed";
            }
        }
        return "redirect:/userCabinet?success";
    }
}