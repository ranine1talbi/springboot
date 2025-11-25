package tn.esprit.spring.tpcafe_talbiranine.dto.Adresse;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdresseResponse {


    private long idAdresse;
    private String rue;
    private String ville;
    private int codePostal;
}
