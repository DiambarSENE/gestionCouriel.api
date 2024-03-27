package sn.yaatout.gestionCouriel.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "tb_users")
@Data
@AllArgsConstructor
@NoArgsConstructor
//@Builder
public class UserDto {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(name = "matricule")
    public String matricule;

    @Column(name = "nom")
    public String nom;
    @Column(name = "prenom")
    public String prenom;
    @Column(name = "telephone")
    public Integer telephone;

    @Column(name = "email")
    public String email;
    //@Column(name = "password")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String password;
    @Column(name = "dateCreation")
    public String dateCreation;

    @Column(name = "adresse")
    public String adresse;

    @Column(name = "photoProfil")
    public String photoProfil;

    @Column(name = "role")
    public String role;
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "users_roles",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id")
//    )
//    public List<RoleDto> roles = new ArrayList<>();

}
