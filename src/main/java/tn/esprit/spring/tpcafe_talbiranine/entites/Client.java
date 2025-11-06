package tn.esprit.spring.tpcafe_talbiranine.entites;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "client")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClient;

    private String nom;

    private String prenom;

    private LocalDate dateNaissance;



    @OneToOne
    @JoinColumn(name = "adresse_id")
    private Adresse adresse;

    @OneToOne(mappedBy = "client")
    private CarteFidelite carteFidelite;

    @OneToMany(mappedBy = "client")
    private List<Commande> commandes;
}