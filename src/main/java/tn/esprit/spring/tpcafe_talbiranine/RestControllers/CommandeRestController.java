package tn.esprit.spring.tpcafe_talbiranine.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.tpcafe_talbiranine.entites.Commande;
import tn.esprit.spring.tpcafe_talbiranine.services.Commande.ICommandeServices;

import java.util.List;

@RestController
@RequestMapping("commande")
@AllArgsConstructor
public class CommandeRestController {

    private final ICommandeServices commandeServices;

    // 🔹 GET : récupérer toutes les commandes
    @GetMapping
    public List<Commande> selectAllCommandes() {
        return commandeServices.selectAllCommandes();
    }

    // 🔹 POST : ajouter une commande
    @PostMapping
    public Commande addCommande(@RequestBody Commande commande) {
        return commandeServices.addCommande(commande);
    }

    // 🔹 POST : ajouter plusieurs commandes
    @PostMapping("/all")
    public List<Commande> addCommandes(@RequestBody List<Commande> commandes) {
        return commandeServices.saveCommandes(commandes);
    }

    // 🔹 GET : récupérer une commande par ID
    @GetMapping("/{id}")
    public Commande selectCommandeById(@PathVariable long id) {
        return commandeServices.selectCommandeById(id);
    }

    // 🔹 DELETE : supprimer une commande par ID
    @DeleteMapping("deleteById/{id}")
    public void deleteCommandeById(@PathVariable long id) {
        commandeServices.deleteCommandeById(id);
    }

    // 🔹 DELETE : supprimer toutes les commandes
    @DeleteMapping("deleteAll")
    public void deleteAllCommandes() {
        commandeServices.deleteAllCommandes();
    }

    // 🔹 GET : compter le nombre de commandes
    @GetMapping("count")
    public long countCommandes() {
        return commandeServices.countCommandes();
    }

    // 🔹 GET : vérifier si une commande existe par ID
    @GetMapping("exists/{id}")
    public boolean verifCommandeById(@PathVariable long id) {
        return commandeServices.verifCommandeById(id);
    }
}
