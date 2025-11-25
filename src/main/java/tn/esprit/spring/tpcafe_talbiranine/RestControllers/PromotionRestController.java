package tn.esprit.spring.tpcafe_talbiranine.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.tpcafe_talbiranine.dto.Promotion.PromotionRequest;
import tn.esprit.spring.tpcafe_talbiranine.dto.Promotion.PromotionResponse;
import tn.esprit.spring.tpcafe_talbiranine.entites.Promotion;
import tn.esprit.spring.tpcafe_talbiranine.mapper.Promotion.PromotionMappers;
import tn.esprit.spring.tpcafe_talbiranine.services.Commande.CommandeService;
import tn.esprit.spring.tpcafe_talbiranine.services.Promotion.PromotionService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/promotions")
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

    @PostMapping("/addAll")
    public List<PromotionResponse> addAllPromotions(@RequestBody List<PromotionRequest> requests) {
        return requests.stream()
                .map(req -> {
                    Promotion entity = promotionMappers.toEntity(req);
                    return promotionService.addPromotion(entity, req.getArticleIds());
                })
                .map(promotionMappers::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping
    public List<PromotionResponse> getAllPromotions() {
        return promotionService.selectAllPromotions()
                .stream()
                .map(promotionMappers::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PromotionResponse getPromotionById(@PathVariable long id) {
        Promotion promotion = promotionService.selectPromotionById(id);
        return promotionMappers.toDto(promotion);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deletePromotionById(@PathVariable long id) {
        promotionService.deletePromotionById(id);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAllPromotions() {
        promotionService.deleteAllPromotions();
    }

    @GetMapping("/count")
    public long countPromotions() {
        return promotionService.countPromotions();
    }

    @GetMapping("/exists/{id}")
    public boolean existsById(@PathVariable long id) {
        return promotionService.verifPromotionById(id);
    }

    // --------------------
    // Méthodes avancées
    // --------------------

    // 1. Trouver promotions par pourcentage exact
    @GetMapping("/pourcentage/{pourcentage}")
    public List<PromotionResponse> findByPourcentage(@PathVariable String pourcentage) {
        return promotionService.findByPourcentage(pourcentage)
                .stream().map(promotionMappers::toDto).collect(Collectors.toList());
    }

    // 2. Promotions par date début
    @GetMapping("/dateDebut/{date}")
    public List<PromotionResponse> findByDateDebut(@PathVariable String date) {
        return promotionService.findByDateDebut(LocalDate.parse(date))
                .stream().map(promotionMappers::toDto).collect(Collectors.toList());
    }

    // 3. Promotions par date fin
    @GetMapping("/dateFin/{date}")
    public List<PromotionResponse> findByDateFin(@PathVariable String date) {
        return promotionService.findByDateFin(LocalDate.parse(date))
                .stream().map(promotionMappers::toDto).collect(Collectors.toList());
    }

    // 4. Existe par pourcentage
    @GetMapping("/existsPourcentage/{pourcentage}")
    public boolean existsByPourcentage(@PathVariable String pourcentage) {
        return promotionService.existsByPourcentage(pourcentage);
    }

    // 5. Compter promotions après une date
    @GetMapping("/countAfter/{date}")
    public long countAfterDate(@PathVariable String date) {
        return promotionService.countPromotionsAfterDate(LocalDate.parse(date));
    }

    // 6. Promotions actives à une date
    @GetMapping("/activeAt/{date}")
    public List<PromotionResponse> getActiveAtDate(@PathVariable String date) {
        return promotionService.findActiveAtDate(LocalDate.parse(date))
                .stream().map(promotionMappers::toDto).collect(Collectors.toList());
    }

    // 7. Promotions par pourcentage dans une période
    @GetMapping("/pourcentage/{pourcentage}/between/{start}/{end}")
    public List<PromotionResponse> getByPourcentageAndPeriod(
            @PathVariable String pourcentage,
            @PathVariable String start,
            @PathVariable String end) {

        return promotionService
                .findByPourcentageAndPeriod(pourcentage, LocalDate.parse(start), LocalDate.parse(end))
                .stream().map(promotionMappers::toDto).collect(Collectors.toList());
    }

    // 8. Promotions valides à une date
    @GetMapping("/validAt/{date}")
    public List<PromotionResponse> validAt(@PathVariable String date) {
        return promotionService.findValidAtDate(LocalDate.parse(date))
                .stream().map(promotionMappers::toDto).collect(Collectors.toList());
    }

    // 9. Promotions filtrées par liste pourcentages + tri
    @PostMapping("/sortedByStartDate")
    public List<PromotionResponse> getByPourcentagesSorted(@RequestBody List<String> pourcentages) {
        return promotionService.findByPourcentagesSorted(pourcentages)
                .stream().map(promotionMappers::toDto).collect(Collectors.toList());
    }

    // 10. Promotions actives triées par pourcentage
    @GetMapping("/activeSorted/{date}")
    public List<PromotionResponse> getActiveSorted(@PathVariable String date) {
        return promotionService.findActiveSortedByPourcentage(LocalDate.parse(date))
                .stream().map(promotionMappers::toDto).collect(Collectors.toList());
    }

    // 11. Promotions sans date de fin
    @GetMapping("/withoutEnd")
    public List<PromotionResponse> getWithoutEndDate() {
        return promotionService.findWithoutEndDate()
                .stream().map(promotionMappers::toDto).collect(Collectors.toList());
    }

    // 12. Promotions avec pourcentage renseigné
    @GetMapping("/withPourcentage")
    public List<PromotionResponse> getWithPourcentage() {
        return promotionService.findWithPourcentage()
                .stream().map(promotionMappers::toDto).collect(Collectors.toList());
    }

    // 13. Promotions avec leurs articles
    @GetMapping("/withArticles")
    public List<Promotion> getWithArticles() {
        return promotionService.findAllWithArticles();
    }

    // 14. Promotions expirées
    @GetMapping("/expired/{date}")
    public List<PromotionResponse> getExpired(@PathVariable String date) {
        return promotionService.findExpired(LocalDate.parse(date))
                .stream().map(promotionMappers::toDto).collect(Collectors.toList());
    }
    @PostMapping("/affecter/{idCommande}/client/{idClient}")
    public void affecterPromotionArticle(@PathVariable long idArticle, @PathVariable long idPromotion) {
        promotionService.affecterPromotionArticle(idArticle,  idPromotion);  // corrigé
    }

    @PostMapping("/desaffecter/{idArticlee}/article/{idPromotion}")
    public void desaffecterPromotionArticle (@PathVariable long idArticle, @PathVariable long idPromotion) {
        promotionService.desaffecterPromotionArticle( idArticle,  idPromotion);  // corrigé
    }


}