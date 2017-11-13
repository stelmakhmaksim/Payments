package com.epam.lab.payments.services;

import com.epam.lab.payments.dao.UserRepository;
import com.epam.lab.payments.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service(value = "userDetailService")
@RequiredArgsConstructor
@Log4j
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(userEmail);

        System.out.println("userEmail '" + userEmail + "'");

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        if (userEntity.isAdmin()) {
            grantedAuthorities.add(new SimpleGrantedAuthority("admin"));
        } else {
            grantedAuthorities.add(new SimpleGrantedAuthority("user"));
        }
        log.info("Load user by user email " + userEmail);
        return new User(userEntity.getEmail(), userEntity.getPassword(), grantedAuthorities);
    }
}
