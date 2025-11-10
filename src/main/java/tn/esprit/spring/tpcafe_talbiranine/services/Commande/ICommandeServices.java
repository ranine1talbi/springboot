package tn.esprit.spring.tpcafe_talbiranine.services.Commande;

import tn.esprit.spring.tpcafe_talbiranine.dto.Commande.CommandeRequest;
import tn.esprit.spring.tpcafe_talbiranine.dto.Commande.CommandeResponse;

import java.util.List;

public interface ICommandeServices {

    // ✅ CRUD avec DTO
    CommandeResponse addCommande(CommandeRequest commandeRequest);
    List<CommandeResponse> saveCommandes(List<CommandeRequest> commandesRequest);
    CommandeResponse selectCommandeById(long id);
    List<CommandeResponse> selectAllCommandes();

    // ✅ Suppression / utils
    void deleteCommandeById(long id);
    void deleteAllCommandes();
    long countCommandes();
    boolean verifCommandeById(long id);

    // Optionnel : si tu veux garder les méthodes avec l'entité
    // void deleteCommande(Commande commande);
    // Commande selectCommandeByIdWithGet(long id);
    // Commande selectCommandeByIdWithOrElse(long id);
}
