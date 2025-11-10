package tn.esprit.spring.tpcafe_talbiranine.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.tpcafe_talbiranine.dto.Promotion.PromotionRequest;
import tn.esprit.spring.tpcafe_talbiranine.dto.Promotion.PromotionResponse;
import tn.esprit.spring.tpcafe_talbiranine.entites.Promotion;
import tn.esprit.spring.tpcafe_talbiranine.mapper.Promotion.PromotionMappers;
import tn.esprit.spring.tpcafe_talbiranine.services.Promotion.PromotionService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("promotions")
@AllArgsConstructor
public class PromotionRestController {

    private final PromotionService promotionService;
    private final PromotionMappers promotionMappers;

    @PostMapping
    public PromotionResponse addPromotion(@RequestBody PromotionRequest request) {
        Promotion promotion = promotionMappers.toEntity(request);
        Promotion saved = promotionService.addPromotion(promotion, request.getArticleIds());
        return promotionMappers.toDto(saved);
    }

    @PostMapping("addAll")
    public List<PromotionResponse> addAllPromotions(@RequestBody List<PromotionRequest> requests) {
        return requests.stream()
                .map(req -> promotionMappers.toEntity(req))
                .map(promo -> promotionService.addPromotion(promo, promo.getArticles().stream().map(a -> a.getIdArticle()).toList()))
                .map(promo -> promotionMappers.toDto(promo))
                .collect(Collectors.toList());
    }

    @GetMapping
    public List<PromotionResponse> getAllPromotions() {
        return promotionService.selectAllPromotions()
                .stream()
                .map(promotionMappers::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public PromotionResponse getPromotionById(@PathVariable long id) {
        Promotion promotion = promotionService.selectPromotionById(id);
        return promotionMappers.toDto(promotion);
    }

    @DeleteMapping("deleteById/{id}")
    public void deletePromotionById(@PathVariable long id) {
        promotionService.deletePromotionById(id);
    }

    @DeleteMapping("deleteAll")
    public void deleteAllPromotions() {
        promotionService.deleteAllPromotions();
    }

    @GetMapping("count")
    public long countPromotions() {
        return promotionService.countPromotions();
    }

    @GetMapping("exists/{id}")
    public boolean existsById(@PathVariable long id) {
        return promotionService.verifPromotionById(id);
    }
}
