package tn.esprit.spring.tpcafe_talbiranine.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.tpcafe_talbiranine.entites.Promotion;
import tn.esprit.spring.tpcafe_talbiranine.services.Promotion.PromotionService;

import java.util.List;

@RestController
@RequestMapping("promotions")
@AllArgsConstructor
public class PromotionRestController {

    private final PromotionService promotionService;

    // Add a single promotion
    @PostMapping("/add")
    public Promotion addPromotion(@RequestBody Promotion promotion) {
        return promotionService.addPromotion(promotion);
    }

    // Add multiple promotions at once
    @PostMapping("/addAll")
    public List<Promotion> addAllPromotions(@RequestBody List<Promotion> promotions) {
        return promotionService.savePromotions(promotions);
    }

    // Get all promotions
    @GetMapping("/all")
    public List<Promotion> getAllPromotions() {
        return promotionService.selectAllPromotions();
    }

    // Get a promotion by ID
    @GetMapping("/{id}")
    public Promotion getPromotionById(@PathVariable long id) {
        return promotionService.selectPromotionById(id);
    }

    // Get a promotion by ID with OrElse (default if not found)
    @GetMapping("/default/{id}")
    public Promotion getPromotionByIdOrElse(@PathVariable long id) {
        return promotionService.selectPromotionByIdWithOrElse(id);
    }

    // Check if a promotion exists by ID
    @GetMapping("/exists/{id}")
    public boolean existsById(@PathVariable long id) {
        return promotionService.verifPromotionById(id);
    }

    // Delete a promotion by ID
    @DeleteMapping("/delete/{id}")
    public void deletePromotionById(@PathVariable long id) {
        promotionService.deletePromotionById(id);
    }

    // Delete all promotions
    @DeleteMapping("/deleteAll")
    public void deleteAllPromotions() {
        promotionService.deleteAllPromotions();
    }

    // Count total promotions
    @GetMapping("/count")
    public long countPromotions() {
        return promotionService.countPromotions();
    }
}
