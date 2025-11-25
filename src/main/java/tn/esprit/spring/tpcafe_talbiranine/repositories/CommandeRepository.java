package tn.esprit.spring.tpcafe_talbiranine.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.tpcafe_talbiranine.entites.Commande;
import tn.esprit.spring.tpcafe_talbiranine.entites.StatusCommande;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {

    // 1. Trouver les commandes par statut
    List<Commande> findByStatusCommande(StatusCommande statusCommande);

    // 2. Trouver les commandes par date exacte
    List<Commande> findByDateCommande(LocalDate dateCommande);

    // 3. Compter les commandes par statut
    long countByStatusCommande(StatusCommande statusCommande);

    // 4. Supprimer les commandes antérieures à une date
    void deleteByDateCommandeBefore(LocalDate date);

    // 5. Trouver les commandes entre deux dates avec un statut spécifique
    List<Commande> findByDateCommandeBetweenAndStatusCommande(LocalDate start, LocalDate end, StatusCommande status);

    // 6. Trouver les commandes avec un total supérieur à un montant et un statut différent
    List<Commande> findByTotalCommandeGreaterThanAndStatusCommandeNot(float total, StatusCommande status);

    // 7. Trouver les commandes avec certains statuts, triées par date
    List<Commande> findByStatusCommandeInOrderByDateCommandeDesc(List<StatusCommande> statuses);

    // 8. Trouver les commandes avant une date avec un total dans une plage
    List<Commande> findByDateCommandeBeforeAndTotalCommandeBetween(LocalDate date, float minTotal, float maxTotal);

    // 9. Trouver les commandes où le statut se termine par une lettre spécifique
    List<Commande> findByStatusCommandeEndingWith(String letter);

    // 10. Trouver les commandes sans statut renseigné
    List<Commande> findByStatusCommandeIsNull();

    // 11. Trouver les commandes avec un total renseigné
    List<Commande> findByTotalCommandeIsNotNull();

    // 12. Trouver les commandes avec leurs détails et client (fix avec @Query)
    @Query("SELECT c FROM Commande c")
    @EntityGraph(attributePaths = {"client", "details"})
    List<Commande> findAllWithClientAndDetails();

    // 13. Trouver le top 3 des commandes les plus récentes
    List<Commande> findTop3ByOrderByDateCommandeDesc();
}
