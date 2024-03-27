package sn.yaatout.gestionCouriel.api.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import sn.yaatout.gestionCouriel.api.exceptions.AppException;
import sn.yaatout.gestionCouriel.api.mappers.RoleMapper;
import sn.yaatout.gestionCouriel.api.models.ErrorDto;
import sn.yaatout.gestionCouriel.api.models.RoleDto;
import sn.yaatout.gestionCouriel.api.repository.RoleRepository;
import sn.yaatout.gestionCouriel.api.services.IRoleService;
import sn.yaatout.gestionJournaux.model.Role;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements IRoleService {

    private final RoleRepository repository;
    private final RoleMapper roleMapper;

    @Override
    public List<Role> getAllRoles() {
        return roleMapper.toListRole(repository.findAll());
    }


    @Override
    public Role updateRole(Role role) {
        RoleDto roleDto = repository.findByid(Long.valueOf(role.getId()));
        if(roleDto == null){
            //new ErrorDto("ROle n'existe pas");
            throw new AppException("ROle n'existe pas", HttpStatus.BAD_REQUEST);
        }else {
            roleDto.setNom(role.getNom());
            roleDto.setDateCreation(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            roleDto.setDescription(role.getDescription());
            roleDto.setId(role.getId());
            repository.save(roleDto);
            return roleMapper.toRole(roleDto);
        }
    }
    @Override
    public List<Role> getAllRoleByUser(Long userId) {
        List<RoleDto> role = repository.findAllByid(Math.toIntExact(userId));
        if (role == null){
            throw new AppException("Il n'existe pas de role dans la base de donnée", HttpStatus.NOT_FOUND);
        }
        return roleMapper.toListRole(role);
    }

    @Override
    public void deleteRole(Long roleId) {
       repository.deleteById(roleId);
    }

    @Override
    public Role addRole(Role role) {
        RoleDto roleDto1 = repository.findByNom(role.getNom());
        if (roleDto1 == null){
            RoleDto roleDto = roleMapper.toRoleDto(role);
            roleDto.setDateCreation(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            repository.save(roleDto);
            return roleMapper.toRole(roleDto);
        }
        throw new AppException("Role éxiste deja", HttpStatus.BAD_REQUEST);
    }

    @Override
    public Role getRoleById(Long roleId) {

        return roleMapper.toRole(repository.findByid(roleId));
    }
}
