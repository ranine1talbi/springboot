package tn.esprit.spring.tpcafe_talbiranine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.tpcafe_talbiranine.entites.Detail_Commande;

import java.util.List;

@Repository
public interface DetailCommandeRepository extends JpaRepository<Detail_Commande, Long> {

    // 1️⃣ Trouver les détails de commande par quantité exacte
    List<Detail_Commande> findByQuantiteArticle(int quantiteArticle);

    // 2️⃣ Trouver les détails par sous-total exact
    List<Detail_Commande> findBySousTotalDetailArticle(float sousTotalDetailArticle);

    // 3️⃣ Compter les détails avec plus de X quantités
    long countByQuantiteArticleGreaterThan(int quantite);

    // 4️⃣ Vérifier l'existence de détails avec un sous-total élevé
    boolean existsBySousTotalDetailArticleGreaterThan(float montant);

    // 5️⃣ Trouver les détails avec une quantité dans une plage et un sous-total minimum
    List<Detail_Commande> findByQuantiteArticleBetweenAndSousTotalDetailArticleGreaterThan(
            int quantiteMin, int quantiteMax, float sousTotalMin);

    // 6️⃣ Trouver les détails avec un sous-total dans une plage, triés par quantité
    List<Detail_Commande> findBySousTotalDetailArticleBetweenOrderByQuantiteArticleAsc(
            float sousTotalMin, float sousTotalMax);

    // 7️⃣ Trouver les détails avec un sous-total après promotion dans une plage
    List<Detail_Commande> findBySousTotalDetailArticleApresPromoBetween(float min, float max);

    // 8️⃣ Trouver les détails par quantité ou sous-total minimum
    List<Detail_Commande> findByQuantiteArticleOrSousTotalDetailArticleGreaterThan(
            int quantite, float sousTotalMin);

    // 9️⃣ Trouver les 5 détails les plus chers
    List<Detail_Commande> findTop5ByOrderBySousTotalDetailArticleDesc();

    // 🔟 Trouver les détails sans quantité renseignée
    // ⚠️ Il faut que quantiteArticle soit Integer au lieu de int pour gérer null
    List<Detail_Commande> findByQuantiteArticleIsNull();

    // 1️⃣1️⃣ Trouver les détails avec un sous-total après promotion renseigné
    List<Detail_Commande> findBySousTotalDetailArticleApresPromoIsNotNull();

    // 1️⃣2️⃣ Trouver les détails avec leur commande et article
    @Query("SELECT d FROM Detail_Commande d JOIN FETCH d.commande JOIN FETCH d.article")
    List<Detail_Commande> findAllWithCommandeAndArticle();
}
