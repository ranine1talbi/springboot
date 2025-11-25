package tn.esprit.spring.tpcafe_talbiranine.services.Commande;

import tn.esprit.spring.tpcafe_talbiranine.dto.Commande.CommandeRequest;
import tn.esprit.spring.tpcafe_talbiranine.dto.Commande.CommandeResponse;
import tn.esprit.spring.tpcafe_talbiranine.entites.Commande;
import tn.esprit.spring.tpcafe_talbiranine.entites.StatusCommande;

import java.time.LocalDate;
import java.util.List;

public interface ICommandeServices {

    // ------------------- CRUD avec DTO -------------------
    CommandeResponse addCommande(CommandeRequest commandeRequest);
    List<CommandeResponse> saveCommandes(List<CommandeRequest> commandesRequest);
    CommandeResponse selectCommandeById(long id);
    List<CommandeResponse> selectAllCommandes();

    // ------------------- Suppression / utils -------------------
    void deleteCommandeById(long id);
    void deleteAllCommandes();
    long countCommandes();
    boolean verifCommandeById(long id);

    // ------------------- Méthodes avancées -------------------

    // 1. Trouver par statut
    List<CommandeResponse> findByStatus(StatusCommande status);

    // 2. Trouver par date exacte
    List<CommandeResponse> findByDate(LocalDate date);

    // 3. Compter par statut
    long countByStatus(StatusCommande status);

    // 4. Supprimer avant une date
    void deleteBeforeDate(LocalDate date);
    void affecterCommandeAClient( long idCommande, long idClient);
    void desaffecterClientDeCommande(long idCommande ,long idClient);

    // 5. Trouver entre deux dates avec statut spécifique
    List<CommandeResponse> findBetweenDatesWithStatus(LocalDate start, LocalDate end, StatusCommande status);

    // 6. Trouver avec total > montant et statut différent
    List<CommandeResponse> findTotalGreaterThanAndStatusNot(float total, StatusCommande status);

    // 7. Trouver avec certains statuts, triées par date
    List<CommandeResponse> findByStatusesOrderByDateDesc(List<StatusCommande> statuses);

    // 8. Trouver avant une date avec total dans une plage
    List<CommandeResponse> findBeforeDateWithTotalBetween(LocalDate date, float min, float max);

    // 9. Trouver où statut se termine par une lettre
    List<CommandeResponse> findByStatusEndingWith(String letter);

    // 10. Trouver commandes sans statut
    List<CommandeResponse> findWithoutStatus();

    // 11. Trouver commandes avec total renseigné
    List<CommandeResponse> findWithTotal();

    // 12. Trouver commandes avec détails et client
    List<CommandeResponse> findAllWithDetailsAndClient();

    // 13. Top 3 commandes les plus récentes
    List<CommandeResponse> findTop3Recent();

}
