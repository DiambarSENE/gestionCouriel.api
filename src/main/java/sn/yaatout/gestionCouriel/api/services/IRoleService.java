package sn.yaatout.gestionCouriel.api.services;

import sn.yaatout.gestionJournaux.model.Role;

import java.util.List;

public interface IRoleService {
    List<Role> getAllRoles();

    Role updateRole(Role role);

    List<Role> getAllRoleByUser(Long userId);

    void deleteRole(Long roleId);

    Role addRole(Role role);

    Role getRoleById(Long roleId);
}
