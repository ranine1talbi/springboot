package tn.esprit.spring.tpcafe_talbiranine.services.CarteFidelite;

import tn.esprit.spring.tpcafe_talbiranine.dto.CartFidelite.CartFideliteRequest;
import tn.esprit.spring.tpcafe_talbiranine.dto.CartFidelite.CartFideliteResponse;
import tn.esprit.spring.tpcafe_talbiranine.entites.CarteFidelite;

import java.time.LocalDate;
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

    // 🔹 Méthodes JPQL avancées

    List<CartFideliteResponse> findByPointsExact(Integer points);

    List<CartFideliteResponse> findByDateCreation(LocalDate date);

    Long countByPointsGreaterThan(Integer points);

    void deleteByDateBefore(LocalDate date);

    List<CartFideliteResponse> findByPointsBetweenAndDateAfter(Integer minPoints, Integer maxPoints, LocalDate date);

    List<CartFideliteResponse> findByPointsGreaterThanOrderByDate(Integer points);

    List<CartFideliteResponse> findByDateBetween(LocalDate start, LocalDate end);

    List<CartFideliteResponse> findByPointsLessThanOrDateBefore(Integer points, LocalDate date);

    CartFideliteResponse findTopByPoints();

    List<CartFideliteResponse> findWithoutDateCreation();

    List<CartFideliteResponse> findWithPoints();

    List<CartFideliteResponse> findByClientNomAndPrenom(String nom, String prenom);

    List<CartFideliteResponse> findTop5ByPoints();
    void affecterCarteAClient(long idClient, long idCarteFidelite);
    void desaffecterCarteAClient(long idClient, long idCarteFidelite);
}
