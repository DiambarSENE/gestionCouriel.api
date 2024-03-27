package sn.yaatout.gestionCouriel.api.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.yaatout.gestionCouriel.api.services.IRoleService;
import sn.yaatout.gestionJournaux.api.RoleApi;
import sn.yaatout.gestionJournaux.api.RolesApi;
import sn.yaatout.gestionJournaux.model.Role;

import javax.validation.Valid;
import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
//@CrossOrigin(origins = "http://178.16.129.206:8081")
//@CrossOrigin(origins = "http://depinfo-dev.tech:8081")
//@CrossOrigin(origins = "http://localhost:8081")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/role")
@Slf4j
@RequiredArgsConstructor
@RestController
public class ControllerRole implements RoleApi, RolesApi {
    private final IRoleService serviceRole;
    @PostMapping("/addRole")
    @Override
    public ResponseEntity<String> createRole(@RequestBody @Valid Role role) {
        serviceRole.addRole(role);
        return new ResponseEntity<>("Role ajouter avec success", HttpStatus.OK);
    }

    @DeleteMapping("/deleteRole/{roleId}")
    @Override
    public ResponseEntity<String> deleteRoleById(@PathVariable Long roleId) {
        serviceRole.deleteRole(roleId);
        return new ResponseEntity<>("Role portant l'identifiant "+ roleId+ " supprimer avec success", HttpStatus.OK);
    }
    @GetMapping("/getRoleById/{idRole}")
    @Override
    public ResponseEntity<Role> getRoleById(@PathVariable Long idRole) {
        return new ResponseEntity<>(serviceRole.getRoleById(idRole), HttpStatus.OK);
    }
    @GetMapping("/getRoleByUser/{userId}")
    @Override
    public ResponseEntity<List<Role>> getRoleByUserId(@PathVariable Long userId) {
        return new ResponseEntity<>(serviceRole.getAllRoleByUser(userId), HttpStatus.OK);
    }
    @PutMapping("/updateRole")
    @Override
    public ResponseEntity<Role> updateRole(@RequestBody @Valid Role role) {
        return new ResponseEntity<>(serviceRole.updateRole(role), HttpStatus.OK);
    }
    @GetMapping("/getAllRoles")
    @Override
    public ResponseEntity<List<Role>> getAllRoles() {
        return new ResponseEntity<>(serviceRole.getAllRoles(), HttpStatus.OK);
    }
}
