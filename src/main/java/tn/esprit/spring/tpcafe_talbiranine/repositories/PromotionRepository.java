package tn.esprit.spring.tpcafe_talbiranine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.tpcafe_talbiranine.entites.Article;
import tn.esprit.spring.tpcafe_talbiranine.entites.Promotion;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {

    // 1. Trouver les promotions par pourcentage exact
    List<Promotion> findByPourcentagePromo(String pourcentagePromo);

    // 2. Trouver les promotions par date de début
    List<Promotion> findByDateDebutPromo(LocalDate dateDebutPromo);

    // 3. Trouver les promotions par date de fin
    List<Promotion> findByDateFinPromo(LocalDate dateFinPromo);

    // 4. Vérifier l'existence d'une promotion par pourcentage
    boolean existsByPourcentagePromo(String pourcentagePromo);

    // 5. Compter les promotions débutant après une date
    long countByDateDebutPromoAfter(LocalDate date);




    // 6. Trouver les promotions actives à une date donnée
    @Query("SELECT p FROM Promotion p WHERE p.dateDebutPromo <= :date AND (p.dateFinPromo IS NULL OR p.dateFinPromo >= :date)")
    List<Promotion> findActivePromotionsAtDate(LocalDate date);

    // 7. Trouver les promotions avec un pourcentage spécifique débutant dans une période
    @Query("SELECT p FROM Promotion p WHERE p.pourcentagePromo = :pourcentage AND p.dateDebutPromo BETWEEN :start AND :end")
    List<Promotion> findByPourcentageAndStartDateBetween(String pourcentage, LocalDate start, LocalDate end);

    // 8. Trouver les promotions valides à une date spécifique
    @Query("SELECT p FROM Promotion p WHERE p.dateDebutPromo <= :date AND (p.dateFinPromo IS NULL OR p.dateFinPromo >= :date)")
    List<Promotion> findValidPromotionsAtDate(LocalDate date);

    // 9. Trouver les promotions avec certains pourcentages, triées par date de début
    List<Promotion> findByPourcentagePromoInOrderByDateDebutPromoAsc(List<String> pourcentages);

    // 10. Trouver les promotions actives triées par pourcentage
    @Query("SELECT p FROM Promotion p WHERE p.dateDebutPromo <= :date AND (p.dateFinPromo IS NULL OR p.dateFinPromo >= :date) ORDER BY p.pourcentagePromo ASC")
    List<Promotion> findActivePromotionsOrderByPourcentage(LocalDate date);

    // 11. Trouver les promotions sans date de fin
    List<Promotion> findByDateFinPromoIsNull();

    // 12. Trouver les promotions avec un pourcentage renseigné
    List<Promotion> findByPourcentagePromoIsNotNull();

    // 13. Trouver les promotions avec leurs articles associés
    @Query("SELECT DISTINCT p FROM Promotion p LEFT JOIN FETCH p.articles")
    List<Promotion> findAllWithArticles();

    // 14. Trouver les promotions expirées
    @Query("SELECT p FROM Promotion p WHERE p.dateFinPromo < :date")
    List<Promotion> findExpiredPromotions(LocalDate date);
}