package tn.esprit.spring.tpcafe_talbiranine.entites;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;
@Entity
@Table(name = "Commande")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCommande;

    @ManyToOne
    private Client client;

    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL)
    private List<Detail_Commande> details;

    private LocalDate dateCommande;

    @Column(name = "totalCommande")
    private float totalCommande;

    @Enumerated(EnumType.STRING)
    private StatusCommande statusCommande;
}
