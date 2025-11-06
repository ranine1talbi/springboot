package tn.esprit.spring.tpcafe_talbiranine.services.Client;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.tpcafe_talbiranine.entites.Client;
import tn.esprit.spring.tpcafe_talbiranine.repositories.ClientRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientService implements IClientServices {

    private final ClientRepository clientRepository;

    @Override
    public Client addClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public List<Client> saveClients(List<Client> clients) {
        return clientRepository.saveAll(clients);
    }

    @Override
    public Client selectClientById(long id) {
        return clientRepository.findById(id).orElse(null);
    }




    @Override
    public List<Client> selectAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public void deleteClient(Client client) {
        clientRepository.delete(client);
    }

    @Override
    public void deleteAllClients() {
        clientRepository.deleteAll();
    }

    @Override
    public void deleteClientById(long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public long countClients() {
        return clientRepository.count();
    }

    @Override
    public boolean verifClientById(long id) {
        return clientRepository.existsById(id);
    }
}
