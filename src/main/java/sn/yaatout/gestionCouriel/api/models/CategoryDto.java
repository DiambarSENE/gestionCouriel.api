package sn.yaatout.gestionCouriel.api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sn.yaatout.gestionJournaux.model.Category;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "tb_categorys")
@Builder
public class CategoryDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(name = "nom")
    public String nom;
    @Column(name = "description")
    public String description;
    @Column(name = "dateCreation")
    public String dateCreation;

}
