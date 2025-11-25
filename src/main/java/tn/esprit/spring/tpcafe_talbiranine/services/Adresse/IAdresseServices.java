package tn.esprit.spring.tpcafe_talbiranine.services.Adresse;

import tn.esprit.spring.tpcafe_talbiranine.dto.Adresse.AdresseRequest;
import tn.esprit.spring.tpcafe_talbiranine.dto.Adresse.AdresseResponse;
import tn.esprit.spring.tpcafe_talbiranine.entites.Adresse;
import tn.esprit.spring.tpcafe_talbiranine.entites.Client;

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

    // Méthodes utilisant DTO
    AdresseResponse addAdresse(AdresseRequest adresseRequest);
    AdresseResponse recupererAdresseParId(long id);
    List<AdresseResponse> trouverAdressesParVille(String ville);



    // 1. Trouver par code postal exact
    List<AdresseResponse> trouverParCodePostal(int codePostal);

    // 2. Compter adresses dans une ville
    long compterAdressesDansVille(String ville);

    // 3. Supprimer toutes les adresses d'une ville
    void supprimerParVille(String ville);

    // 4. Ville + code postal spécifique
    List<AdresseResponse> trouverParVilleEtCodePostal(String ville, int codePostal);

    // 5. Rue contient mot (ignore case)
    List<AdresseResponse> trouverParRueContenant(String mot);

    // 6. Adresses dans une liste de villes
    List<AdresseResponse> trouverParVilles(List<String> villes);

    // 7. Code postal dans une plage
    List<AdresseResponse> trouverParCodePostalEntre(int min, int max);

    // 8. Code postal >, >=, <, <=
    List<AdresseResponse> trouverParCodePostalSup(int min);
    List<AdresseResponse> trouverParCodePostalSupEgal(int min);
    List<AdresseResponse> trouverParCodePostalInf(int max);
    List<AdresseResponse> trouverParCodePostalInfEgal(int max);

    // 9. Rue commence par + ville + tri par code postal
    List<AdresseResponse> trouverRueCommenceEtVilleTrie(String prefix, String ville);

    // 10. Rue commence par
    List<AdresseResponse> trouverRueCommence(String prefix);

    // 11. Ville se termine par
    List<AdresseResponse> trouverVilleTermineePar(String suffix);

    // 12. Rue est null
    List<AdresseResponse> trouverRueNull();

    // 13. Ville non null
    List<AdresseResponse> trouverVilleNonNull();
    void ajouterEtAffecterAdresseAClient(Adresse adresse , Client client);
}
