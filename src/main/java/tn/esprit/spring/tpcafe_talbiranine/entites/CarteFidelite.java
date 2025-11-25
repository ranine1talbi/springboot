package tn.esprit.spring.tpcafe_talbiranine.entites;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "carte_fidelite")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarteFidelite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCarteFidelite;

    private Integer pointsAcumules; // ✅ nom corrigé pour correspondre au repository

    private LocalDate dateCreation;
    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;

}
