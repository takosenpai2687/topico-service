package com.topico.mapper;

import com.topico.entity.User;
import com.topico.dto.UpdateUserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", ignore = true)
    User updateFromDTO(UpdateUserDTO updateUserDTO, @MappingTarget User user);
}