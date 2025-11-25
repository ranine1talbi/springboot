package tn.esprit.spring.tpcafe_talbiranine.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.tpcafe_talbiranine.dto.Adresse.AdresseRequest;
import tn.esprit.spring.tpcafe_talbiranine.dto.Adresse.AdresseResponse;
import tn.esprit.spring.tpcafe_talbiranine.entites.Adresse;
import tn.esprit.spring.tpcafe_talbiranine.entites.Client;
import tn.esprit.spring.tpcafe_talbiranine.services.Adresse.AdresseService;
import tn.esprit.spring.tpcafe_talbiranine.services.Adresse.IAdresseServices;

import java.util.List;

@RestController
@RequestMapping("adresse")
@AllArgsConstructor
public class AdresseRestController {

    private final IAdresseServices adresseServices;
    private AdresseService service;

    // ----------------------------------------------------
    // CRUD de base
    // ----------------------------------------------------

    @GetMapping
    public List<AdresseResponse> selectAllAdresses() {
        return adresseServices.selectAllAdresses();
    }

    @PostMapping
    public AdresseResponse addAdresse(@RequestBody AdresseRequest adresse) {
        return adresseServices.addAdresse(adresse);
    }

    @PostMapping("addadresse")
    public List<AdresseResponse> addAdresse(@RequestBody List<AdresseRequest> adresses) {
        return adresseServices.saveAdresses(adresses);
    }

    @GetMapping("selectedbyId/{id}")
    public AdresseResponse getAdresseById(@PathVariable long id) {
        return adresseServices.recupererAdresseParId(id);
    }

    @DeleteMapping("deleteById/{id}")
    public void deleteAdresseById(@PathVariable long id) {
        adresseServices.deleteAdresseById(id);
    }

    @DeleteMapping("deleteAll")
    public void deleteAllAdresses() {
        adresseServices.deleteAllAdresses();
    }

    @GetMapping("count")
    public long countAdresses() {
        return adresseServices.countAdresses();
    }

    @GetMapping("exists/{id}")
    public boolean verifAdresseById(@PathVariable long id) {
        return adresseServices.verifAdresseById(id);
    }

    // ----------------------------------------------------
    // MÉTHODES AVANCÉES
    // ----------------------------------------------------

    // 1. Ville
    @GetMapping("/ville/{ville}")
    public List<AdresseResponse> getAdressesByVille(@PathVariable String ville) {
        return adresseServices.trouverAdressesParVille(ville);
    }

    // 2. Code postal exact
    @GetMapping("/code-postal/{cp}")
    public List<AdresseResponse> getByCodePostal(@PathVariable int cp) {
        return adresseServices.trouverParCodePostal(cp);
    }

    // 3. Compter adresses par ville
    @GetMapping("/ville/{ville}/count")
    public long countByVille(@PathVariable String ville) {
        return adresseServices.compterAdressesDansVille(ville);
    }

    // 4. Supprimer par ville
    @DeleteMapping("/ville/{ville}")
    public void deleteByVille(@PathVariable String ville) {
        adresseServices.supprimerParVille(ville);
    }

    // 5. Ville + code postal
    @GetMapping("/ville/{ville}/cp/{cp}")
    public List<AdresseResponse> getByVilleAndCP(@PathVariable String ville, @PathVariable int cp) {
        return adresseServices.trouverParVilleEtCodePostal(ville, cp);
    }

    // 6. Rue contenant mot (ignore case)
    @GetMapping("/rue/contains/{mot}")
    public List<AdresseResponse> getRueContains(@PathVariable String mot) {
        return adresseServices.trouverParRueContenant(mot);
    }

    // 7. Liste de villes
    @PostMapping("/villes")
    public List<AdresseResponse> getByVilles(@RequestBody List<String> villes) {
        return adresseServices.trouverParVilles(villes);
    }

    // 8. Code postal entre
    @GetMapping("/cp/between/{min}/{max}")
    public List<AdresseResponse> getCpBetween(@PathVariable int min, @PathVariable int max) {
        return adresseServices.trouverParCodePostalEntre(min, max);
    }

    // 9. Code postal >
    @GetMapping("/cp/gt/{min}")
    public List<AdresseResponse> getCpGreater(@PathVariable int min) {
        return adresseServices.trouverParCodePostalSup(min);
    }

    // 10. Code postal >=
    @GetMapping("/cp/gte/{min}")
    public List<AdresseResponse> getCpGreaterEqual(@PathVariable int min) {
        return adresseServices.trouverParCodePostalSupEgal(min);
    }

    // 11. Code postal <
    @GetMapping("/cp/lt/{max}")
    public List<AdresseResponse> getCpLess(@PathVariable int max) {
        return adresseServices.trouverParCodePostalInf(max);
    }

    // 12. Code postal <=
    @GetMapping("/cp/lte/{max}")
    public List<AdresseResponse> getCpLessEqual(@PathVariable int max) {
        return adresseServices.trouverParCodePostalInfEgal(max);
    }

    // 13. Rue commence par + ville + tri
    @GetMapping("/rue/start/{prefix}/ville/{ville}")
    public List<AdresseResponse> getRueStartVilleSorted(
            @PathVariable String prefix,
            @PathVariable String ville
    ) {
        return adresseServices.trouverRueCommenceEtVilleTrie(prefix, ville);
    }

    // 14. Rue commence par
    @GetMapping("/rue/start/{prefix}")
    public List<AdresseResponse> getRueStart(@PathVariable String prefix) {
        return adresseServices.trouverRueCommence(prefix);
    }

    // 15. Ville se termine par
    @GetMapping("/ville/end/{suffix}")
    public List<AdresseResponse> getVilleEnds(@PathVariable String suffix) {
        return adresseServices.trouverVilleTermineePar(suffix);
    }

    // 16. Rue null
    @GetMapping("/rue/null")
    public List<AdresseResponse> getRueNull() {
        return adresseServices.trouverRueNull();
    }

    @PostMapping("ajouterEtAffecterAdresseAClient")
     public void ajouterEtAffecterAdresseAClient(@RequestBody Adresse adresse ,@RequestBody Client client)
    {
        service.ajouterEtAffecterAdresseAClient(adresse , client);
}


    // 17. Ville non null
    @GetMapping("/ville/not-null")
    public List<AdresseResponse> getVilleNotNull() {
        return adresseServices.trouverVilleNonNull();
    }
}

