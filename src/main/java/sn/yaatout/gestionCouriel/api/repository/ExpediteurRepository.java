package sn.yaatout.gestionCouriel.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sn.yaatout.gestionCouriel.api.models.ExpediteurDto;

public interface ExpediteurRepository extends JpaRepository<ExpediteurDto, Long> {

    @Query(value = "SELECT * FROM tb_expediteurs c WHERE c.id = ?1",nativeQuery = true)
    ExpediteurDto findByid(Long id);
    @Query(value = "SELECT * FROM tb_expediteurs c WHERE c.nom = ?1",nativeQuery = true)
    ExpediteurDto findByNom(String nom);
}
