package tn.esprit.spring.tpcafe_talbiranine.services.Client;

import tn.esprit.spring.tpcafe_talbiranine.dto.Client.ClientRequest;
import tn.esprit.spring.tpcafe_talbiranine.dto.Client.ClientResponse;
import tn.esprit.spring.tpcafe_talbiranine.entites.Commande;
import tn.esprit.spring.tpcafe_talbiranine.entites.TypeArticle;

import java.time.LocalDate;
import java.util.List;

public interface IClientServices {

    ClientResponse addClient(ClientRequest clientRequest);
    List<ClientResponse> saveClients(List<ClientRequest> clientRequests);
    ClientResponse selectClientById(long id);
    List<ClientResponse> selectAllClients();
    void deleteClientById(long id);
    void deleteAllClients();
    long countClients();
    boolean verifClientById(long id);

    // Méthodes personnalisées
    List<ClientResponse> findByNom(String nom);
    List<ClientResponse> findByPrenom(String prenom);
    ClientResponse findByNomAndPrenom(String nom, String prenom);
    boolean existsByNom(String nom);
    long countByDateNaissanceAfter(LocalDate date);
    List<ClientResponse> findByNomOrPrenomContaining(String keyword);
    List<ClientResponse> findByNomAndPrenomContaining(String nomKeyword, String prenomKeyword);
    List<ClientResponse> findByDateNaissanceBetween(LocalDate start, LocalDate end);
    List<ClientResponse> findByNomStartingWithAndDateNaissanceBefore(String prefix, LocalDate date);
    List<ClientResponse> findByVille(String ville);
    List<ClientResponse> findByNomContainingOrderByPrenomAsc(String keyword);
    List<ClientResponse> findByNomContainingOrderByPrenomDesc(String keyword);
    List<ClientResponse> findByNomStartingWith(String letter);
    List<ClientResponse> findByPrenomEndingWith(String suffix);
    List<ClientResponse> findClientsWithoutDateNaissance();
    List<ClientResponse> findClientsWithAdresse();
    List<ClientResponse> findClientsByVilles(List<String> villes);

    // Méthodes liées aux points fidélité
    List<ClientResponse> findClientsByPtsAccumulesGreaterThan(int pts);
    List<ClientResponse> findClientsByPtsAccumulesGreaterOrEqual(int pts);
    List<ClientResponse> findClientsByPtsAccumulesBetween(int min, int max);

    // Méthodes liées aux commandes/articles
    List<ClientResponse> findClientsByArticleName(String articleName);

    // ✅ Correction : utiliser TypeArticle
void ajouterCommandeEtAffecterAClient(Commande commande ,  String nomC, String prenomC);

    List<ClientResponse> findClientsByNomContainsAndArticleType(String nomKeyword, TypeArticle typeArticle);
}

