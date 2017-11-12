package com.epam.lab.payments.adapters;

import com.epam.lab.payments.domain.UserEntity;
import com.epam.lab.payments.dto.UserDTO;
import com.epam.lab.payments.services.PaymentsService;
import com.epam.lab.payments.web.PaymentsController;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter {
    @Transactional(readOnly = true)
    public List<UserDTO> getAllUserAsUserDTO() {
        List<UserDTO> userDTOs = new ArrayList<>();

        for (UserEntity user : new PaymentsService().findAllUsers()) {
            userDTOs.add(constructUserDTO(user));
        }

        return userDTOs;
    }

    private UserDTO constructUserDTO(UserEntity user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhone(user.getPhone());
        userDTO.setAdmin(user.isAdmin());
        return userDTO;
    }
}
