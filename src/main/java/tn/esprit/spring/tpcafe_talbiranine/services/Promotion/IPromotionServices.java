package tn.esprit.spring.tpcafe_talbiranine.services.Promotion;

import tn.esprit.spring.tpcafe_talbiranine.entites.Promotion;
import java.util.List;

public interface IPromotionServices {

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
}
