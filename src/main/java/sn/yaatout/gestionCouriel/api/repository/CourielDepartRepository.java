package sn.yaatout.gestionCouriel.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sn.yaatout.gestionCouriel.api.models.CourielDepartDto;


import java.util.List;

public interface CourielDepartRepository extends JpaRepository<CourielDepartDto, Long> {
    @Query(value = "SELECT * FROM tb_couriel_depart c WHERE c.user_id = ?1",nativeQuery = true)
    List<CourielDepartDto> findAllByid(Long id);
    @Query(value = "SELECT * FROM tb_couriel_depart c WHERE c.id = ?1",nativeQuery = true)
    CourielDepartDto findByid(Long id);
    //@Query(value = "SELECT * FROM tb_couriel_depart c WHERE DATE(c.dateNow) = CURDATE()", nativeQuery = true)
    @Query(value = "SELECT * FROM tb_couriel_depart c WHERE c.date_du_depart = CURDATE() OR c.verifier = false", nativeQuery = true)
    List<CourielDepartDto> findCourrielsToday();

    @Query(value = "SELECT * FROM tb_couriel_depart c WHERE c.date_du_depart != CURDATE() AND c.verifier = true", nativeQuery = true)
    List<CourielDepartDto> findCourrielsNotToday();



}
