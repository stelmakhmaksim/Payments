package com.epam.lab.payments.mappers;


import com.epam.lab.payments.domain.UserEntity;
import com.epam.lab.payments.dto.UserDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO userToUserDto(UserEntity user);

    List<UserDTO> usersToUsersDto(List<UserEntity> users);

    UserEntity userDtoToUser(UserDTO user);

    List<UserEntity> usersDtoToUsers(List<UserDTO> users);

}
