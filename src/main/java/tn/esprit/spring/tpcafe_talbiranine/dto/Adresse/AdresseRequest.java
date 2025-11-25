package tn.esprit.spring.tpcafe_talbiranine.dto.Adresse;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdresseRequest {

    private String rue;
    private String ville;
    private int codePostal;

}
