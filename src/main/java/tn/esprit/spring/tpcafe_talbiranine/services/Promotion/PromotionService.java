package tn.esprit.spring.tpcafe_talbiranine.services.Promotion;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.tpcafe_talbiranine.entites.Promotion;
import tn.esprit.spring.tpcafe_talbiranine.repositories.PromotionRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class PromotionService implements IPromotionServices {

    public PromotionRepository promotionRepository;

    @Override
    public Promotion addPromotion(Promotion promotion) {
        return promotionRepository.save(promotion);
    }

    @Override
    public List<Promotion> savePromotions(List<Promotion> promotions) {
        return promotionRepository.saveAll(promotions);
    }

    @Override
    public Promotion selectPromotionByIdWithGet(long id) {
        return promotionRepository.findById(id).get();
    }

    @Override
    public Promotion selectPromotionByIdWithOrElse(long id) {
        Promotion defaultPromotion = Promotion.builder()
                .pourcentagePromo("0%")
                .dateDebutPromo(null)
                .dateFinPromo(null)
                .build();
        return promotionRepository.findById(id).orElse(defaultPromotion);
    }

    @Override
    public List<Promotion> selectAllPromotions() {
        return promotionRepository.findAll();
    }

    @Override
    public void deletePromotion(Promotion promotion) {
        promotionRepository.delete(promotion);
    }

    @Override
    public void deleteAllPromotions() {
        promotionRepository.deleteAll();
    }

    @Override
    public void deletePromotionById(long id) {
        promotionRepository.deleteById(id);
    }

    @Override
    public long countPromotions() {
        return promotionRepository.count();
    }

    @Override
    public boolean verifPromotionById(long id) {
        return promotionRepository.existsById(id);
    }

    @Override
    public Promotion selectPromotionById(long id) {
        return promotionRepository.findById(id).orElse(null);
    }
}
