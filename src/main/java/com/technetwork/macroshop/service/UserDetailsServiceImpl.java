package com.technetwork.macroshop.service;

import com.technetwork.macroshop.dao.CredentialsDao;
import com.technetwork.macroshop.model.Credentials;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
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
        Optional<Credentials> optionalCredentials = Optional.ofNullable(credentialsDao.findByUsername(username.toLowerCase()));
        if (optionalCredentials.isPresent()) {
            final Credentials credentials = optionalCredentials.get();
            return new User(credentials.getUsername(), credentials.getPassword(), getGrantedAuthorities(credentials));
        } else {
            log.warn("No user found with username=" + username);
            throw new UsernameNotFoundException("No user found");
        }
    }

    private Set<GrantedAuthority> getGrantedAuthorities(Credentials credentials) {
        final Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(credentials.getCredentialsRole().toString()));
        return grantedAuthorities;
    }
}
