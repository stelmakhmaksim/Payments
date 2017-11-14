package com.epam.lab.payments.services;

import com.epam.lab.payments.dao.UserRepository;
import com.epam.lab.payments.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorizationService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void save(UserEntity userEntity) {
        userEntity.setPassword(bCryptPasswordEncoder.encode(userEntity.getPassword()));
        userRepository.save(userEntity);
    }

    public UserEntity findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void update(UserEntity user) {
        UserEntity oldUser = userRepository.findByEmail(user.getEmail());
        if (!user.getPhone().isEmpty()) {
            oldUser.setPhone(user.getPhone());
        }
        if (!user.getPassword().isEmpty()) {
            oldUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        userRepository.save(user);
    }

}
