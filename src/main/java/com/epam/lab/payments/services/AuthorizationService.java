package com.epam.lab.payments.services;

import com.epam.lab.payments.dao.UserRepository;
import com.epam.lab.payments.dto.UserDTO;
import com.epam.lab.payments.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorizationService {
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void save(UserDTO userDTO) {
        userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        userRepository.save(userMapper.userDtoToUser(userDTO));
    }

    public UserDTO findUserByEmail(String email) {
        return userMapper.userToUserDto(userRepository.findByEmail(email));
    }
}
