package tn.esprit.spring.tpcafe_talbiranine.services.Commande;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.tpcafe_talbiranine.dto.Commande.CommandeRequest;
import tn.esprit.spring.tpcafe_talbiranine.dto.Commande.CommandeResponse;
import tn.esprit.spring.tpcafe_talbiranine.entites.*;
import tn.esprit.spring.tpcafe_talbiranine.mapper.Commande.CommandeMappers;
import tn.esprit.spring.tpcafe_talbiranine.repositories.ClientRepository;
import tn.esprit.spring.tpcafe_talbiranine.repositories.CommandeRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommandeService implements ICommandeServices {

    private final CommandeRepository commandeRepository;
    private final CommandeMappers commandeMapper;

    ClientRepository clientRepository;
    // ------------------- Méthodes CRUD de base -------------------
    @Override
    public CommandeResponse addCommande(CommandeRequest commandeRequest) {
        Commande commande = commandeMapper.toEntity(commandeRequest);
        Commande saved = commandeRepository.save(commande);
        return commandeMapper.toDto(saved);
    }


    @Override
    public void affecterCommandeAClient( long idClient,long idCommande) {
        Commande commande = commandeRepository.findById(idCommande).get();
        Client client = clientRepository.findById(idClient).get();
        client.getCommandes().add(commande);
        commandeRepository.save(commande);
    }

    @Override
    public void desaffecterClientDeCommande(long idClient,long idCommande) {
        Commande commande = commandeRepository.findById(idCommande).get();
        Client client = clientRepository.findById(idClient).get();
        client.getCommandes().remove(commande);
        commandeRepository.save(commande);
    }



    @Override
    public List<CommandeResponse> saveCommandes(List<CommandeRequest> commandesRequest) {
        List<Commande> commandes = commandesRequest.stream()
                .map(commandeMapper::toEntity)
                .collect(Collectors.toList());
        List<Commande> saved = commandeRepository.saveAll(commandes);
        return saved.stream()
                .map(commandeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CommandeResponse selectCommandeById(long id) {
        return commandeRepository.findById(id)
                .map(commandeMapper::toDto)
                .orElse(null);
    }

    @Override
    public List<CommandeResponse> selectAllCommandes() {
        return commandeRepository.findAll().stream()
                .map(commandeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCommandeById(long id) {
        commandeRepository.deleteById(id);
    }

    @Override
    public void deleteAllCommandes() {
        commandeRepository.deleteAll();
    }

    @Override
    public long countCommandes() {
        return commandeRepository.count();
    }

    @Override
    public boolean verifCommandeById(long id) {
        return commandeRepository.existsById(id);
    }

    // ------------------- Méthodes avancées -------------------

    // 1. Trouver par statut
    public List<CommandeResponse> findByStatus(StatusCommande status) {
        return commandeRepository.findByStatusCommande(status).stream()
                .map(commandeMapper::toDto)
                .collect(Collectors.toList());
    }

    // 2. Trouver par date exacte
    public List<CommandeResponse> findByDate(LocalDate date) {
        return commandeRepository.findByDateCommande(date).stream()
                .map(commandeMapper::toDto)
                .collect(Collectors.toList());
    }

    // 3. Compter par statut
    public long countByStatus(StatusCommande status) {
        return commandeRepository.countByStatusCommande(status);
    }

    // 4. Supprimer avant une date
    public void deleteBeforeDate(LocalDate date) {
        commandeRepository.deleteByDateCommandeBefore(date);
    }

    // 5. Trouver entre deux dates avec statut spécifique
    public List<CommandeResponse> findBetweenDatesWithStatus(LocalDate start, LocalDate end, StatusCommande status) {
        return commandeRepository.findByDateCommandeBetweenAndStatusCommande(start, end, status).stream()
                .map(commandeMapper::toDto)
                .collect(Collectors.toList());
    }

    // 6. Trouver avec total > montant et statut différent
    public List<CommandeResponse> findTotalGreaterThanAndStatusNot(float total, StatusCommande status) {
        return commandeRepository.findByTotalCommandeGreaterThanAndStatusCommandeNot(total, status).stream()
                .map(commandeMapper::toDto)
                .collect(Collectors.toList());
    }

    // 7. Trouver avec certains statuts, triées par date
    public List<CommandeResponse> findByStatusesOrderByDateDesc(List<StatusCommande> statuses) {
        return commandeRepository.findByStatusCommandeInOrderByDateCommandeDesc(statuses).stream()
                .map(commandeMapper::toDto)
                .collect(Collectors.toList());
    }

    // 8. Trouver avant une date avec total dans une plage
    public List<CommandeResponse> findBeforeDateWithTotalBetween(LocalDate date, float min, float max) {
        return commandeRepository.findByDateCommandeBeforeAndTotalCommandeBetween(date, min, max).stream()
                .map(commandeMapper::toDto)
                .collect(Collectors.toList());
    }

    // 9. Trouver où statut se termine par une lettre
    public List<CommandeResponse> findByStatusEndingWith(String letter) {
        return commandeRepository.findByStatusCommandeEndingWith(letter).stream()
                .map(commandeMapper::toDto)
                .collect(Collectors.toList());
    }

    // 10. Trouver commandes sans statut
    public List<CommandeResponse> findWithoutStatus() {
        return commandeRepository.findByStatusCommandeIsNull().stream()
                .map(commandeMapper::toDto)
                .collect(Collectors.toList());
    }

    // 11. Trouver commandes avec total renseigné
    public List<CommandeResponse> findWithTotal() {
        return commandeRepository.findByTotalCommandeIsNotNull().stream()
                .map(commandeMapper::toDto)
                .collect(Collectors.toList());
    }

    // 12. Trouver commandes avec détails et client
    public List<CommandeResponse> findAllWithDetailsAndClient() {
        return commandeRepository.findAllWithClientAndDetails().stream()
                .map(commandeMapper::toDto)
                .collect(Collectors.toList());
    }

    // 13. Top 3 commandes les plus récentes
    public List<CommandeResponse> findTop3Recent() {
        return commandeRepository.findTop3ByOrderByDateCommandeDesc().stream()
                .map(commandeMapper::toDto)
                .collect(Collectors.toList());
    }
}
