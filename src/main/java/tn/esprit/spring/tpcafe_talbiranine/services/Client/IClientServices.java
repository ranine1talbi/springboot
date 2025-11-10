package tn.esprit.spring.tpcafe_talbiranine.services.Client;

import tn.esprit.spring.tpcafe_talbiranine.dto.Client.ClientRequest;
import tn.esprit.spring.tpcafe_talbiranine.dto.Client.ClientResponse;
import tn.esprit.spring.tpcafe_talbiranine.entites.Client;

import java.util.List;

public interface IClientServices {

    // ✅ Méthodes utilisant les DTOs
    ClientResponse addClient(ClientRequest clientRequest);
    List<ClientResponse> saveClients(List<ClientRequest> clientRequests);
    ClientResponse selectClientById(long id);
    List<ClientResponse> selectAllClients();

    // ✅ Méthodes de gestion simples
    void deleteAllClients();
    void deleteClientById(long id);

    long countClients();
    boolean verifClientById(long id);
}
