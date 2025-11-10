package tn.esprit.spring.tpcafe_talbiranine.services.Promotion;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.tpcafe_talbiranine.dto.Promotion.PromotionRequest;
import tn.esprit.spring.tpcafe_talbiranine.dto.Promotion.PromotionResponse;
import tn.esprit.spring.tpcafe_talbiranine.entites.Article;
import tn.esprit.spring.tpcafe_talbiranine.entites.Promotion;
import tn.esprit.spring.tpcafe_talbiranine.mapper.Promotion.PromotionMappers;
import tn.esprit.spring.tpcafe_talbiranine.repositories.ArticleRepository;
import tn.esprit.spring.tpcafe_talbiranine.repositories.PromotionRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PromotionService implements IPromotionServices {

    private final PromotionRepository promotionRepository;
    private final ArticleRepository articleRepository;
    private final PromotionMappers promotionMappers;

    // ✅ Ajouter une promotion avec gestion des articles
    public Promotion addPromotion(Promotion promotion, List<Long> articleIds) {
        if (articleIds != null && !articleIds.isEmpty()) {
            List<Article> articles = articleRepository.findAllById(articleIds);
            promotion.setArticles(articles);
        }
        return promotionRepository.save(promotion);
    }

    // ✅ Ajouter plusieurs promotions
    public List<Promotion> savePromotions(List<Promotion> promotions) {
        return promotionRepository.saveAll(promotions);
    }

    @Override
    public Promotion selectPromotionById(long id) {
        return promotionRepository.findById(id).orElse(null);
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

    // ✅ Récupération via DTO
    public PromotionResponse recupererPromotionParId(long id) {
        Promotion promotion = selectPromotionById(id);
        return (promotion != null) ? promotionMappers.toDto(promotion) : null;
    }

    public List<PromotionResponse> selectAllPromotionsDTO() {
        return selectAllPromotions().stream().map(promotionMappers::toDto).collect(Collectors.toList());
    }

}
