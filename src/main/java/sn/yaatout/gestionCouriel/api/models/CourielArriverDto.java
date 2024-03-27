package sn.yaatout.gestionCouriel.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.io.Resource;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "tb_couriel_arriver")
@Builder
public class CourielArriverDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(name = "dateDarriver")
    public String dateDarriver;
    @Column(name = "dateCorrespondance")
    public String dateCorrespondance;
    @Column(name = "numeroCorrespondance")
    public Long numeroCorrespondance;
    @Column(name = "expediteur")
    public String expediteur;
    @Column(name = "objet")
    public String objet;
    @Column(name = "dateReponse")
    public String dateReponse;
    @Column(name = "numeroReponse")
    public Long numeroReponse;
    @Column(name = "filePdf")
    public String filePdf;
    @Column(name = "verifier")
    public Boolean verifier;

       //@Lob
    //private byte[] imageData;
    //@Lob
    //@Column(columnDefinition = "MEDIUMBLOB")

}
