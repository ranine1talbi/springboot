package tn.esprit.spring.tpcafe_talbiranine.entites;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "adresse")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Adresse {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAdresse;

    private String rue;

    private String ville;

    private int codePostal;

    @OneToOne(mappedBy = "adresse")
    private Client client;


}
