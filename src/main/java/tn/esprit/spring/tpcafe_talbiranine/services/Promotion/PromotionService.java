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

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PromotionService implements IPromotionServices {

    private final PromotionRepository promotionRepository;
    private final ArticleRepository articleRepository;
    private final PromotionMappers promotionMappers;

    // Ajouter une promotion avec gestion des articles
    public Promotion addPromotion(Promotion promotion, List<Long> articleIds) {
        if (articleIds != null && !articleIds.isEmpty()) {
            List<Article> articles = articleRepository.findAllById(articleIds);
            promotion.setArticles(articles);
        }
        return promotionRepository.save(promotion);
    }

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
    public void affecterPromotionArticle(long idArticle, long idPromotion) {
        Article article = articleRepository.findById(idArticle).get();
        Promotion promo = promotionRepository.findById(idPromotion).get();
        article.getPromotions().add(promo);
        articleRepository.save(article);
    }
    @Override
    public void desaffecterPromotionArticle(long idArticle, long idPromotion) {
        Article article = articleRepository.findById(idArticle).get();
        Promotion promo = promotionRepository.findById(idPromotion).get();
        article.getPromotions().remove(promo);
        articleRepository.save(article);
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

    // ---------------- Méthodes avancées ---------------- //

    // 1. Trouver les promotions par pourcentage exact
    public List<Promotion> findByPourcentage(String pourcentage) {
        return promotionRepository.findByPourcentagePromo(pourcentage);
    }

    // 2. Trouver les promotions par date de début
    public List<Promotion> findByDateDebut(LocalDate dateDebut) {
        return promotionRepository.findByDateDebutPromo(dateDebut);
    }

    // 3. Trouver les promotions par date de fin
    public List<Promotion> findByDateFin(LocalDate dateFin) {
        return promotionRepository.findByDateFinPromo(dateFin);
    }

    // 4. Vérifier existence d'une promotion par pourcentage
    public boolean existsByPourcentage(String pourcentage) {
        return promotionRepository.existsByPourcentagePromo(pourcentage);
    }

    // 5. Compter promotions débutant après une date
    public long countPromotionsAfterDate(LocalDate date) {
        return promotionRepository.countByDateDebutPromoAfter(date);
    }

    // 6. Trouver promotions actives à une date donnée
    public List<Promotion> findActiveAtDate(LocalDate date) {
        return promotionRepository.findActivePromotionsAtDate(date);
    }

    // 7. Promotions avec pourcentage spécifique dans une période
    public List<Promotion> findByPourcentageAndPeriod(String pourcentage, LocalDate start, LocalDate end) {
        return promotionRepository.findByPourcentageAndStartDateBetween(pourcentage, start, end);
    }

    // 8. Promotions valides à une date spécifique
    public List<Promotion> findValidAtDate(LocalDate date) {
        return promotionRepository.findValidPromotionsAtDate(date);
    }

    // 9. Promotions avec certains pourcentages, triées par date début
    public List<Promotion> findByPourcentagesSorted(List<String> pourcentages) {
        return promotionRepository.findByPourcentagePromoInOrderByDateDebutPromoAsc(pourcentages);
    }

    // 10. Promotions actives triées par pourcentage
    public List<Promotion> findActiveSortedByPourcentage(LocalDate date) {
        return promotionRepository.findActivePromotionsOrderByPourcentage(date);
    }

    // 11. Promotions sans date de fin
    public List<Promotion> findWithoutEndDate() {
        return promotionRepository.findByDateFinPromoIsNull();
    }

    // 12. Promotions avec pourcentage renseigné
    public List<Promotion> findWithPourcentage() {
        return promotionRepository.findByPourcentagePromoIsNotNull();
    }

    // 13. Promotions avec articles associés
    public List<Promotion> findAllWithArticles() {
        return promotionRepository.findAllWithArticles();
    }

    // 14. Promotions expirées
    public List<Promotion> findExpired(LocalDate date) {
        return promotionRepository.findExpiredPromotions(date);
    }
}
