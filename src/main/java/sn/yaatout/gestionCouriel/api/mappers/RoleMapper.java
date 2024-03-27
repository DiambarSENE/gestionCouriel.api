package sn.yaatout.gestionCouriel.api.mappers;

import org.mapstruct.Mapper;
import sn.yaatout.gestionCouriel.api.models.RoleDto;
import sn.yaatout.gestionJournaux.model.Role;


import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDto toRoleDto(Role role);
    List<RoleDto> toListRoleDto(List<Role> list);

    Role toRole(RoleDto roleDto);

    List<Role> toListRole(List<RoleDto> list);
}
