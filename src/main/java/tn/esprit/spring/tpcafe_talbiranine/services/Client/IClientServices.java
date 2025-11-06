package tn.esprit.spring.tpcafe_talbiranine.services.Client;

import tn.esprit.spring.tpcafe_talbiranine.entites.Client;
import java.util.List;

public interface IClientServices {
    Client addClient(Client client);
    List<Client> saveClients(List<Client> clients);
    Client selectClientById(long id);
    List<Client> selectAllClients();
    void deleteClient(Client client);
    void deleteAllClients();
    void deleteClientById(long id);
    long countClients();
    boolean verifClientById(long id);
}
