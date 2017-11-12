package com.epam.lab.payments.mappers;


import com.epam.lab.payments.domain.UserEntity;
import com.epam.lab.payments.dto.UserDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<UserDTO> usersToUsersDto(List<UserEntity> users);

    UserDTO userToUserDto(UserEntity user);
}
