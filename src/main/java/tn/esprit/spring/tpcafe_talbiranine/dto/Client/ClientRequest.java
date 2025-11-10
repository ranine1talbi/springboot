package tn.esprit.spring.tpcafe_talbiranine.dto.Client;

import lombok.*;
import tn.esprit.spring.tpcafe_talbiranine.dto.Adresse.AdresseRequest;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientRequest {

    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private AdresseRequest adresse;
}
