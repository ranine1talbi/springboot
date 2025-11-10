package tn.esprit.spring.tpcafe_talbiranine.dto.Commande;

import lombok.*;
import tn.esprit.spring.tpcafe_talbiranine.dto.Client.ClientResponse;
import tn.esprit.spring.tpcafe_talbiranine.dto.Detail_Commande.Detail_CommandeResponse;
import tn.esprit.spring.tpcafe_talbiranine.entites.StatusCommande;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommandeResponse {

    private long idCommande;

    private ClientResponse client; // DTO pour le client

    private List<Detail_CommandeResponse> details; // DTO pour les détails de commande

    private LocalDate dateCommande;

    private float totalCommande;

    private StatusCommande statusCommande;
}
