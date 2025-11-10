package tn.esprit.spring.tpcafe_talbiranine.services.Client;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.tpcafe_talbiranine.dto.Client.ClientRequest;
import tn.esprit.spring.tpcafe_talbiranine.dto.Client.ClientResponse;
import tn.esprit.spring.tpcafe_talbiranine.entites.Client;
import tn.esprit.spring.tpcafe_talbiranine.mapper.Client.ClientMappers;
import tn.esprit.spring.tpcafe_talbiranine.repositories.ClientRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClientService implements IClientServices {

    private final ClientRepository clientRepository;
    private final ClientMappers clientMappers;

    // ✅ Ajouter un client via DTO
    @Override
    public ClientResponse addClient(ClientRequest clientRequest) {
        Client client = clientMappers.toEntity(clientRequest);
        Client saved = clientRepository.save(client);
        return clientMappers.toDto(saved);
    }

    // ✅ Ajouter plusieurs clients via DTO
    @Override
    public List<ClientResponse> saveClients(List<ClientRequest> clientRequests) {
        List<Client> clients = clientRequests.stream()
                .map(clientMappers::toEntity)
                .collect(Collectors.toList());
        List<Client> saved = clientRepository.saveAll(clients);
        return saved.stream().map(clientMappers::toDto).collect(Collectors.toList());
    }

    // ✅ Récupérer un client par ID
    @Override
    public ClientResponse selectClientById(long id) {
        Client client = clientRepository.findById(id).orElse(null);
        return (client != null) ? clientMappers.toDto(client) : null;
    }

    // ✅ Récupérer tous les clients
    @Override
    public List<ClientResponse> selectAllClients() {
        return clientRepository.findAll().stream()
                .map(clientMappers::toDto)
                .collect(Collectors.toList());
    }

    // ✅ Supprimer un client
    @Override
    public void deleteClientById(long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public void deleteAllClients() {
        clientRepository.deleteAll();
    }

    // ✅ Compter le nombre de clients
    @Override
    public long countClients() {
        return clientRepository.count();
    }

    // ✅ Vérifier si un client existe
    @Override
    public boolean verifClientById(long id) {
        return clientRepository.existsById(id);
    }
}
