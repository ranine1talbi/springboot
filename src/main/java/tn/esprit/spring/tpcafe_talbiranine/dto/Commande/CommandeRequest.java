package tn.esprit.spring.tpcafe_talbiranine.dto.Commande;

import lombok.*;
import tn.esprit.spring.tpcafe_talbiranine.dto.Client.ClientRequest;
import tn.esprit.spring.tpcafe_talbiranine.dto.Detail_Commande.Detail_CommandeRequest;
import tn.esprit.spring.tpcafe_talbiranine.entites.StatusCommande;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommandeRequest {

    private long idCommande; // optionnel si c'est pour créer, généralement généré par la DB

    private ClientRequest client; // DTO pour le client

    private List<Detail_CommandeRequest> details; // DTO pour les détails de commande

    private LocalDate dateCommande;

    private float totalCommande;

    private StatusCommande statusCommande;
}
