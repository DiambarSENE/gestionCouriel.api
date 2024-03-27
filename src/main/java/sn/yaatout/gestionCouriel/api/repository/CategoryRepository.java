package sn.yaatout.gestionCouriel.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sn.yaatout.gestionCouriel.api.models.CategoryDto;

public interface CategoryRepository extends JpaRepository<CategoryDto, Long> {

    @Query(value = "SELECT * FROM tb_categorys c WHERE c.id = ?1",nativeQuery = true)
    CategoryDto findByid(Long id);
    @Query(value = "SELECT * FROM tb_categorys c WHERE c.nom = ?1",nativeQuery = true)
    CategoryDto findByNom(String nom);
}
