package tn.esprit.spring.tpcafe_talbiranine.services.Adresse;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.tpcafe_talbiranine.dto.Adresse.AdresseRequest;
import tn.esprit.spring.tpcafe_talbiranine.dto.Adresse.AdresseResponse;
import tn.esprit.spring.tpcafe_talbiranine.entites.Adresse;
import tn.esprit.spring.tpcafe_talbiranine.mapper.Adresse.AdresseMappers;
import tn.esprit.spring.tpcafe_talbiranine.repositories.AdresseRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AdresseService implements IAdresseServices {

    private final AdresseRepository adresseRepository;
    private final AdresseMappers adresseMapper;

    // ✅ Ajouter une adresse via DTO
    @Override
    public AdresseResponse addAdresse(AdresseRequest adresseRequest) {
        Adresse adresse = adresseMapper.toEntity(adresseRequest);
        Adresse saved = adresseRepository.save(adresse);
        return adresseMapper.toDto(saved);
    }

    // ✅ Récupérer via DTO
    @Override
    public AdresseResponse recupererAdresseParId(long id) {
        Adresse a = adresseRepository.findById(id).orElse(null);
        return (a != null) ? adresseMapper.toDto(a) : null;
    }

    // ✅ CRUD de base
    /*@Override
    public Adresse addAdresse(Adresse adresse) {
        return adresseRepository.save(adresse);
    }*/

    @Override
    public List<AdresseResponse> saveAdresses(List<AdresseRequest> adressesRequest) {
        List<Adresse> adresses = adressesRequest.stream()
                .map(adresseMapper::toEntity)
                .collect(Collectors.toList());
        List<Adresse> saved = adresseRepository.saveAll(adresses);
        return saved.stream().map(adresseMapper::toDto).collect(Collectors.toList());
    }

    /*@Override
    public Adresse selectAdresseById(long id) {
        return adresseRepository.findById(id).orElse(null);
    }*/

    @Override
    public List<AdresseResponse> selectAllAdresses() {
        return adresseRepository.findAll().stream().map(adresseMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void deleteAdresse(Adresse adresse) {
        adresseRepository.delete(adresse);
    }

    @Override
    public void deleteAllAdresses() {
        adresseRepository.deleteAll();
    }

    @Override
    public void deleteAdresseById(long id) {
        adresseRepository.deleteById(id);
    }

    @Override
    public long countAdresses() {
        return adresseRepository.count();
    }

    @Override
    public boolean verifAdresseById(long id) {
        return adresseRepository.existsById(id);
    }

    // ✅ Gestion des Optionals
    /*@Override
    public Adresse selectAdresseByIdWithGet(long id) {
        return adresseRepository.findById(id).get();
    }

    @Override
    public Adresse selectAdresseByIdWithOrElse(long id) {
        Adresse adresseDefaut = Adresse.builder()
                .rue("Rue de Tunis")
                .ville("Tunis")
                .codePostal(1000)
                .build();
        return adresseRepository.findById(id).orElse(adresseDefaut);
    }*/
}
