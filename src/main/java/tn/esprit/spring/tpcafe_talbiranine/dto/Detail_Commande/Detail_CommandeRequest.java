package tn.esprit.spring.tpcafe_talbiranine.dto.Detail_Commande;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Detail_CommandeRequest {

    // ✅ ID de la commande associée
    private Long commandeId;

    // ✅ Quantité de l'article dans la commande
    private int quantiteArticle;

}
