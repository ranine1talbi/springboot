package tn.esprit.spring.tpcafe_talbiranine.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.tpcafe_talbiranine.dto.Adresse.AdresseRequest;
import tn.esprit.spring.tpcafe_talbiranine.dto.Adresse.AdresseResponse;
import tn.esprit.spring.tpcafe_talbiranine.entites.Adresse;
import tn.esprit.spring.tpcafe_talbiranine.services.Adresse.IAdresseServices;
import java.util.List;

@RestController
@RequestMapping("adresse")
@AllArgsConstructor
public class AdresseRestController {

    private final IAdresseServices adresseServices;

    @GetMapping
    public List<AdresseResponse> selectAllAdresses() {
        return adresseServices.selectAllAdresses();
    }

    @PostMapping
    public AdresseResponse addAdresse(@RequestBody AdresseRequest adresse) {
        return adresseServices.addAdresse(adresse);
    }
        @PostMapping("addadresse")
        public List<AdresseResponse> addAdresse(@RequestBody List<AdresseRequest> adresse){
            return adresseServices.saveAdresses(adresse);
        }
       /* @GetMapping("selectById/{id}")
    public Adresse selectAdresseById(@PathVariable long id) {
        return adresseServices.selectAdresseByIdWithGet(id);
        }
        @GetMapping("selectById2")
public Adresse selectAdresseById2(@RequestParam long id) {
    return adresseServices.selectAdresseByIdWithOrElse(id);}*/
    @GetMapping("selectedbyId/{id}")
    public AdresseResponse displayadressesbyid(@PathVariable long id){
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
}
