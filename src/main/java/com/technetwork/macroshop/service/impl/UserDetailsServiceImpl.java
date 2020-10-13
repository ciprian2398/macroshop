package com.technetwork.macroshop.service.impl;

import com.technetwork.macroshop.dao.CredentialsDao;
import com.technetwork.macroshop.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final CredentialsDao credentialsDao;

    public UserDetailsServiceImpl(CredentialsDao credentialsDao) {
        this.credentialsDao = credentialsDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        //todo replace optional with proper repository
        Optional<User> optionalCredentials = Optional.ofNullable(credentialsDao.findByLogin(username.toLowerCase()));
        if (optionalCredentials.isPresent()) {
            final User user = optionalCredentials.get();
            return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), getGrantedAuthorities(user));
        } else {
            log.warn("No user found with username=" + username);
            throw new UsernameNotFoundException("No user found");
        }
    }

    private Set<GrantedAuthority> getGrantedAuthorities(User user) {
        final Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getCredentialsRole().toString()));
        return grantedAuthorities;
    }
}