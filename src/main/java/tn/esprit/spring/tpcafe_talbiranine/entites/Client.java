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

    // ✅ Lorsqu’on crée/supprime un client, son adresse est aussi créée/supprimée
    @OneToOne(cascade = CascadeType.ALL)
    private Adresse adresse;

    // ✅ Lorsqu’on
    // supprime un client, sa carte fidélité est aussi supprimée
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    List <CarteFidelite> carteFidelite;

    // ✅ Lorsqu’on supprime un client, ses commandes aussi
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
     List<Commande> commandes;
}
