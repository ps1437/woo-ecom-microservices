package com.syscho.wocom.users.mapper;

import com.syscho.wocom.users.dto.UserDTO;
import com.syscho.wocom.users.repo.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", source = "entity.id")
    UserDTO toUserDTO(UserEntity entity);

    @Mapping(target = "id", ignore = true)
    UserEntity toUserEntity(UserDTO dto);

    List<UserDTO> toUserDTOList(List<UserEntity> entities);
}
