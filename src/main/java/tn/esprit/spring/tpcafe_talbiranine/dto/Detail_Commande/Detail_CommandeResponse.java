package tn.esprit.spring.tpcafe_talbiranine.dto.Detail_Commande;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Detail_CommandeResponse {

    // ✅ ID du détail commande
    private long idDetailCommande;

    // ✅ ID de la commande associée
    private Long commandeId;

}
