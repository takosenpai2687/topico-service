package com.topico.mapper;

import com.topico.dto.UpdateUserDto;
import com.topico.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User updateFromDTO(UpdateUserDto updateUserDTO, @MappingTarget User user);
}