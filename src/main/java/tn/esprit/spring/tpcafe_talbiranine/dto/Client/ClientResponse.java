package tn.esprit.spring.tpcafe_talbiranine.dto.Client;

import lombok.*;
import tn.esprit.spring.tpcafe_talbiranine.dto.Adresse.AdresseResponse;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientResponse {

    private Long idClient;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private AdresseResponse adresse;
}
