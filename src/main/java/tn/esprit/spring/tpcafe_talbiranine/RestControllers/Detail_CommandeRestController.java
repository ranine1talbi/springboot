package tn.esprit.spring.tpcafe_talbiranine.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.tpcafe_talbiranine.dto.Detail_Commande.Detail_CommandeRequest;
import tn.esprit.spring.tpcafe_talbiranine.dto.Detail_Commande.Detail_CommandeResponse;
import tn.esprit.spring.tpcafe_talbiranine.services.Detail_Commande.IDetail_CommandeServices;

import java.util.List;

@RestController
@RequestMapping("details-commandes")
@AllArgsConstructor
public class Detail_CommandeRestController {

    private final IDetail_CommandeServices detailCommandeServices;

    // ✅ Récupérer tous les détails commandes
    @GetMapping
    public List<Detail_CommandeResponse> selectAllDetailsCommande() {
        return detailCommandeServices.selectAllDetailsCommande();
    }

    // ✅ Ajouter un détail commande
    @PostMapping
    public Detail_CommandeResponse addDetailCommande(@RequestBody Detail_CommandeRequest request) {
        return detailCommandeServices.addDetailCommande(request);
    }

    // ✅ Ajouter plusieurs détails commandes
    @PostMapping("/addAll")
    public List<Detail_CommandeResponse> addAllDetailsCommande(@RequestBody List<Detail_CommandeRequest> requests) {
        return detailCommandeServices.saveDetailsCommande(requests);
    }

    // ✅ Récupérer un détail commande par ID
    @GetMapping("/{id}")
    public Detail_CommandeResponse displayDetailCommandeById(@PathVariable long id) {
        return detailCommandeServices.recupererDetailCommandeParId(id);
    }

    // ✅ Vérifier si un détail commande existe
    @GetMapping("/exists/{id}")
    public boolean verifDetailCommandeById(@PathVariable long id) {
        return detailCommandeServices.verifDetailCommandeById(id);
    }

    // ✅ Supprimer un détail commande par ID
    @DeleteMapping("/deleteById/{id}")
    public void deleteDetailCommandeById(@PathVariable long id) {
        detailCommandeServices.deleteDetailCommandeById(id);
    }

    // ✅ Supprimer tous les détails commandes
    @DeleteMapping("/deleteAll")
    public void deleteAllDetailsCommande() {
        detailCommandeServices.deleteAllDetailsCommande();
    }

    // ✅ Compter les détails commandes
    @GetMapping("/count")
    public long countDetailsCommande() {
        return detailCommandeServices.countDetailsCommande();
    }

    // 1️⃣ Trouver par quantité exacte
    @GetMapping("/byQuantite/{quantite}")
    public List<Detail_CommandeResponse> findByQuantiteArticle(@PathVariable int quantite) {
        return detailCommandeServices.findByQuantiteArticle(quantite);
    }

    // 2️⃣ Trouver par sous-total exact
    @GetMapping("/bySousTotal/{sousTotal}")
    public List<Detail_CommandeResponse> findBySousTotalExact(@PathVariable float sousTotal) {
        return detailCommandeServices.findBySousTotalExact(sousTotal);
    }

    // 3️⃣ Compter les détails avec plus de X quantités
    @GetMapping("/countByQuantiteGreaterThan/{quantite}")
    public long countByQuantiteGreaterThan(@PathVariable int quantite) {
        return detailCommandeServices.countByQuantiteGreaterThan(quantite);
    }

    // 4️⃣ Vérifier existence par sous-total
    @GetMapping("/existsBySousTotalGreaterThan/{montant}")
    public boolean existsBySousTotalGreaterThan(@PathVariable float montant) {
        return detailCommandeServices.existsBySousTotalGreaterThan(montant);
    }

    // 5️⃣ Quantité dans plage et sous-total minimum
    @GetMapping("/byQuantiteBetweenAndSousTotalMin")
    public List<Detail_CommandeResponse> findByQuantiteBetweenAndSousTotalMin(
            @RequestParam int minQte,
            @RequestParam int maxQte,
            @RequestParam float minSousTotal) {
        return detailCommandeServices.findByQuantiteBetweenAndSousTotalMin(minQte, maxQte, minSousTotal);
    }

    // 6️⃣ Sous-total dans plage triés par quantité
    @GetMapping("/bySousTotalBetweenOrderByQuantite")
    public List<Detail_CommandeResponse> findBySousTotalBetweenOrderByQuantite(
            @RequestParam float min,
            @RequestParam float max) {
        return detailCommandeServices.findBySousTotalBetweenOrderByQuantite(min, max);
    }

    // 7️⃣ Sous-total après promo dans plage
    @GetMapping("/bySousTotalApresPromoBetween")
    public List<Detail_CommandeResponse> findBySousTotalApresPromoBetween(
            @RequestParam float min,
            @RequestParam float max) {
        return detailCommandeServices.findBySousTotalApresPromoBetween(min, max);
    }

    // 8️⃣ Quantité ou sous-total minimum
    @GetMapping("/byQuantiteOrSousTotalMin")
    public List<Detail_CommandeResponse> findByQuantiteOrSousTotalMin(
            @RequestParam int quantite,
            @RequestParam float sousTotalMin) {
        return detailCommandeServices.findByQuantiteOrSousTotalMin(quantite, sousTotalMin);
    }

    // 9️⃣ Top 5 détails les plus chers
    @GetMapping("/top5MostExpensive")
    public List<Detail_CommandeResponse> findTop5MostExpensive() {
        return detailCommandeServices.findTop5MostExpensive();
    }

    // 🔟 Détails sans quantité
    @GetMapping("/withoutQuantite")
    public List<Detail_CommandeResponse> findWithoutQuantite() {
        return detailCommandeServices.findWithoutQuantite();
    }

    // 1️⃣1️⃣ Détails avec sous-total après promotion renseigné
    @GetMapping("/withSousTotalApresPromo")
    public List<Detail_CommandeResponse> findWithSousTotalApresPromo() {
        return detailCommandeServices.findWithSousTotalApresPromo();
    }

    // 1️⃣2️⃣ Tous les détails avec commande et article
    @GetMapping("/withCommandeAndArticle")
    public List<Detail_CommandeResponse> findAllWithCommandeAndArticle() {
        return detailCommandeServices.findAllWithCommandeAndArticle();
    }
}