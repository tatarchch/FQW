package com.example.fqw.mapper;

import com.example.fqw.dto.AdminDto;
import com.example.fqw.entity.Admin;
import org.mapstruct.Mapper;

@Mapper
public interface AdminMapper {

    Admin toEntity(AdminDto adminDto);

    AdminDto toDTO(Admin admin);
}
