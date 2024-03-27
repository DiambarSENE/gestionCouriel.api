package sn.yaatout.gestionCouriel.api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "tb_couriel_depart")
@Builder
public class CourielDepartDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(name = "numeroDordre")
    public Long numeroDordre;
    @Column(name = "nombreDePieces")
    public Long nombreDePieces;
    @Column(name = "dateDuDepart")
    public String dateDuDepart;
    @Column(name = "destinataire")
    public String destinataire;
    @Column(name = "objet")
    public String objet;
    @Column(name = "numeroArChive")
    public Long numeroArChive;
    @Column(name = "observations")
    public String observations;
    @Column(name = "filePdf")
    public String filePdf;
    @Column(name = "verifier")
    public Boolean verifier;

       //@Lob
    //private byte[] imageData;
    //@Lob
    //@Column(columnDefinition = "MEDIUMBLOB")

}
