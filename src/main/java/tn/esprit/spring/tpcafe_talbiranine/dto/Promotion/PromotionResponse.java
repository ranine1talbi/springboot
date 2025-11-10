package tn.esprit.spring.tpcafe_talbiranine.dto.Promotion;

import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PromotionResponse {
    private long idPromotion;
    private String pourcentagePromo;
    private LocalDate dateDebutPromo;
    private LocalDate dateFinPromo;
    private List<Long> articleIds; // IDs des articles associés
}
