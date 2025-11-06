package tn.esprit.spring.tpcafe_talbiranine.services.Adresse;

import tn.esprit.spring.tpcafe_talbiranine.dto.Adresse.AdresseRequest;
import tn.esprit.spring.tpcafe_talbiranine.dto.Adresse.AdresseResponse;
import tn.esprit.spring.tpcafe_talbiranine.entites.Adresse;

import java.util.List;

public interface IAdresseServices {
    // CRUD de base
    //Adresse addAdresse(Adresse adresse);
    List<AdresseResponse> saveAdresses(List<AdresseRequest> adressesRequest);
    //Adresse selectAdresseById(long id);
    List<AdresseResponse> selectAllAdresses();
    void deleteAdresse(Adresse adresse);
    void deleteAllAdresses();
    void deleteAdresseById(long id);
    long countAdresses();
    boolean verifAdresseById(long id);

    // Variantes avec Optional
    //Adresse selectAdresseByIdWithOrElse(long id);
    //Adresse selectAdresseByIdWithGet(long id);

    // Méthodes utilisant DTO
    AdresseResponse addAdresse(AdresseRequest adresseRequest);
    AdresseResponse recupererAdresseParId(long id);
}
