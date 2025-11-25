package tn.esprit.spring.tpcafe_talbiranine.services.CarteFidelite;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import tn.esprit.spring.tpcafe_talbiranine.dto.CartFidelite.CartFideliteRequest;
import tn.esprit.spring.tpcafe_talbiranine.dto.CartFidelite.CartFideliteResponse;
import tn.esprit.spring.tpcafe_talbiranine.entites.CarteFidelite;
import tn.esprit.spring.tpcafe_talbiranine.entites.Client;
import tn.esprit.spring.tpcafe_talbiranine.mapper.CartFidelite.CartFideliteMappers;
import tn.esprit.spring.tpcafe_talbiranine.repositories.CarteFideliteRepository;
import tn.esprit.spring.tpcafe_talbiranine.repositories.ClientRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CarteFideliteService implements ICarteFideliteServices {

    private final CarteFideliteRepository carteFideliteRepository;
    private final ClientRepository clientRepository;
    private final CartFideliteMappers carteFideliteMapper;

    // Ajouter une carte via DTO
    @Override
    public CartFideliteResponse addCarteFidelite(CartFideliteRequest carteRequest) {
        CarteFidelite carte = carteFideliteMapper.toEntity(carteRequest);
        if (carteRequest.getClientId() != null) {
            Client client = clientRepository.findById(carteRequest.getClientId()).orElse(null);
            carte.setClient(client);
        }
        CarteFidelite saved = carteFideliteRepository.save(carte);
        return carteFideliteMapper.toDto(saved);
    }
    @Override
    public void affecterCarteAClient(long idClient, long idCarteFidelite) {
        CarteFidelite carteFidelite = carteFideliteRepository.findById(idCarteFidelite).get();
        Client client = clientRepository.findById(idClient).get();
        client.getCarteFidelite().add(carteFidelite);
        clientRepository.save(client);
    }


    @Override
    public void desaffecterCarteAClient(long idClient, long idCarteFidelite) {
        CarteFidelite carteFidelite = carteFideliteRepository.findById(idCarteFidelite).get();
        Client client = clientRepository.findById(idClient).get();
       client.getCarteFidelite().remove(carteFidelite);
        clientRepository.save(client);
    }

    // Ajouter plusieurs cartes
    @Override
    public List<CartFideliteResponse> saveCartesFidelite(List<CartFideliteRequest> cartesRequest) {
        List<CarteFidelite> cartes = cartesRequest.stream()
                .map(carteFideliteMapper::toEntity)
                .collect(Collectors.toList());

        for (int i = 0; i < cartes.size(); i++) {
            Long clientId = cartesRequest.get(i).getClientId();
            if (clientId != null) {
                Client client = clientRepository.findById(clientId).orElse(null);
                cartes.get(i).setClient(client);
            }
        }

        List<CarteFidelite> saved = carteFideliteRepository.saveAll(cartes);
        return saved.stream().map(carteFideliteMapper::toDto).collect(Collectors.toList());
    }

    // Récupérer toutes les cartes
    @Override
    public List<CartFideliteResponse> selectAllCartes() {
        return carteFideliteRepository.findAll()
                .stream()
                .map(carteFideliteMapper::toDto)
                .collect(Collectors.toList());
    }

    // Récupérer une carte par ID
    @Override
    public CartFideliteResponse recupererCarteParId(long id) {
        CarteFidelite carte = carteFideliteRepository.findById(id).orElse(null);
        return (carte != null) ? carteFideliteMapper.toDto(carte) : null;
    }

    // Suppressions et opérations de base
    @Override
    public void deleteCarte(CarteFidelite carte) {
        carteFideliteRepository.delete(carte);
    }

    @Override
    public void deleteAllCartes() {
        carteFideliteRepository.deleteAll();
    }

    @Override
    public void deleteCarteById(long id) {
        carteFideliteRepository.deleteById(id);
    }

    @Override
    public long countCartes() {
        return carteFideliteRepository.count();
    }

    @Override
    public boolean verifCarteById(long id) {
        return carteFideliteRepository.existsById(id);
    }

    // Méthodes avancées

    @Override
    public List<CartFideliteResponse> findByPointsExact(Integer points) {
        return carteFideliteRepository.findByPointsAcumules(points)
                .stream()
                .map(carteFideliteMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CartFideliteResponse> findByDateCreation(LocalDate date) {
        return carteFideliteRepository.findByDateCreation(date)
                .stream()
                .map(carteFideliteMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Long countByPointsGreaterThan(Integer points) {
        return carteFideliteRepository.countByPointsAcumulesGreaterThan(points);
    }

    @Override
    @Transactional
    public void deleteByDateBefore(LocalDate date) {
        carteFideliteRepository.deleteByDateCreationBefore(date);
    }

    @Override
    public List<CartFideliteResponse> findByPointsBetweenAndDateAfter(Integer minPoints, Integer maxPoints, LocalDate date) {
        return carteFideliteRepository.findByPointsAcumulesBetweenAndDateCreationAfter(minPoints, maxPoints, date)
                .stream()
                .map(carteFideliteMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CartFideliteResponse> findByPointsGreaterThanOrderByDate(Integer points) {
        return carteFideliteRepository.findByPointsAcumulesGreaterThanEqualOrderByDateCreationAsc(points)
                .stream()
                .map(carteFideliteMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CartFideliteResponse> findByDateBetween(LocalDate start, LocalDate end) {
        return carteFideliteRepository.findByDateCreationBetween(start, end)
                .stream()
                .map(carteFideliteMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CartFideliteResponse> findByPointsLessThanOrDateBefore(Integer points, LocalDate date) {
        return carteFideliteRepository.findByPointsAcumulesLessThanOrDateCreationLessThan(points, date)
                .stream()
                .map(carteFideliteMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CartFideliteResponse findTopByPoints() {
        CarteFidelite carte = carteFideliteRepository.findTopByOrderByPointsAcumulesDesc();
        return (carte != null) ? carteFideliteMapper.toDto(carte) : null;
    }

    @Override
    public List<CartFideliteResponse> findWithoutDateCreation() {
        return carteFideliteRepository.findByDateCreationIsNull()
                .stream()
                .map(carteFideliteMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CartFideliteResponse> findWithPoints() {
        return carteFideliteRepository.findByPointsAcumulesIsNotNull()
                .stream()
                .map(carteFideliteMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CartFideliteResponse> findByClientNomAndPrenom(String nom, String prenom) {
        return carteFideliteRepository.findByClientNomAndClientPrenom(nom, prenom)
                .stream()
                .map(carteFideliteMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CartFideliteResponse> findTop5ByPoints() {
        return carteFideliteRepository.findTop5ByOrderByPointsAcumulesDesc(PageRequest.of(0, 5))
                .stream()
                .map(carteFideliteMapper::toDto)
                .collect(Collectors.toList());
    }
}