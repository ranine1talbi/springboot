package tn.esprit.spring.tpcafe_talbiranine.services.Promotion;

import tn.esprit.spring.tpcafe_talbiranine.entites.Article;
import tn.esprit.spring.tpcafe_talbiranine.entites.Promotion;

import java.time.LocalDate;
import java.util.List;

public interface IPromotionServices {

    // ---------------- CRUD de base ---------------- //

    // Ajouter une promotion et lier des articles
    Promotion addPromotion(Promotion promotion, List<Long> articleIds);

    // Ajouter plusieurs promotions
    List<Promotion> savePromotions(List<Promotion> promotions);

    // Récupérer une promotion par ID
    Promotion selectPromotionById(long id);

    Promotion selectPromotionByIdWithGet(long id);

    Promotion selectPromotionByIdWithOrElse(long id);

    // Récupérer toutes les promotions
    List<Promotion> selectAllPromotions();

    // Supprimer une promotion
    void deletePromotion(Promotion promotion);

    // Supprimer toutes les promotions
    void deleteAllPromotions();

    // Supprimer une promotion par ID
    void deletePromotionById(long id);

    // Compter le nombre total de promotions
    long countPromotions();

    // Vérifier si une promotion existe par ID
    boolean verifPromotionById(long id);


    // ---------------- Méthodes avancées ---------------- //

    // 1. Trouver les promotions par pourcentage exact
    List<Promotion> findByPourcentage(String pourcentage);

    // 2. Trouver les promotions par date de début
    List<Promotion> findByDateDebut(LocalDate dateDebut);

    // 3. Trouver les promotions par date de fin
    List<Promotion> findByDateFin(LocalDate dateFin);

    // 4. Vérifier l'existence d'une promotion par pourcentage
    boolean existsByPourcentage(String pourcentage);

    // 5. Compter les promotions débutant après une date
    long countPromotionsAfterDate(LocalDate date);

    // 6. Trouver les promotions actives à une date donnée
    List<Promotion> findActiveAtDate(LocalDate date);

    // 7. Trouver les promotions avec un pourcentage spécifique débutant dans une période
    List<Promotion> findByPourcentageAndPeriod(String pourcentage, LocalDate start, LocalDate end);

    // 8. Trouver les promotions valides à une date spécifique
    List<Promotion> findValidAtDate(LocalDate date);

    // 9. Trouver les promotions avec certains pourcentages, triées par date de début
    List<Promotion> findByPourcentagesSorted(List<String> pourcentages);

    // 10. Trouver les promotions actives triées par pourcentage
    List<Promotion> findActiveSortedByPourcentage(LocalDate date);

    // 11. Trouver les promotions sans date de fin
    List<Promotion> findWithoutEndDate();

    // 12. Trouver les promotions avec un pourcentage renseigné
    List<Promotion> findWithPourcentage();

    // 13. Trouver les promotions avec leurs articles associés
    List<Promotion> findAllWithArticles();

    // 14. Trouver les promotions expirées
    List<Promotion> findExpired(LocalDate date);

    void affecterPromotionArticle(long idArticle, long idPromotion);
    void desaffecterPromotionArticle(long idArticle, long idPromotion);

}