package tn.esprit.spring.tpcafe_talbiranine.services.CarteFidelite;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.tpcafe_talbiranine.dto.CartFidelite.CartFideliteRequest;
import tn.esprit.spring.tpcafe_talbiranine.dto.CartFidelite.CartFideliteResponse;
import tn.esprit.spring.tpcafe_talbiranine.entites.CarteFidelite;
import tn.esprit.spring.tpcafe_talbiranine.entites.Client;
import tn.esprit.spring.tpcafe_talbiranine.mapper.CartFidelite.CartFideliteMappers;
import tn.esprit.spring.tpcafe_talbiranine.repositories.CarteFideliteRepository;
import tn.esprit.spring.tpcafe_talbiranine.repositories.ClientRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CarteFideliteService implements ICarteFideliteServices {

    private final CarteFideliteRepository carteFideliteRepository;
    private final ClientRepository clientRepository;
    private final CartFideliteMappers carteFideliteMapper;

    // ✅ Ajouter une carte via DTO
    @Override
    public CartFideliteResponse addCarteFidelite(CartFideliteRequest carteRequest) {
        CarteFidelite carte = carteFideliteMapper.toEntity(carteRequest);

        // Associer le client s’il existe
        if (carteRequest.getClientId() != null) {
            Client client = clientRepository.findById(carteRequest.getClientId()).orElse(null);
            carte.setClient(client);
        }

        CarteFidelite saved = carteFideliteRepository.save(carte);
        return carteFideliteMapper.toDto(saved);
    }

    // ✅ Ajouter plusieurs cartes
    @Override
    public List<CartFideliteResponse> saveCartesFidelite(List<CartFideliteRequest> cartesRequest) {
        List<CarteFidelite> cartes = cartesRequest.stream()
                .map(carteFideliteMapper::toEntity)
                .collect(Collectors.toList());

        // Lier les clients si les IDs sont fournis
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

    // ✅ Récupérer toutes les cartes
    @Override
    public List<CartFideliteResponse> selectAllCartes() {
        return carteFideliteRepository.findAll()
                .stream()
                .map(carteFideliteMapper::toDto)
                .collect(Collectors.toList());
    }

    // ✅ Récupérer une carte par ID
    @Override
    public CartFideliteResponse recupererCarteParId(long id) {
        CarteFidelite carte = carteFideliteRepository.findById(id).orElse(null);
        return (carte != null) ? carteFideliteMapper.toDto(carte) : null;
    }

    // ✅ Suppressions et autres opérations de base
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
}
