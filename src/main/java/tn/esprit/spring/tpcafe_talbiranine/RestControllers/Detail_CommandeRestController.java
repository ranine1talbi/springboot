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
}
