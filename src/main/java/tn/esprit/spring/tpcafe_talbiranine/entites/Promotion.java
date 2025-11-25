package tn.esprit.spring.tpcafe_talbiranine.entites;

import jakarta.persistence.*; //Importe  les annotations et classes de JPA
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Promotion")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //la base génère automatiquement
    private long idPromotion;

    private String pourcentagePromo;

    private LocalDate dateDebutPromo;
    private LocalDate dateFinPromo;


    @ManyToMany(mappedBy = "promotions",cascade = CascadeType.ALL)
    private List<Article> articles;

}