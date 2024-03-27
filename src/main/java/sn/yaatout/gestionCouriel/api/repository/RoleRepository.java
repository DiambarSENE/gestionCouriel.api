package sn.yaatout.gestionCouriel.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sn.yaatout.gestionCouriel.api.models.RoleDto;

import java.util.List;

public interface RoleRepository extends JpaRepository<RoleDto,Long> {

    @Query(value = "SELECT * FROM tb_roles r WHERE r.no, = ?1",nativeQuery = true)
    RoleDto findByNom(String nom);

    @Query(value = "SELECT * FROM tb_roles r WHERE r.id = ?1",nativeQuery = true)
    RoleDto findByid(Long id);
    @Query(value = "SELECT * FROM tb_roles r WHERE r.id = ?1",nativeQuery = true)
    List<RoleDto> findAllByid(Integer id);
}
