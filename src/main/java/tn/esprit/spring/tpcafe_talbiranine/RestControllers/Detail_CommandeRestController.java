package tn.esprit.spring.tpcafe_talbiranine.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.tpcafe_talbiranine.entites.Detail_Commande;
import tn.esprit.spring.tpcafe_talbiranine.services.Detail_Commande.Detail_CommandeService;

import java.util.List;

@RestController
@RequestMapping("details-commandes")
@AllArgsConstructor
public class Detail_CommandeRestController {

    private final Detail_CommandeService detailCommandeService;

    @PostMapping("/add")
    public Detail_Commande addDetailCommande(@RequestBody Detail_Commande detail) {
        return detailCommandeService.addDetailCommande(detail);
    }

    @PostMapping("/addAll")
    public List<Detail_Commande> addAllDetailsCommande(@RequestBody List<Detail_Commande> details) {
        return detailCommandeService.saveDetailsCommande(details);
    }

    @GetMapping("/all")
    public List<Detail_Commande> getAllDetailsCommande() {
        return detailCommandeService.selectAllDetailsCommande();
    }

    @GetMapping("/{id}")
    public Detail_Commande getDetailCommandeById(@PathVariable long id) {
        return detailCommandeService.selectDetailCommandeById(id);
    }

    @GetMapping("/exists/{id}")
    public boolean existsById(@PathVariable long id) {
        return detailCommandeService.verifDetailCommandeById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteDetailCommandeById(@PathVariable long id) {
        detailCommandeService.deleteDetailCommandeById(id);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAllDetailsCommande() {
        detailCommandeService.deleteAllDetailsCommande();
    }

    @GetMapping("/count")
    public long countDetailsCommande() {
        return detailCommandeService.countDetailsCommande();
    }
}
