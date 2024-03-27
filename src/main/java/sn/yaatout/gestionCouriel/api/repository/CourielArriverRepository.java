package sn.yaatout.gestionCouriel.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sn.yaatout.gestionCouriel.api.models.CourielArriverDto;


import java.util.List;

public interface CourielArriverRepository extends JpaRepository<CourielArriverDto, Long> {
    @Query(value = "SELECT * FROM tb_couriel_arriver c WHERE c.user_id = ?1",nativeQuery = true)
    List<CourielArriverDto> findAllByid(Long id);
    @Query(value = "SELECT * FROM tb_couriel_arriver c WHERE c.id = ?1",nativeQuery = true)
    CourielArriverDto findByid(Long id);

//    @Query(value = "SELECT * FROM tb_couriel_arriver c WHERE DATE(c.dateNow) = CURDATE()", nativeQuery = true)

    @Query(value = "SELECT * FROM tb_couriel_arriver c WHERE c.date_darriver = CURDATE() OR c.verifier = false", nativeQuery = true)
    List<CourielArriverDto> findCourrielsToday();

    @Query(value = "SELECT * FROM tb_couriel_arriver c WHERE c.date_darriver != CURDATE() AND c.verifier = true", nativeQuery = true)
    List<CourielArriverDto> findCourrielsNotToday();



}
