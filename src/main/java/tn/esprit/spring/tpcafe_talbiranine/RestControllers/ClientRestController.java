package tn.esprit.spring.tpcafe_talbiranine.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.tpcafe_talbiranine.entites.Client;
import tn.esprit.spring.tpcafe_talbiranine.services.Client.IClientServices;

import java.util.List;

@RestController
@RequestMapping("client")
@AllArgsConstructor
public class ClientRestController {

    private final IClientServices clientServices;

    // 🔹 GET : récupérer tous les clients
    @GetMapping
    public List<Client> selectAllClients() {
        return clientServices.selectAllClients();
    }

    // 🔹 POST : ajouter un seul client
    @PostMapping
    public Client addClient(@RequestBody Client client) {
        return clientServices.addClient(client);
    }

    // 🔹 POST : ajouter plusieurs clients
    @PostMapping("/all")
    public List<Client> addClients(@RequestBody List<Client> clients) {
        return clientServices.saveClients(clients);
    }

    // 🔹 GET : récupérer un client par ID (PathVariable)
    @GetMapping("/{id}")
    public Client selectClientById(@PathVariable long id) {
        return clientServices.selectClientById(id);
    }



    // 🔹 DELETE : supprimer un client par ID
    @DeleteMapping("deleteById/{id}")
    public void deleteClientById(@PathVariable long id) {
        clientServices.deleteClientById(id);
    }

    // 🔹 DELETE : supprimer tous les clients
    @DeleteMapping("deleteAll")
    public void deleteAllClients() {
        clientServices.deleteAllClients();
    }

    // 🔹 GET : compter le nombre de clients
    @GetMapping("count")
    public long countClients() {
        return clientServices.countClients();
    }

    // 🔹 GET : vérifier si un client existe par ID
    @GetMapping("exists/{id}")
    public boolean verifClientById(@PathVariable long id) {
        return clientServices.verifClientById(id);
    }
}
