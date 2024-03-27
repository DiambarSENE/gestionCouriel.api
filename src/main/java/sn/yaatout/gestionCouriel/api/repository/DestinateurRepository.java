package sn.yaatout.gestionCouriel.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sn.yaatout.gestionCouriel.api.models.DestinateurDto;

public interface DestinateurRepository extends JpaRepository<DestinateurDto, Long> {

    @Query(value = "SELECT * FROM tb_destinateurs c WHERE c.id = ?1",nativeQuery = true)
    DestinateurDto findByid(Long id);
    @Query(value = "SELECT * FROM tb_destinateurs c WHERE c.nom = ?1",nativeQuery = true)
    DestinateurDto findByNom(String nom);
}
