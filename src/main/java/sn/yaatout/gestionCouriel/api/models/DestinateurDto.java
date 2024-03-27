package sn.yaatout.gestionCouriel.api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "tb_destinateurs")
@Builder
public class DestinateurDto {
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
