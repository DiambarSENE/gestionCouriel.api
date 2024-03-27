package sn.yaatout.gestionCouriel.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sn.yaatout.gestionCouriel.api.models.UserDto;

public interface UserRepository extends JpaRepository<UserDto, Long> {
    @Query(value = "SELECT * FROM tb_users u WHERE u.email = ?1",nativeQuery = true)
    UserDto findByEmail(String astring);

    @Query(value = "SELECT * FROM tb_users u WHERE u.id = ?1",nativeQuery = true)
    UserDto findByid(Long id);


}
