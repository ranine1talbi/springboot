package tn.esprit.spring.tpcafe_talbiranine.services.Client;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.tpcafe_talbiranine.dto.Client.ClientRequest;
import tn.esprit.spring.tpcafe_talbiranine.dto.Client.ClientResponse;
import tn.esprit.spring.tpcafe_talbiranine.entites.Client;
import tn.esprit.spring.tpcafe_talbiranine.entites.Commande;
import tn.esprit.spring.tpcafe_talbiranine.entites.TypeArticle;
import tn.esprit.spring.tpcafe_talbiranine.mapper.Client.ClientMappers;
import tn.esprit.spring.tpcafe_talbiranine.repositories.ClientRepository;
import tn.esprit.spring.tpcafe_talbiranine.repositories.CommandeRepository;
import tn.esprit.spring.tpcafe_talbiranine.services.Commande.CommandeService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClientService implements IClientServices {

    private final ClientRepository clientRepository;
    private final ClientMappers clientMappers;
    CommandeRepository commandeRepository;

    @Override
    public ClientResponse addClient(ClientRequest clientRequest) {
        Client client = clientMappers.toEntity(clientRequest);
        Client saved = clientRepository.save(client);
        return clientMappers.toDto(saved);
    }

    @Override
    public List<ClientResponse> saveClients(List<ClientRequest> clientRequests) {
        List<Client> clients = clientRequests.stream()
                .map(clientMappers::toEntity)
                .collect(Collectors.toList());
        List<Client> saved = clientRepository.saveAll(clients);
        return saved.stream().map(clientMappers::toDto).collect(Collectors.toList());
    }

    @Override
    public ClientResponse selectClientById(long id) {
        return clientRepository.findById(id)
                .map(clientMappers::toDto)
                .orElse(null);
    }

    @Override
    public List<ClientResponse> selectAllClients() {
        return clientRepository.findAll().stream()
                .map(clientMappers::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteClientById(long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public void deleteAllClients() {
        clientRepository.deleteAll();
    }

    @Override
    public long countClients() {
        return clientRepository.count();
    }

    @Override
    public boolean verifClientById(long id) {
        return clientRepository.existsById(id);
    }

    // ================== Méthodes personnalisées ==================

    @Override
    public List<ClientResponse> findByNom(String nom) {
        return clientRepository.findByNom(nom).stream()
                .map(clientMappers::toDto).toList();
    }

    @Override
    public List<ClientResponse> findByPrenom(String prenom) {
        return clientRepository.findByPrenom(prenom).stream()
                .map(clientMappers::toDto).toList();
    }

    @Override
    public ClientResponse findByNomAndPrenom(String nom, String prenom) {
        Client client = clientRepository.findByNomAndPrenom(nom, prenom);
        return client != null ? clientMappers.toDto(client) : null;
    }


    @Override
    public boolean existsByNom(String nom) {
        return clientRepository.existsByNom(nom);
    }

    @Override
    public long countByDateNaissanceAfter(LocalDate date) {
        return clientRepository.countByDateNaissanceAfter(date);
    }

    @Override
    public List<ClientResponse> findByNomOrPrenomContaining(String keyword) {
        return clientRepository.findByNomContainingOrPrenomContaining(keyword, keyword).stream()
                .map(clientMappers::toDto).toList();
    }

    @Override
    public List<ClientResponse> findByNomAndPrenomContaining(String nomKeyword, String prenomKeyword) {
        return clientRepository.findByNomContainingAndPrenomContaining(nomKeyword, prenomKeyword).stream()
                .map(clientMappers::toDto).toList();
    }

    @Override
    public List<ClientResponse> findByDateNaissanceBetween(LocalDate start, LocalDate end) {
        return clientRepository.findByDateNaissanceBetween(start, end).stream()
                .map(clientMappers::toDto).toList();
    }

    @Override
    public List<ClientResponse> findByNomStartingWithAndDateNaissanceBefore(String prefix, LocalDate date) {
        return clientRepository.findByNomStartingWithAndDateNaissanceBefore(prefix, date).stream()
                .map(clientMappers::toDto).toList();
    }

    @Override
    public List<ClientResponse> findByVille(String ville) {
        return clientRepository.findByAdresse_Ville(ville).stream()
                .map(clientMappers::toDto).toList();
    }

    @Override
    public List<ClientResponse> findByNomContainingOrderByPrenomAsc(String keyword) {
        return clientRepository.findByNomContainingOrderByPrenomAsc(keyword).stream()
                .map(clientMappers::toDto).toList();
    }

    @Override
    public List<ClientResponse> findByNomContainingOrderByPrenomDesc(String keyword) {
        return clientRepository.findByNomContainingOrderByPrenomDesc(keyword).stream()
                .map(clientMappers::toDto).toList();
    }

    @Override
    public List<ClientResponse> findByNomStartingWith(String letter) {
        return clientRepository.findByNomStartingWith(letter).stream()
                .map(clientMappers::toDto).toList();
    }

    @Override
    public List<ClientResponse> findByPrenomEndingWith(String suffix) {
        return clientRepository.findByPrenomEndingWith(suffix).stream()
                .map(clientMappers::toDto).toList();
    }

    @Override
    public List<ClientResponse> findClientsWithoutDateNaissance() {
        return clientRepository.findByDateNaissanceIsNull().stream()
                .map(clientMappers::toDto).toList();
    }

    @Override
    public List<ClientResponse> findClientsWithAdresse() {
        return clientRepository.findByAdresseIsNotNull().stream()
                .map(clientMappers::toDto).toList();
    }

    @Override
    public List<ClientResponse> findClientsByVilles(List<String> villes) {
        return clientRepository.findByAdresse_VilleIn(villes).stream()
                .map(clientMappers::toDto).toList();
    }

    // ================== Méthodes liées aux points fidélité ==================

    @Override
    public List<ClientResponse> findClientsByPtsAccumulesGreaterThan(int pts) {
        return clientRepository.findByCarteFidelite_PointsAcumulesGreaterThan(pts)
                .stream()
                .map(clientMappers::toDto)
                .toList();
    }

    @Override
    public List<ClientResponse> findClientsByPtsAccumulesGreaterOrEqual(int pts) {
        return clientRepository.findByCarteFidelite_PointsAcumulesGreaterThanEqual(pts)
                .stream()
                .map(clientMappers::toDto)
                .toList();
    }

    @Override
    public List<ClientResponse> findClientsByPtsAccumulesBetween(int min, int max) {
        return clientRepository.findByCarteFidelite_PointsAcumulesBetween(min, max)
                .stream()
                .map(clientMappers::toDto)
                .toList();
    }

    // ================== Méthodes liées aux commandes/articles ==================

    @Override
    public List<ClientResponse> findClientsByArticleName(String articleName) {
        return clientRepository.findByCommandes_Details_Article_NomArticle(articleName).stream()
                .map(clientMappers::toDto)
                .toList();
    }

    @Override
    public void ajouterCommandeEtAffecterAClient(Commande c, String nomC, String prenomC) {
        c =commandeRepository.save(c);
        Client client = clientRepository.findByNomAndPrenom(nomC,prenomC);
        c.setClient(client);
        commandeRepository.save(c);

    }


    @Override
    public List<ClientResponse> findClientsByNomContainsAndArticleType(String nomKeyword, TypeArticle type) {
        return clientRepository
                .findByNomContainingAndCommandes_Details_Article_TypeArticle(nomKeyword, type)
                .stream()
                .map(clientMappers::toDto)
                .toList();
    }


}
