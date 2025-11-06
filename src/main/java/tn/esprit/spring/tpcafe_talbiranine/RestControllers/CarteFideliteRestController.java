package tn.esprit.spring.tpcafe_talbiranine.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.tpcafe_talbiranine.dto.CartFidelite.CartFideliteRequest;
import tn.esprit.spring.tpcafe_talbiranine.dto.CartFidelite.CartFideliteResponse;
import tn.esprit.spring.tpcafe_talbiranine.services.CarteFidelite.ICarteFideliteServices;

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
}
