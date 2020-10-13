package com.technetwork.macroshop.controller;

import com.technetwork.macroshop.model.enums.CredentialsRole;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/*")
    public String login(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal.getAuthorities().contains(new SimpleGrantedAuthority(CredentialsRole.ROLE_USER.name()))) {
                return "redirect:/userCabinet";
            }
        }
        return "login";
    }

}
