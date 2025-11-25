package tn.esprit.spring.tpcafe_talbiranine.services.Detail_Commande;

import tn.esprit.spring.tpcafe_talbiranine.dto.Detail_Commande.Detail_CommandeRequest;
import tn.esprit.spring.tpcafe_talbiranine.dto.Detail_Commande.Detail_CommandeResponse;
import tn.esprit.spring.tpcafe_talbiranine.entites.Detail_Commande;

import java.util.List;

public interface IDetail_CommandeServices {

    // ✅ Méthodes CRUD avec DTO
    Detail_CommandeResponse addDetailCommande(Detail_CommandeRequest request);
    List<Detail_CommandeResponse> saveDetailsCommande(List<Detail_CommandeRequest> requests);
    Detail_CommandeResponse recupererDetailCommandeParId(long id);
    List<Detail_CommandeResponse> selectAllDetailsCommande();

    // ✅ Méthodes de suppression et utils
    void deleteDetailCommandeById(long id);
    void deleteAllDetailsCommande();
    long countDetailsCommande();
    boolean verifDetailCommandeById(long id);

    // 1️⃣ Méthodes avancées pour requêtes personnalisées
    List<Detail_CommandeResponse> findByQuantiteArticle(int quantite);
    List<Detail_CommandeResponse> findBySousTotalExact(float sousTotal);
    long countByQuantiteGreaterThan(int quantite);
    boolean existsBySousTotalGreaterThan(float montant);
    List<Detail_CommandeResponse> findByQuantiteBetweenAndSousTotalMin(int minQte, int maxQte, float minSousTotal);
    List<Detail_CommandeResponse> findBySousTotalBetweenOrderByQuantite(float min, float max);
    List<Detail_CommandeResponse> findBySousTotalApresPromoBetween(float min, float max);
    List<Detail_CommandeResponse> findByQuantiteOrSousTotalMin(int quantite, float sousTotalMin);
    List<Detail_CommandeResponse> findTop5MostExpensive();
    List<Detail_CommandeResponse> findWithoutQuantite();
    List<Detail_CommandeResponse> findWithSousTotalApresPromo();
    List<Detail_CommandeResponse> findAllWithCommandeAndArticle();
}
