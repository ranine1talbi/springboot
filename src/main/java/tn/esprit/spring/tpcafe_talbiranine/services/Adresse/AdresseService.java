package tn.esprit.spring.tpcafe_talbiranine.services.Adresse;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.tpcafe_talbiranine.dto.Adresse.AdresseRequest;
import tn.esprit.spring.tpcafe_talbiranine.dto.Adresse.AdresseResponse;
import tn.esprit.spring.tpcafe_talbiranine.entites.Adresse;
import tn.esprit.spring.tpcafe_talbiranine.entites.Client;
import tn.esprit.spring.tpcafe_talbiranine.mapper.Adresse.AdresseMappers;
import tn.esprit.spring.tpcafe_talbiranine.repositories.AdresseRepository;
import tn.esprit.spring.tpcafe_talbiranine.repositories.ClientRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AdresseService implements IAdresseServices {

    private final AdresseRepository adresseRepository;
    private final AdresseMappers adresseMapper;
    private ClientRepository clientRepository;
    // ----------------------------------------------------
    // CRUD + DTO
    // ----------------------------------------------------

    @Override
    public AdresseResponse addAdresse(AdresseRequest adresseRequest) {
        Adresse adresse = adresseMapper.toEntity(adresseRequest);
        Adresse saved = adresseRepository.save(adresse);
        return adresseMapper.toDto(saved);
    }

    @Override
    public AdresseResponse recupererAdresseParId(long id) {
        Adresse a = adresseRepository.findById(id).orElse(null);
        return (a != null) ? adresseMapper.toDto(a) : null;
    }

    @Override
    public List<AdresseResponse> saveAdresses(List<AdresseRequest> adressesRequest) {
        List<Adresse> adresses = adressesRequest.stream()
                .map(adresseMapper::toEntity)
                .collect(Collectors.toList());

        List<Adresse> saved = adresseRepository.saveAll(adresses);

        return saved.stream()
                .map(adresseMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AdresseResponse> selectAllAdresses() {
        return adresseRepository.findAll()
                .stream()
                .map(adresseMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAdresse(Adresse adresse) {
        adresseRepository.delete(adresse);
    }

    @Override
    public void deleteAdresseById(long id) {
        adresseRepository.deleteById(id);
    }

    @Override
    public void deleteAllAdresses() {
        adresseRepository.deleteAll();
    }

    @Override
    public long countAdresses() {
        return adresseRepository.count();
    }

    @Override
    public boolean verifAdresseById(long id) {
        return adresseRepository.existsById(id);
    }

    @Override
    public List<AdresseResponse> trouverAdressesParVille(String ville) {
        return adresseRepository.findByVille(ville)
                .stream()
                .map(adresseMapper::toDto)
                .collect(Collectors.toList());
    }

    // ----------------------------------------------------
    //           MÉTHODES AVANCÉES DEMANDÉES
    // ----------------------------------------------------

    @Override
    public List<AdresseResponse> trouverParCodePostal(int codePostal) {
        return adresseRepository.findByCodePostal(codePostal)
                .stream()
                .map(adresseMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public long compterAdressesDansVille(String ville) {
        return adresseRepository.countByVille(ville);
    }

    @Override
    public void supprimerParVille(String ville) {
        adresseRepository.deleteByVille(ville);
    }

    @Override
    public List<AdresseResponse> trouverParVilleEtCodePostal(String ville, int codePostal) {
        return adresseRepository.findByVilleAndCodePostal(ville, codePostal)
                .stream()
                .map(adresseMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AdresseResponse> trouverParRueContenant(String mot) {
        return adresseRepository.findByRueContainingIgnoreCase(mot)
                .stream()
                .map(adresseMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AdresseResponse> trouverParVilles(List<String> villes) {
        return adresseRepository.findByVilleIn(villes)
                .stream()
                .map(adresseMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AdresseResponse> trouverParCodePostalEntre(int min, int max) {
        return adresseRepository.findByCodePostalBetween(min, max)
                .stream()
                .map(adresseMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AdresseResponse> trouverParCodePostalSup(int min) {
        return adresseRepository.findByCodePostalGreaterThan(min)
                .stream()
                .map(adresseMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AdresseResponse> trouverParCodePostalSupEgal(int min) {
        return adresseRepository.findByCodePostalGreaterThanEqual(min)
                .stream()
                .map(adresseMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AdresseResponse> trouverParCodePostalInf(int max) {
        return adresseRepository.findByCodePostalLessThan(max)
                .stream()
                .map(adresseMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AdresseResponse> trouverParCodePostalInfEgal(int max) {
        return adresseRepository.findByCodePostalLessThanEqual(max)
                .stream()
                .map(adresseMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AdresseResponse> trouverRueCommenceEtVilleTrie(String prefix, String ville) {
        return adresseRepository.findByRueStartingWithAndVilleOrderByCodePostal(prefix, ville)
                .stream()
                .map(adresseMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AdresseResponse> trouverRueCommence(String prefix) {
        return adresseRepository.findByRueStartingWith(prefix)
                .stream()
                .map(adresseMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AdresseResponse> trouverVilleTermineePar(String suffix) {
        return adresseRepository.findByVilleEndingWith(suffix)
                .stream()
                .map(adresseMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AdresseResponse> trouverRueNull() {
        return adresseRepository.findByRueIsNull()
                .stream()
                .map(adresseMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AdresseResponse> trouverVilleNonNull() {
        return adresseRepository.findByVilleIsNotNull()
                .stream()
                .map(adresseMapper::toDto)
                .collect(Collectors.toList());
    }
    @Override
    public void ajouterEtAffecterAdresseAClient(Adresse adresse, Client client) {
        client.setAdresse(adresse);
        clientRepository.save(client); // ✔ on utilise l'instance
    }
}
