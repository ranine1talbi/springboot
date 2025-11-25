package tn.esprit.spring.tpcafe_talbiranine.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.tpcafe_talbiranine.dto.Client.ClientRequest;
import tn.esprit.spring.tpcafe_talbiranine.dto.Client.ClientResponse;
import tn.esprit.spring.tpcafe_talbiranine.entites.Adresse;
import tn.esprit.spring.tpcafe_talbiranine.entites.Commande;
import tn.esprit.spring.tpcafe_talbiranine.entites.Promotion;
import tn.esprit.spring.tpcafe_talbiranine.entites.TypeArticle;
import tn.esprit.spring.tpcafe_talbiranine.services.Client.IClientServices;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("client")
@AllArgsConstructor
public class ClientRestController {

    private final IClientServices clientServices;

    // ================== CRUD de base ==================
    @GetMapping
    public List<ClientResponse> selectAllClients() {
        return clientServices.selectAllClients();
    }

    @PostMapping
    public ClientResponse addClient(@RequestBody ClientRequest client) {
        return clientServices.addClient(client);
    }

    @PostMapping("/all")
    public List<ClientResponse> addClients(@RequestBody List<ClientRequest> clients) {
        return clientServices.saveClients(clients);
    }

    @GetMapping("/{id}")
    public ClientResponse selectClientById(@PathVariable long id) {
        return clientServices.selectClientById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteClientById(@PathVariable long id) {
        clientServices.deleteClientById(id);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAllClients() {
        clientServices.deleteAllClients();
    }

    @GetMapping("/count")
    public long countClients() {
        return clientServices.countClients();
    }

    @GetMapping("/exists/{id}")
    public boolean verifClientById(@PathVariable long id) {
        return clientServices.verifClientById(id);
    }

    // ================== Recherches ==================

    @GetMapping("/nom/{nom}")
    public List<ClientResponse> findByNom(@PathVariable String nom) {
        return clientServices.findByNom(nom);
    }

    @GetMapping("/prenom/{prenom}")
    public List<ClientResponse> findByPrenom(@PathVariable String prenom) {
        return clientServices.findByPrenom(prenom);
    }

    @GetMapping("/search")
    public ClientResponse findByNomAndPrenom(
            @RequestParam String nom,
            @RequestParam String prenom) {
        return clientServices.findByNomAndPrenom(nom, prenom);
    }

    @GetMapping("/existsByNom/{nom}")
    public boolean existsByNom(@PathVariable String nom) {
        return clientServices.existsByNom(nom);
    }

    @GetMapping("/countAfter")
    public long countByDateNaissanceAfter(@RequestParam String date) {
        return clientServices.countByDateNaissanceAfter(LocalDate.parse(date));
    }

    @GetMapping("/search/keyword")
    public List<ClientResponse> findByNomOrPrenomContaining(@RequestParam String keyword) {
        return clientServices.findByNomOrPrenomContaining(keyword);
    }

    @GetMapping("/search/names")
    public List<ClientResponse> findByNomAndPrenomContaining(
            @RequestParam String nom,
            @RequestParam String prenom) {
        return clientServices.findByNomAndPrenomContaining(nom, prenom);
    }

    @GetMapping("/birth/between")
    public List<ClientResponse> findByDateNaissanceBetween(
            @RequestParam String start,
            @RequestParam String end) {
        return clientServices.findByDateNaissanceBetween(LocalDate.parse(start), LocalDate.parse(end));
    }

    @GetMapping("/search/nomPrefixDate")
    public List<ClientResponse> findByNomStartingWithAndDateNaissanceBefore(
            @RequestParam String prefix,
            @RequestParam String date) {
        return clientServices.findByNomStartingWithAndDateNaissanceBefore(prefix, LocalDate.parse(date));
    }

    @GetMapping("/ville/{ville}")
    public List<ClientResponse> findByVille(@PathVariable String ville) {
        return clientServices.findByVille(ville);
    }

    @GetMapping("/search/nomAsc")
    public List<ClientResponse> findByNomContainingOrderByPrenomAsc(@RequestParam String keyword) {
        return clientServices.findByNomContainingOrderByPrenomAsc(keyword);
    }

    @GetMapping("/search/nomDesc")
    public List<ClientResponse> findByNomContainingOrderByPrenomDesc(@RequestParam String keyword) {
        return clientServices.findByNomContainingOrderByPrenomDesc(keyword);
    }

    @GetMapping("/nomPrefix/{letter}")
    public List<ClientResponse> findByNomStartingWith(@PathVariable String letter) {
        return clientServices.findByNomStartingWith(letter);
    }

    @GetMapping("/prenomSuffix/{suffix}")
    public List<ClientResponse> findByPrenomEndingWith(@PathVariable String suffix) {
        return clientServices.findByPrenomEndingWith(suffix);
    }

    @GetMapping("/noBirthdate")
    public List<ClientResponse> findClientsWithoutDateNaissance() {
        return clientServices.findClientsWithoutDateNaissance();
    }

    @GetMapping("/withAdresse")
    public List<ClientResponse> findClientsWithAdresse() {
        return clientServices.findClientsWithAdresse();
    }

    @GetMapping("/villes")
    public List<ClientResponse> findClientsByVilles(@RequestParam List<String> villes) {
        return clientServices.findClientsByVilles(villes);
    }

    @GetMapping("/pts/greaterThan/{pts}")
    public List<ClientResponse> findClientsByPtsAccumulesGreaterThan(@PathVariable int pts) {
        return clientServices.findClientsByPtsAccumulesGreaterThan(pts);
    }

    @GetMapping("/pts/greaterOrEqual/{pts}")
    public List<ClientResponse> findClientsByPtsAccumulesGreaterOrEqual(@PathVariable int pts) {
        return clientServices.findClientsByPtsAccumulesGreaterOrEqual(pts);
    }

    @GetMapping("/pts/between")
    public List<ClientResponse> findClientsByPtsAccumulesBetween(
            @RequestParam int min,
            @RequestParam int max) {
        return clientServices.findClientsByPtsAccumulesBetween(min, max);
    }

    @GetMapping("/article/{name}")
    public List<ClientResponse> findClientsByArticleName(@PathVariable String name) {
        return clientServices.findClientsByArticleName(name);
    }

    @GetMapping("/search/nomArticleType")
    public List<ClientResponse> findClientsByNomContainsAndArticleType(
            @RequestParam String nom,
            @RequestParam String type) {

        TypeArticle typeArticle;
        try {
            typeArticle = TypeArticle.valueOf(type.toUpperCase()); // conversion String -> enum
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("TypeArticle invalide : " + type);
        }

        return clientServices.findClientsByNomContainsAndArticleType(nom, typeArticle);
    }
   /* @PostMapping("ajouterCommandeEtAffecterAClient")
    public void ajouterCommandeEtAffecterAClient(@RequestBody Commande commande , @RequestParam Adresse adresse , @RequestParam String nom, @RequestParam String prenom) {
        clientServices.ajouterCommandeEtAffecterAClient(Commande commande, String nomC, String prenomC);

    }*/
}
