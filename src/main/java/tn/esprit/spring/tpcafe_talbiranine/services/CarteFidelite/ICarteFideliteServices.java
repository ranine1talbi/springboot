package tn.esprit.spring.tpcafe_talbiranine.services.CarteFidelite;

import tn.esprit.spring.tpcafe_talbiranine.dto.CartFidelite.CartFideliteRequest;
import tn.esprit.spring.tpcafe_talbiranine.dto.CartFidelite.CartFideliteResponse;
import tn.esprit.spring.tpcafe_talbiranine.entites.CarteFidelite;

import java.util.List;

public interface ICarteFideliteServices {

    // ✅ Méthodes utilisant les DTOs
    CartFideliteResponse addCarteFidelite(CartFideliteRequest carteRequest);
    List<CartFideliteResponse> saveCartesFidelite(List<CartFideliteRequest> cartesRequest);
    List<CartFideliteResponse> selectAllCartes();
    CartFideliteResponse recupererCarteParId(long id);

    // ✅ Méthodes CRUD de base (entités)
    void deleteCarte(CarteFidelite carte);
    void deleteAllCartes();
    void deleteCarteById(long id);
    long countCartes();
    boolean verifCarteById(long id);
}
