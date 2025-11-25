package tn.esprit.spring.tpcafe_talbiranine.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.tpcafe_talbiranine.dto.CartFidelite.CartFideliteRequest;
import tn.esprit.spring.tpcafe_talbiranine.dto.CartFidelite.CartFideliteResponse;
import tn.esprit.spring.tpcafe_talbiranine.services.CarteFidelite.ICarteFideliteServices;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("cartefidelite")
@AllArgsConstructor
public class CarteFideliteRestController {

    private final ICarteFideliteServices carteFideliteServices;

    // 🔹 Récupérer toutes les cartes
    @GetMapping
    public List<CartFideliteResponse> selectAllCartes() {
        return carteFideliteServices.selectAllCartes();
    }

    // 🔹 Ajouter une carte
    @PostMapping
    public CartFideliteResponse addCarte(@RequestBody CartFideliteRequest carte) {
        return carteFideliteServices.addCarteFidelite(carte);
    }

    // 🔹 Ajouter plusieurs cartes
    @PostMapping("addcartes")
    public List<CartFideliteResponse> addCartes(@RequestBody List<CartFideliteRequest> cartes) {
        return carteFideliteServices.saveCartesFidelite(cartes);
    }

    // 🔹 Sélection par ID
    @GetMapping("selectedbyId/{id}")
    public CartFideliteResponse displayCarteById(@PathVariable long id) {
        return carteFideliteServices.recupererCarteParId(id);
    }

    // 🔹 Supprimer une carte par ID
    @DeleteMapping("deleteById/{id}")
    public void deleteCarteById(@PathVariable long id) {
        carteFideliteServices.deleteCarteById(id);
    }

    // 🔹 Supprimer toutes les cartes
    @DeleteMapping("deleteAll")
    public void deleteAllCartes() {
        carteFideliteServices.deleteAllCartes();
    }

    // 🔹 Compter le nombre de cartes
    @GetMapping("count")
    public long countCartes() {
        return carteFideliteServices.countCartes();
    }

    // 🔹 Vérifier si une carte existe par ID
    @GetMapping("exists/{id}")
    public boolean verifCarteById(@PathVariable long id) {
        return carteFideliteServices.verifCarteById(id);
    }

    // ----------------- Endpoints JPQL avancés -----------------

    // 🔹 Trouver cartes par points exacts
    @GetMapping("points/{points}")
    public List<CartFideliteResponse> findByPointsExact(@PathVariable Integer points) {
        return carteFideliteServices.findByPointsExact(points);
    }

    // 🔹 Trouver cartes créées à une date spécifique
    @GetMapping("date/{date}")
    public List<CartFideliteResponse> findByDateCreation(@PathVariable String date) {
        return carteFideliteServices.findByDateCreation(LocalDate.parse(date));
    }

    // 🔹 Trouver cartes avec plus de X points
    @GetMapping("pointsGreaterThan/{points}")
    public Long countByPointsGreaterThan(@PathVariable Integer points) {
        return carteFideliteServices.countByPointsGreaterThan(points);
    }

    // 🔹 Trouver top 5 cartes avec le plus de points
    @GetMapping("top5")
    public List<CartFideliteResponse> findTop5ByPoints() {
        return carteFideliteServices.findTop5ByPoints();
    }

    // 🔹 Supprimer cartes créées avant une date
    @DeleteMapping("deleteBefore/{date}")
    public void deleteByDateBefore(@PathVariable String date) {
        carteFideliteServices.deleteByDateBefore(LocalDate.parse(date));
    }
    @PutMapping("/affecter/{idCarte}/{idClient}")
    public void affecterCarteAClient(@PathVariable long idCarte, @PathVariable long idClient) {
        carteFideliteServices.affecterCarteAClient(idCarte, idClient);
    }

    @PutMapping("/desaffecter/{idCarte}/{idClient}")
    public void desaffecterCarteAClient(@PathVariable long idCarte, @PathVariable long idClient) {
        carteFideliteServices.desaffecterCarteAClient(idCarte, idClient);
    }


    // Tu peux continuer à exposer les autres méthodes JPQL similaires...
}

