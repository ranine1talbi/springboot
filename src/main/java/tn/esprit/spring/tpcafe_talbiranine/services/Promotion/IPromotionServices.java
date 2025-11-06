package tn.esprit.spring.tpcafe_talbiranine.services.Promotion;
import tn.esprit.spring.tpcafe_talbiranine.entites.Promotion;
import java.util.List;
public interface IPromotionServices {
    Promotion addPromotion(Promotion promotion);
    List<Promotion> savePromotions(List<Promotion> promotions);
    Promotion selectPromotionById(long id);
    Promotion selectPromotionByIdWithGet(long id);
    Promotion selectPromotionByIdWithOrElse(long id);
    List<Promotion> selectAllPromotions();
    void deletePromotion(Promotion promotion);
    void deleteAllPromotions();
    void deletePromotionById(long id);
    long countPromotions();
    boolean verifPromotionById(long id);
}
