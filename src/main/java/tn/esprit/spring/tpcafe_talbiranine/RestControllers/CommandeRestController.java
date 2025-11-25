package tn.esprit.spring.tpcafe_talbiranine.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.tpcafe_talbiranine.dto.Commande.CommandeRequest;
import tn.esprit.spring.tpcafe_talbiranine.dto.Commande.CommandeResponse;
import tn.esprit.spring.tpcafe_talbiranine.entites.StatusCommande;
import tn.esprit.spring.tpcafe_talbiranine.services.Commande.CommandeService;
import tn.esprit.spring.tpcafe_talbiranine.services.Commande.ICommandeServices;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("commande")
@AllArgsConstructor
public class CommandeRestController {

    private final ICommandeServices commandeServices;

    // ------------------- CRUD de base -------------------

    @GetMapping
    public List<CommandeResponse> selectAllCommandes() {
        return commandeServices.selectAllCommandes();
    }

    @PostMapping
    public CommandeResponse addCommande(@RequestBody CommandeRequest commandeRequest) {
        return commandeServices.addCommande(commandeRequest);
    }

    @PostMapping("/all")
    public List<CommandeResponse> addCommandes(@RequestBody List<CommandeRequest> commandes) {
        return commandeServices.saveCommandes(commandes);
    }

    @GetMapping("/{id}")
    public CommandeResponse selectCommandeById(@PathVariable long id) {
        return commandeServices.selectCommandeById(id);
    }

    @DeleteMapping("deleteById/{id}")
    public void deleteCommandeById(@PathVariable long id) {
        commandeServices.deleteCommandeById(id);
    }

    @DeleteMapping("deleteAll")
    public void deleteAllCommandes() {
        commandeServices.deleteAllCommandes();
    }

    @GetMapping("count")
    public long countCommandes() {
        return commandeServices.countCommandes();
    }

    @GetMapping("exists/{id}")
    public boolean verifCommandeById(@PathVariable long id) {
        return commandeServices.verifCommandeById(id);
    }

    // ------------------- Méthodes avancées -------------------

    @GetMapping("status/{status}")
    public List<CommandeResponse> findByStatus(@PathVariable StatusCommande status) {
        return commandeServices.findByStatus(status);
    }

    @GetMapping("date/{date}")
    public List<CommandeResponse> findByDate(@PathVariable String date) {
        return commandeServices.findByDate(LocalDate.parse(date));
    }

    @GetMapping("count/status/{status}")
    public long countByStatus(@PathVariable StatusCommande status) {
        return commandeServices.countByStatus(status);
    }

    @DeleteMapping("deleteBefore/{date}")
    public void deleteBeforeDate(@PathVariable String date) {
        commandeServices.deleteBeforeDate(LocalDate.parse(date));
    }

    @GetMapping("between/{start}/{end}/status/{status}")
    public List<CommandeResponse> findBetweenDatesWithStatus(
            @PathVariable String start,
            @PathVariable String end,
            @PathVariable StatusCommande status) {
        return commandeServices.findBetweenDatesWithStatus(
                LocalDate.parse(start),
                LocalDate.parse(end),
                status
        );
    }

    @GetMapping("totalGreater/{total}/statusNot/{status}")
    public List<CommandeResponse> findTotalGreaterThanAndStatusNot(
            @PathVariable float total,
            @PathVariable StatusCommande status) {
        return commandeServices.findTotalGreaterThanAndStatusNot(total, status);
    }

    @GetMapping("statuses/orderByDateDesc")
    public List<CommandeResponse> findByStatusesOrderByDateDesc(@RequestParam List<StatusCommande> statuses) {
        return commandeServices.findByStatusesOrderByDateDesc(statuses);
    }

    @GetMapping("before/{date}/totalBetween")
    public List<CommandeResponse> findBeforeDateWithTotalBetween(
            @PathVariable String date,
            @RequestParam float min,
            @RequestParam float max) {
        return commandeServices.findBeforeDateWithTotalBetween(LocalDate.parse(date), min, max);
    }

    @GetMapping("statusEnding/{letter}")
    public List<CommandeResponse> findByStatusEndingWith(@PathVariable String letter) {
        return commandeServices.findByStatusEndingWith(letter);
    }

    @GetMapping("withoutStatus")
    public List<CommandeResponse> findWithoutStatus() {
        return commandeServices.findWithoutStatus();
    }

    @GetMapping("withTotal")
    public List<CommandeResponse> findWithTotal() {
        return commandeServices.findWithTotal();
    }

    @GetMapping("withDetailsAndClient")
    public List<CommandeResponse> findAllWithDetailsAndClient() {
        return commandeServices.findAllWithDetailsAndClient();
    }

    @GetMapping("top3Recent")
    public List<CommandeResponse> findTop3Recent() {
        return commandeServices.findTop3Recent();
    }

    @PostMapping("/affecter/{idCommande}/client/{idClient}")
    public void affecterCommandeAClient(@PathVariable long idCommande, @PathVariable long idClient) {
        commandeServices.affecterCommandeAClient(idCommande, idClient);  // corrigé
    }

    @PostMapping("/desaffecter/{idCommande}/client/{idClient}")
    public void desaffecterClientDeCommande(@PathVariable long idCommande, @PathVariable long idClient) {
        commandeServices.desaffecterClientDeCommande(idCommande, idClient);  // corrigé
    }
}
