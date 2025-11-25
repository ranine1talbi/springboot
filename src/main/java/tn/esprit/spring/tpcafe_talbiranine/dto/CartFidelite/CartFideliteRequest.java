package tn.esprit.spring.tpcafe_talbiranine.dto.CartFidelite;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartFideliteRequest {
    private Integer pointsAcumules;
    private LocalDate dateCreation;
    private Long clientId;
}
